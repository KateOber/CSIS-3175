package com.csis3175project.easymoney;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;

//TODO:
//Setup error messages
//get logged in user
//
public class AddExpense extends AppCompatActivity {
    EMDatabase databaseHelper;
    String category, name, date;
    private double amount;
    private int recurring;
    private DatePickerDialog.OnDateSetListener onDateSetListener;

/*       " ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+USERNAME+" VARCHAR(255) ,"+ ENAME +" VARCHAR(255) ,"+ AMOUNT +" REAL ,"+ DATE +" TEXT ,"+
    CATEGORY +" VARCHAR(255) ,"+ RECURRING +" INTEGER ,"+ REPEAT +" INTEGER);";*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);

        //initialize dbHelper
        databaseHelper = new EMDatabase(this);

        Spinner categorySpinner = findViewById(R.id.category);
        TextInputLayout amount_txt = findViewById(R.id.amount);
        TextInputLayout name_txt = findViewById(R.id.expenseName);
        TextView date_txt = findViewById(R.id.date);
        Button btnAdd = findViewById(R.id.btn_addExpense);
        ImageView btnBack = findViewById(R.id.btnBack);
        CheckBox recurring_checkbox = findViewById(R.id.checkbox_recurExp);

        //footer buttons
        Button btnProfileFooter = findViewById(R.id.bEProfileFoot);
        Button btnExpenseTrackerFooter = findViewById(R.id.trackerTrackerFoot);
        Button btnBigExpenseFooter = findViewById(R.id.bETrackerFoot);
        Button btnReportFooter = findViewById(R.id.reportFoot);

        //setup date picker
        date_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        AddExpense.this, android.R.style.Theme_DeviceDefault, onDateSetListener,
                        year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        //get date from the date picker
        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                date = "" + year + '/' + month + '/' + dayOfMonth;

            }
        };

        //button listener
        btnAdd.setOnClickListener(new View.OnClickListener() {
            long expenseAdded;

            @Override
            public void onClick(View v) {
               // name = name_txt.getText().toString();
                category = categorySpinner.toString();
               // amount = Double.parseDouble(amount_txt.getText().toString());
                if (recurring_checkbox.isChecked())
                    recurring = 1;
                else
                    recurring = 0;

                expenseAdded = databaseHelper.insertEXPENSEData("user",
                        name, amount, date, category, recurring, 1);

                //if SUCCESSFUL ADD RESET ALL INPUT FIELDS
                if(expenseAdded != 0){
                Toast.makeText(AddExpense.this,
                        "Expense Added", Toast.LENGTH_SHORT).show();
                //name_txt.setText("");
                //amount_txt.setText("");
                recurring_checkbox.setChecked(false);
                categorySpinner.setPrompt("Expense Category");
                date_txt.setText("");
                }
                //ELSE DO SOME ERROR STUFF
            }

        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddExpense.this, ExpenseTracker.class));
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
                startActivity(new Intent(AddExpense.this, MainActivity.class));
            }
        });
        btnProfileFooter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddExpense.this, Profile.class));
            }
        });
 /*       List<String> categories = new ArrayList<String>();
        categories.add("Entertainment");
        categories.add("Groceries");
        categories.add("Transport");
        categories.add("Shopping");
        categories.add("Dining");
        categories.add("Education");
        categories.add("Other");
        }*/
        // Creating adapter for spinner
        //ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
    }
}