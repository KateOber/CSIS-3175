package com.csis3175project.easymoney;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.android.material.textfield.TextInputEditText;

public class AddExpense extends AppCompatActivity {
    String[] cat = {"item 1", "item 2", "item 3"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);

        Button addExpensebtn = findViewById(R.id.addExpensebtn);
        Button addExpenseBackbtn = findViewById(R.id.addExpenseBackbtn);
        TextInputEditText addExpenseDate = findViewById(R.id.expensenameInput);
        TextInputEditText addExpenseAmmount = findViewById(R.id.expenseammount);
        TextInputEditText addExpenseName = findViewById(R.id.expensename);

        addExpenseBackbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });

        addExpensebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddExpense.this, ExpenseTracker.class));

            }
        });

    }
}