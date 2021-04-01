package com.csis3175project.easymoney;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

//TODO: get value from 2 lists and one amount, go to next screen - Big Expenses main
//
public class BigExpense_setup extends AppCompatActivity {
    double costOfExpense;
    String categoriesExpense;
    int expensePeriod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_expense_setup);

        Spinner groupCat = findViewById(R.id.txtCatExpGroup);
        TextView groupPeriod = findViewById(R.id.BEPeriod);
        EditText cost = findViewById(R.id.BEPeriod);
        TextView output = findViewById(R.id.txtOutput);

        Button button = findViewById(R.id.btnBigEx);


        //footer buttons
        Button btnProfileFooter = findViewById(R.id.bigExUsTrackerFoot);
        Button btnExpenseTrackerFooter = findViewById(R.id.trackerBigExTrackerFoot);
        Button btnBigExpenseFooter = findViewById(R.id.bEBexTrackerFoot);
        Button btnReportFooter = findViewById(R.id.reportBexTrackerFoot);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    costOfExpense = Double.parseDouble(cost.getText().toString());
                    expensePeriod = Integer.parseInt(groupPeriod.getText().toString());
                    categoriesExpense = groupCat.getSelectedItem().toString();
                    Intent intent = new Intent( BigExpense_setup.this,BigExpenses_main.class);
                    intent.putExtra("cost", costOfExpense);
                    intent.putExtra("Period",expensePeriod);
                    intent.putExtra("cat",categoriesExpense);
                    startActivity(intent);
                } catch (NumberFormatException ex) {
                    output.setText("Enter the cost of expense");
                }
            }
        });

        //Footer Buttons
        btnExpenseTrackerFooter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BigExpense_setup.this, ExpenseTracker.class));
            }
        });
        btnReportFooter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BigExpense_setup.this, MainActivity.class));
            }
        });
        btnBigExpenseFooter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BigExpense_setup.this, BigExpense_setup.class));
            }
        });
        btnProfileFooter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BigExpense_setup.this, Profile.class));
            }
        });
    }
}