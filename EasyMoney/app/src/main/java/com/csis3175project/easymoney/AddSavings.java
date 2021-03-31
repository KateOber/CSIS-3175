package com.csis3175project.easymoney;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

public class AddSavings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_savings);

        Button addSavingsbtn = findViewById(R.id.addSavingsbtn);
        Button addSavingsBackbtn = findViewById(R.id.addSavingsBackbtn);
        TextInputEditText addSavingsDate = findViewById(R.id.SavingsnameInput);
        TextInputEditText addSavingsAmmount = findViewById(R.id.Savingsammount);
        TextInputEditText addSavingsName = findViewById(R.id.Savingsname);

        addSavingsBackbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });

        addSavingsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddSavings.this, ExpenseTracker.class));

            }
        });
    }
}