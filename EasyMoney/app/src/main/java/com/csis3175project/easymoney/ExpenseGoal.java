package com.csis3175project.easymoney;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;

import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ExpenseGoal extends AppCompatActivity {
    int PO, PD, PE, PT, PG, PS;
    double TO, TD, TE, TT, TG, TS, TotalIncome, allowedExpense, DO, DD, DE, DT, DG, DS;
    Cursor expenses;
    Cursor income;
    EMDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_goal);
        database = new EMDatabase(this);

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String username = sharedPref.getString("username","");

        PO=0; PD=0; PE=0; PT=0; PG=0; PS=0;
        expenses = database.getEXPENSEData();
        income = database.getINCOMEData();
        double totalIncome=0;
        if(income.getCount()>0){
            while(income.moveToNext()){
                if(income.getString(1).equals(username)){
                    totalIncome += income.getDouble(3);
                }
            }
        }
        allowedExpense = totalIncome/6;

        String expenseCat;
        double expenseAmmount;
        if(expenses.getCount()>0){
            while(expenses.moveToNext()){
                if(expenses.getString(1).equals(username)){
                    expenseCat = expenses.getString(5);
                    expenseAmmount = expenses.getDouble(3);
                    if(expenseCat.equals("Dining")){
                        TD += expenseAmmount;
                    }
                    else if(expenseCat.equals("Entertainment")){
                        TE += expenseAmmount;
                    }
                    else if(expenseCat.equals("Groceries")){
                        TG += expenseAmmount;
                    }
                    else if(expenseCat.equals("Shopping")){
                        TS += expenseAmmount;
                    }
                    else if(expenseCat.equals("Transport")){
                        TT += expenseAmmount;
                    }
                    else if(expenseCat.equals("Other")){
                        TO += expenseAmmount;
                    }
                    else;
                }
            }
        }
        DD = TD/allowedExpense*100;
        PD = (int) DD;
        if(PD > 100) PD=100;
        DE = TE/allowedExpense*100;
        PE = (int) DE;
        if(PE > 100) PE=100;
        DG = TG/allowedExpense*100;
        PG = (int) DG;
        if(PG > 100) PG=100;
        DS = TS/allowedExpense*100;
        PS = (int) DS;
        if(PS > 100) PS=100;
        DT = TT/allowedExpense*100;
        PT = (int) DT;
        if(PT > 100) PT=100;
        DO = TO/allowedExpense*100;
        PO = (int) DO;
        if(PO > 100) PO=100;
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