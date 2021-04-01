package com.csis3175project.easymoney;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ExpenseTracker extends AppCompatActivity {
    int DAP;
    EMDatabase database;
    Cursor expenses;
    Cursor income;
    double savings, EA1, EA2, EA3, EA4, todayExpense, DAT, DAF, netIncome, DAU, recurringExpense, recurringIncome;
    String EN1,EN2,EN3,EN4, ED1, ED2, ED3, ED4, formatIncome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_tracker);

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String username = sharedPref.getString("username","");

        todayExpense = 0;
        netIncome = 0;
        DAT = 0;

        database = new EMDatabase(this);
        expenses = database.getEXPENSEData();
        income = database.getINCOMEData();
        savings = database.getSavings(username);

        ProgressBar DAPRogress = findViewById(R.id.DASpentProgress);

        Button gotoTrackerbtn = findViewById(R.id.calendarTrackerBtn);
        Button addIncomebtn = findViewById(R.id.trackerAddIncomeBtn);
        Button addExpensebtn = findViewById(R.id.trackerAddExpenseBtn);
        Button addSavingsbtn = findViewById(R.id.trackerAddSavingsbtn);

        TextView totalIncometxt = findViewById(R.id.trackerExpenseTotaltxt);
        double totalIncome=0;

        if(income.getCount()>0){
            while(income.moveToNext()){
                if(income.getString(1).equals(username)){
                    if(income.getInt(5) == 1) recurringIncome+=income.getDouble(3);
                    totalIncome += income.getDouble(3);
                }
            }
        }
        netIncome = totalIncome;

        TextView totalSavingstxt = findViewById(R.id.trackerIncomeTotaltxt);
        totalSavingstxt.setText("$"+savings);
        TextView totalDAtxt = findViewById(R.id.DATotaltxt);
        double DA = database.getDAllowance(username);


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
        TextView expenseName2 = findViewById(R.id.trackerExpenseName2);
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
                    if(expenses.getInt(6)==0) recurringExpense += expenseAmmount;
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
        Calendar month = Calendar.getInstance();
        int monthMaxDays = month.getActualMaximum(Calendar.DAY_OF_MONTH);
        if(DA == 0){
            DA = (recurringIncome-recurringExpense) / monthMaxDays;
            database.updateDAllowance(DA, username);
        }
        DAF = DA - todayExpense;
        DAU = todayExpense/DA*100;
        DAP = (int) DAU;
        if(DAP>100)DAP=100;

        DAPRogress.setProgress(DAP);

        formatIncome = String.format("%.2f", netIncome);
        totalIncometxt.setText("$"+formatIncome);
        totalDAtxt.setText("$"+DAF);
        //footer buttons
        Button btnProfileFooter = findViewById(R.id.userReportFoot);
        Button btnExpenseTrackerFooter = findViewById(R.id.trackerReportFoot);
        Button btnBigExpenseFooter = findViewById(R.id.bEReportFoot);
        Button btnReportFooter = findViewById(R.id.reportReportFoot);


        gotoTrackerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ExpenseTracker.this, RecurringBillsCalendar.class));

            }
        });

        addIncomebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ExpenseTracker.this, AddIncome.class));

            }
        });

        addExpensebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ExpenseTracker.this, AddExpense.class));

            }
        });

        addSavingsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ExpenseTracker.this, AddSavings.class));

            }
        });

        //Footer Buttons
        btnExpenseTrackerFooter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ExpenseTracker.this, ExpenseTracker.class));
            }
        });
        btnReportFooter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ExpenseTracker.this, MonthlyReport.class));
            }
        });
        btnBigExpenseFooter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ExpenseTracker.this, BigExpense_setup.class));
            }
        });
        btnProfileFooter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ExpenseTracker.this, Profile.class));
            }
        });

    }
}
