package com.csis3175project.easymoney;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

public class AddIncome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_income);

        Button addIncomebtn = findViewById(R.id.addIncomebtn);
        Button addIncomeBackbtn = findViewById(R.id.addIncomeBackbtn);
        TextInputEditText addIncomeDate = findViewById(R.id.IncomenameInput);
        TextInputEditText addIncomeAmmount = findViewById(R.id.Incomeammount);
        TextInputEditText addIncomeName = findViewById(R.id.Incomename);

        addIncomeBackbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });

        addIncomebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddIncome.this, ExpenseTracker.class));

            }
        });
    }
}