package com.csis3175project.easymoney;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
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
        EditText groupPeriod = findViewById(R.id.BEPeriod);
        EditText cost = findViewById(R.id.editTextNumber2);

        Button button = findViewById(R.id.btnBigEx);


        //footer buttons
        Button btnProfileFooter = findViewById(R.id.bigExUsTrackerFoot);
        Button btnExpenseTrackerFooter = findViewById(R.id.trackerBigExTrackerFoot);
        Button btnBigExpenseFooter = findViewById(R.id.bEBexTrackerFoot);
        Button btnReportFooter = findViewById(R.id.reportBexTrackerFoot);
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPref.edit();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    categoriesExpense = groupCat.getSelectedItem().toString();

                    if(categoriesExpense.equals("Category"))
                        groupCat.setBackgroundColor(Color.parseColor("#20D81B60"));
                    else
                        groupCat.setBackgroundColor(Color.parseColor("#E0E0E0"));
                    if(cost.getText().toString().isEmpty())
                        cost.setBackgroundColor(Color.parseColor("#20D81B60"));
                    else
                        cost.setBackgroundColor(Color.parseColor("#E0E0E0"));
                    if(groupPeriod.getText().toString().isEmpty())
                        groupPeriod.setBackgroundColor(Color.parseColor("#20D81B60"));
                    else
                        groupPeriod.setBackgroundColor(Color.parseColor("#E0E0E0"));

                    if(!cost.getText().toString().isEmpty() && !groupPeriod.getText().toString().isEmpty()  &&
                            !categoriesExpense.equals("Category")) {
                        costOfExpense = Double.parseDouble(cost.getText().toString());
                        expensePeriod = Integer.parseInt(groupPeriod.getText().toString());


                        editor.putString("cost", Double.toString(costOfExpense));
                        editor.putInt("period", expensePeriod);
                        editor.putString("cat", categoriesExpense);
                        editor.commit();
                        startActivity(new Intent(BigExpense_setup.this, BigExpenses_main.class));
                    }
                    else
                        Message.message(BigExpense_setup.this, "Error");
                } catch (NumberFormatException ex) {
                    Message.message(BigExpense_setup.this, "Error");
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
                startActivity(new Intent(BigExpense_setup.this, MonthlyReport.class));
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