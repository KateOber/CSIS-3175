package com.csis3175project.easymoney;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

public class AddExpense extends AppCompatActivity {
    EMDatabase databaseHelper;
    String category, name, date;
    private double amount;
    private int recurring;
    private DatePickerDialog.OnDateSetListener onDateSetListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);

        //sharedPreference
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String username = sharedPref.getString("username","");

        //initialize dbHelper
        databaseHelper = new EMDatabase(this);

        Spinner categorySpinner = findViewById(R.id.category);
        TextInputEditText amount_txt = findViewById(R.id.inputAmount);
        TextInputEditText name_txt = findViewById(R.id.inputExpenseName);
        TextInputEditText date_txt = findViewById(R.id.datePicker);
        Button btnAdd = findViewById(R.id.btn_addExpense);
        ImageView btnBack = findViewById(R.id.btnBackBills);
        CheckBox recurring_checkbox = findViewById(R.id.checkbox_recurExp);

        //footer buttons
        Button btnProfileFooter = findViewById(R.id.bigExUsTrackerFoot);
        Button btnExpenseTrackerFooter = findViewById(R.id.trackerBigExTrackerFoot);
        Button btnBigExpenseFooter = findViewById(R.id.bEBexTrackerFoot);
        Button btnReportFooter = findViewById(R.id.reportBexTrackerFoot);


        //setup date picker
        date_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        AddExpense.this, android.R.style.Theme_Black, onDateSetListener,
//Theme_Dark for diferent style
                        year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                datePickerDialog.show();
            }
        });

        //get date from the date picker
        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                date = "" + year + '/' + ++month + '/' + dayOfMonth;
                date_txt.setText(date.toString());

            }
        };



        //button listener
        btnAdd.setOnClickListener(new View.OnClickListener() {
            long expenseAdded;

            @Override
            public void onClick(View v) {
                category = categorySpinner.getSelectedItem().toString();

                if (recurring_checkbox.isChecked())
                    recurring = 1;
                else
                    recurring = 0;

                if(name_txt.getText().toString().isEmpty())
                   name_txt.setBackgroundColor(Color.parseColor("#20D81B60"));
                else
                    name_txt.setBackgroundColor(Color.parseColor("#E0E0E0"));
                if(amount_txt.getText().toString().isEmpty())
                    amount_txt.setBackgroundColor(Color.parseColor("#20D81B60"));
                else
                    amount_txt.setBackgroundColor(Color.parseColor("#E0E0E0"));
                if(date == null)
                    date_txt.setBackgroundColor(Color.parseColor("#20D81B60"));
                else
                    date_txt.setBackgroundColor(Color.parseColor("#E0E0E0"));
                if(category.equals("Category"))
                   categorySpinner.setBackgroundColor(Color.parseColor("#20D81B60"));
                else
                    categorySpinner.setBackgroundColor(Color.parseColor("#E0E0E0"));

                if(!name_txt.getText().toString().isEmpty() && !amount_txt.getText().toString().isEmpty() && date != null &&
                        !category.equals("Category")
                ) {
                    name = name_txt.getText().toString();
                    amount = Double.parseDouble(amount_txt.getText().toString());

                    expenseAdded = databaseHelper.insertEXPENSEData(username,
                            name, amount, date, category, recurring, 1);

                    //if SUCCESSFUL ADD RESET ALL INPUT FIELDS
                    if (expenseAdded != 0) {

                        name_txt.setText("");
                        amount_txt.setText("");
                        recurring_checkbox.setChecked(false);
                        categorySpinner.setPrompt("Expense Category");
                        date_txt.setText("Date of expense");
                        date = null;
                        categorySpinner.setSelection(0);
                        Toast.makeText(AddExpense.this,
                                "Expense Added", Toast.LENGTH_SHORT).show();
                    }
                    //ELSE DO SOME ERROR STUFF
                    else {
                    Toast.makeText(AddExpense.this,
                            "Error", Toast.LENGTH_SHORT).show();
                    }
                }
            }

        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //Footer Buttons
        btnExpenseTrackerFooter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddExpense.this, ExpenseTracker.class));
            }
        });
        btnReportFooter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddExpense.this, MainActivity.class));
            }
        });
        btnBigExpenseFooter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddExpense.this, BigExpense_setup.class));
            }
        });
        btnProfileFooter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddExpense.this, Profile.class));
            }
        });
    }
}
