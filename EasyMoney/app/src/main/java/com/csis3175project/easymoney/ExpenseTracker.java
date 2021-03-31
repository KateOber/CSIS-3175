package com.csis3175project.easymoney;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ExpenseTracker extends AppCompatActivity {
    int DAP;
    EMDatabase database;
    Cursor expenses;
    Cursor income;
    double savings, EA1, EA2, EA3, EA4;
    String EC1,EC2,EC3,EC4, ED1, ED2, ED3, ED4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_tracker);

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String username = sharedPref.getString("username","");



        database = new EMDatabase(this);
        expenses = database.getEXPENSEData();
        income = database.getINCOMEData();
        savings = database.getSavings(username);
        
        ProgressBar DAPRogress = findViewById(R.id.DASpentProgress);

        Button gotoTrackerbtn = findViewById(R.id.calendarTrackerBtn);
        Button addIncomebtn = findViewById(R.id.trackerAddIncomeBtn);
        Button addExpensebtn = findViewById(R.id.trackerAddExpenseBtn);
        Button addSavingsbtn = findViewById(R.id.trackerAddSavingsbtn);

        TextView totalIncometxt = findViewById(R.id.trackerIncomeTotaltxt);
        double totalIncome=0;

        if(income.getCount()>0){
            while(income.moveToNext()){
                if(income.getString(1).equals(username)){
                    totalIncome += income.getDouble(3);
                }
            }
        }
        totalIncometxt.setText("$"+totalIncome);
        TextView totalSavingstxt = findViewById(R.id.trackerSavingsTotaltxt);
        totalSavingstxt.setText("$"+savings);
        TextView totalDAtxt = findViewById(R.id.DATotaltxt);
        double DA = database.getDAllowance(username);
        totalDAtxt.setText("$"+DA);



        TextView expensePrice1 = findViewById(R.id.trackerExpensePrice1);
        TextView expensePrice2 = findViewById(R.id.trackerExpensePrice2);
        TextView expensePrice3 = findViewById(R.id.trackerExpensePrice3);
        TextView expensePrice4 = findViewById(R.id.trackerExpensePrice4);

        TextView expenseDate1 = findViewById(R.id.trackerExpenseDate1);
        TextView expenseDate2 = findViewById(R.id.trackerExpenseDate2);
        TextView expenseDate3 = findViewById(R.id.trackerExpenseDate3);
        TextView expenseDate4 = findViewById(R.id.trackerExpenseDate4);

        ImageView expenseIcon1 = findViewById(R.id.trackerIcon1);
        ImageView expenseIcon2 = findViewById(R.id.trackerIcon2);
        ImageView expenseIcon3 = findViewById(R.id.trackerIcon3);
        ImageView expenseIcon4 = findViewById(R.id.trackerIcon4);
        String expenseCat, expenseDate;
        double expenseAmmount;
        int i=1;
        if(expenses.getCount()>0){
            while(expenses.moveToNext()){
                if(expenses.getString(1).equals(username)){
                    expenseCat = expenses.getString(5);
                    expenseDate = expenses.getString(4);
                    expenseAmmount = expenses.getDouble(3);
                    if(i==0){
                        EA1 = expenseAmmount;EC1 = expenseCat;ED1 = expenseDate;
                        expenseDate1.setText(ED1);expensePrice1.setText("$"+EA1);
                        if(EC1.equals("Dinning"));
                        else if(EC1.equals("Entertainment"));
                        else if(EC1.equals("Groceries"));
                        else if(EC1.equals("Transport"));
                        else if(EC1.equals("Shopping"));
                        else if(EC1.equals("Other"));
                    }
                    else if(i==1){
                        EA2 = expenseAmmount;EC2 = expenseCat;ED2 = expenseDate;
                        expenseDate2.setText(ED2);expensePrice2.setText("$"+EA2);
                    }
                    else if(i==2){
                        EA3 = expenseAmmount;EC3 = expenseCat;ED3 = expenseDate;
                        expenseDate3.setText(ED3);expensePrice3.setText("$"+EA3);
                    }
                    else if(i==3){
                        EA4 = expenseAmmount;EC4 = expenseCat;ED4 = expenseDate;
                        expenseDate4.setText(ED4);expensePrice4.setText("$"+EA4);
                    }
                }
            }
        }

        //footer buttons
        Button btnProfileFooter = findViewById(R.id.userTrackerFoot);
        Button btnExpenseTrackerFooter = findViewById(R.id.trackerTrackerFoot);
        Button btnBigExpenseFooter = findViewById(R.id.bETrackerFoot);
        Button btnReportFooter = findViewById(R.id.reportTrackerFoot);

        DAPRogress.setProgress(DAP);


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
                startActivity(new Intent(ExpenseTracker.this, MainActivity.class));
            }
        });
        btnBigExpenseFooter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ExpenseTracker.this, MainActivity.class));
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