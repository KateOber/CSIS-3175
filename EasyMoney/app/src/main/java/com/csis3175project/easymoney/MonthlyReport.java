package com.csis3175project.easymoney;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MonthlyReport extends AppCompatActivity {
    EMDatabase database;
    Cursor expenses;
    Cursor income;
    double savings, EA1, EA2, EA3, EA4, todayExpense, netIncome;
    String EN1,EN2,EN3,EN4, ED1, ED2, ED3, ED4, formatIncome;
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
                    if (formattedDate.equals(expenseDate)) {
                        todayExpense += expenseAmmount;
                        System.out.println("total expense"+ todayExpense);
                    }

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

        formatIncome = String.format("%.2f", netIncome);
        totalIncometxt.setText("$"+formatIncome);

        Button btnProfileFooter = findViewById(R.id.userReportFoot);
        Button btnExpenseTrackerFooter = findViewById(R.id.trackerReportFoot);
        Button btnBigExpenseFooter = findViewById(R.id.bEReportFoot);
        Button btnReportFooter = findViewById(R.id.trackerReportFoot);
    }
}