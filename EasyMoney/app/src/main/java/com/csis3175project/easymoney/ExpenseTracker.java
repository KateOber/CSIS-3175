package com.csis3175project.easymoney;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ExpenseTracker extends AppCompatActivity {
    int DAP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_tracker);

        ProgressBar DAPRogress = findViewById(R.id.DASpentProgress);

        Button gotoTrackerbtn = findViewById(R.id.calendarTrackerBtn);
        Button addIncomebtn = findViewById(R.id.trackerAddIncomeBtn);
        Button addExpensebtn = findViewById(R.id.trackerAddExpenseBtn);
        Button addSavingsbtn = findViewById(R.id.trackerAddSavingsbtn);

        TextView totalIncometxt = findViewById(R.id.trackerIncomeTotaltxt);
        TextView totalSavingstxt = findViewById(R.id.trackerSavingsTotaltxt);
        TextView totalDAtxt = findViewById(R.id.DATotaltxt);
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

        //footer buttons
        Button btnProfileFooter = findViewById(R.id.userTrackerFoot);
        Button btnExpenseTrackerFooter = findViewById(R.id.trackerTrackerFoot);
        Button btnBigExpenseFooter = findViewById(R.id.bETrackerFoot);
        Button btnReportFooter = findViewById(R.id.reportTrackerFoot);

        DAPRogress.setProgress(DAP);


        gotoTrackerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ExpenseTracker.this, RecurringBills.class));

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