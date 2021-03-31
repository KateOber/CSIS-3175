package com.csis3175project.easymoney;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ExpenseGoal extends AppCompatActivity {
    int PO, PD, PE, PT, PG, PS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_goal);

        PO=0; PD=0; PE=0; PT=0; PG=0; PS=0;

        ProgressBar goalprogress_bar_other = findViewById(R.id.goalCircleOther);
        ProgressBar goalprogress_bar_dining = findViewById(R.id.goalCircleDining);
        ProgressBar goalprogress_bar_entertainment = findViewById(R.id.goalCircleEntertainment);
        ProgressBar goalprogress_bar_transport = findViewById(R.id.goalCircleTransport);
        ProgressBar goalprogress_bar_groceries = findViewById(R.id.goalCircleGroceries);
        ProgressBar goalprogress_bar_shopping = findViewById(R.id.goalCircleShopping);

        TextView goaltextView_Other = findViewById(R.id.goalPercentageOther);
        TextView goaltextView_Dining = findViewById(R.id.goalPercentageDining);
        TextView goaltextView_Entertainment = findViewById(R.id.goalPercentageEntertainment);
        TextView goaltextView_Transport = findViewById(R.id.goalPercentageTransport);
        TextView goaltextView_Groceries = findViewById(R.id.goalPercentageGroceries);
        TextView goaltextView_Shopping = findViewById(R.id.goalPercentageShopping);

        Button goalbtn_Back = findViewById(R.id.goal1BackBtn);
        Button goalbtn_List = findViewById(R.id.goal1ListButton);

        goalprogress_bar_other.setProgress(PO);
        goalprogress_bar_dining.setProgress(PD);
        goalprogress_bar_entertainment.setProgress(PE);
        goalprogress_bar_transport.setProgress(PT);
        goalprogress_bar_groceries.setProgress(PG);
        goalprogress_bar_shopping.setProgress(PS);

        goaltextView_Other.setText(PO+"%");
        goaltextView_Dining.setText(PD+"%");
        goaltextView_Entertainment.setText(PE+"%");
        goaltextView_Transport.setText(PT+"%");
        goaltextView_Groceries.setText(PG+"%");
        goaltextView_Shopping.setText(PS+"%");

        goalbtn_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });

        goalbtn_List.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ExpenseGoal.this, expenseGoal2.class));

            }
        });
    }

}