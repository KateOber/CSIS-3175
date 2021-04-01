package com.csis3175project.easymoney;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.TextView;

//TODO: implement the logic:
// 1. first to option substract from expence and saving
// 3. third lead to BigEpence_Loan activity
public class BigExpenses_main extends AppCompatActivity {
    EMDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_expenses);
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String username = sharedPref.getString("username","");
        String category = sharedPref.getString("cat", "");
        int period = sharedPref.getInt("Period", 0);
        String stringAmount = sharedPref.getString("cost", "");
        double amount = Double.parseDouble(stringAmount);
        String formatPerMonth;
        final double[] perMonth = {0};
        database = new EMDatabase(this);
        TextView option1Amount = findViewById(R.id.txtAmount1);
        TextView option2Amount = findViewById(R.id.txtAmount2);
        TextView option3Amount = findViewById(R.id.txtAmount3);
        TextView totaltxt = findViewById(R.id.txtEnteredExp);
        TextView periodtxt = findViewById(R.id.txtEntBExpPeriod);
        TextView cattxt = findViewById(R.id.txtEntBExpCat);

        ConstraintLayout option1Card = findViewById(R.id.option1BigExp);
        ConstraintLayout option2Card = findViewById(R.id.option2BigExp);
        ConstraintLayout option3Card = findViewById(R.id.option3BigExp);

        totaltxt.setText("$"+amount);
        periodtxt.setText("During "+period+" months");
        cattxt.setText("In "+category);
        perMonth[0] = amount/period;
        formatPerMonth = String.format("%.2f", perMonth[0]);
        option1Amount.setText("$"+formatPerMonth);
        option2Amount.setText("$"+formatPerMonth);
        option3Amount.setText("$"+formatPerMonth);

        option1Card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                perMonth[0] = amount/period;
                startActivity(new Intent(BigExpenses_main.this, ExpenseTracker.class));
                database.insertEXPENSEData(username, "Big Expense", perMonth[0], "2021/4/01", category, 1, period);
            }
        });
        option2Card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                perMonth[0] = amount/period;
                double savings = database.getSavings(username);
                database.updateSavings(savings-perMonth[0], username);
                startActivity(new Intent(BigExpenses_main.this, ExpenseTracker.class));
            }
        });
        option3Card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BigExpenses_main.this, BigExpenses_loan.class));
            }
        });
    }
}