package com.csis3175project.easymoney;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MonthlyReport extends AppCompatActivity {
    EMDatabase database;
    Cursor expenses;
    Cursor income;
    double savings, EA1, EA2, EA3, EA4, todayExpense, netIncome, netData;
    String EN1,EN2,EN3,EN4, ED1, ED2, ED3, ED4, formatIncome, formatExpense, formatnetExpense;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monthly_report);

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String username = sharedPref.getString("username","");

        netIncome = 0;
        database = new EMDatabase(this);
        expenses = database.getEXPENSEData();
        income = database.getINCOMEData();
        savings = database.getSavings(username);

        TextView totalIncometxt = findViewById(R.id.trackerIncomeTotaltxt);
        double totalIncome=0;

        if(income.getCount()>0){
            while(income.moveToNext()){
                if(income.getString(1).equals(username)){
                    totalIncome += income.getDouble(3);
                }
            }
        }
        netIncome = totalIncome;

        TextView totalSavingsTotaltxt = findViewById(R.id.trackerSavingsTotaltxt);
        totalSavingsTotaltxt.setText("$"+savings);

        Date c = Calendar.getInstance().getTime();

        SimpleDateFormat df = new SimpleDateFormat("yyyy/M/dd", Locale.getDefault());
        String formattedDate = df.format(c);

        TextView expensePrice1 = findViewById(R.id.reportExpensePrice1);
        TextView expensePrice2 = findViewById(R.id.reportExpensePrice2);
        TextView expensePrice3 = findViewById(R.id.reportExpensePrice3);
        TextView expensePrice4 = findViewById(R.id.reportExpensePrice4);

        TextView expenseDate1 = findViewById(R.id.reportExpenseDate1);
        TextView expenseDate2 = findViewById(R.id.reportExpenseDate2);
        TextView expenseDate3 = findViewById(R.id.reportExpenseDate3);
        TextView expenseDate4 = findViewById(R.id.reportExpenseDate4);

        TextView expenseName1 = findViewById(R.id.reportExpenseName1);
        TextView expenseName2 = findViewById(R.id.reportExpenseName2);
        TextView expenseName3 = findViewById(R.id.reportExpenseName3);
        TextView expenseName4 = findViewById(R.id.reportExpenseName4);

        String expenseName, expenseDate;
        double expenseAmmount;
        int i=1;
        if(expenses.getCount()>0){
            for (expenses.moveToLast(); !expenses.isBeforeFirst(); expenses.moveToPrevious()) {
                expenseDate = expenses.getString(4);
                expenseAmmount = expenses.getDouble(3);
                expenseName = expenses.getString(2);
                if (expenses.getString(1).equals(username)) {
                    netIncome -= expenseAmmount;
                    todayExpense += expenseAmmount;


                    if (i == 0) {
                        EA1 = expenseAmmount;
                        EN1 = expenseName;
                        ED1 = expenseDate;
                        expenseDate1.setText(ED1);
                        expensePrice1.setText("$" + EA1);
                        expenseName1.setText(expenseName);
                    } else if (i == 1) {
                        EA2 = expenseAmmount;
                        EN2 = expenseName;
                        ED2 = expenseDate;
                        expenseDate2.setText(ED2);
                        expensePrice2.setText("$" + EA2);
                        expenseName2.setText(expenseName);
                    } else if (i == 2) {
                        EA3 = expenseAmmount;
                        EN3 = expenseName;
                        ED3 = expenseDate;
                        expenseDate3.setText(ED3);
                        expensePrice3.setText("$" + EA3);
                        expenseName3.setText(expenseName);
                    } else if (i == 3) {
                        EA4 = expenseAmmount;
                        EN4 = expenseName;
                        ED4 = expenseDate;
                        expenseDate4.setText(ED4);
                        expensePrice4.setText("$" + EA4);
                        expenseName4.setText(EN4);
                    }
                    i++;
                }
            }
        }

        TextView totalExpensetxt = findViewById(R.id.trackerExpenseTotaltxt);
        formatExpense = String.format("%.2f", todayExpense);
        totalExpensetxt.setText("$"+formatExpense);
        formatIncome = String.format("%.2f", netIncome);
        totalIncometxt.setText("$"+formatIncome);

        netData = netIncome - todayExpense;
        formatnetExpense = String.format("%.2f", netData);
        TextView NetData = findViewById(R.id.txtEnteredExp);
        TextView positiveNegativetxt = findViewById(R.id.txtEntBExpPeriod);
        if(netData < 0){
            NetData.setTextColor(Color.parseColor("#DC0000"));
            positiveNegativetxt.setText("Was Negative. removed expenses from Income.");
            database.updateSavings(savings+=netData, username);
        }
        else positiveNegativetxt.setText("Was positive! You saved money!");
        NetData.setText("$"+formatnetExpense);

        Button btnProfileFooter = findViewById(R.id.userReportFoot);
        Button btnExpenseTrackerFooter = findViewById(R.id.reportReportFoot);
        Button btnBigExpenseFooter = findViewById(R.id.bEReportFoot);
        Button btnReportFooter = findViewById(R.id.trackerReportFoot);
        //Footer Buttons
        btnExpenseTrackerFooter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MonthlyReport.this, ExpenseTracker.class));
            }
        });
        btnReportFooter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MonthlyReport.this, MonthlyReport.class));
            }
        });
        btnBigExpenseFooter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MonthlyReport.this, MainActivity.class));
            }
        });
        btnProfileFooter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MonthlyReport.this, Profile.class));
            }
        });
    }
}