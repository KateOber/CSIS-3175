package com.csis3175project.easymoney;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class expenseGoal2 extends AppCompatActivity {
    double TO, TD, TE, TG, TS, TT;
    EMDatabase database;
    Cursor expenses;
    Cursor income;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_goal2);

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String username = sharedPref.getString("username","");

        database = new EMDatabase(this);
        TO=0;TD=0;TE=0;TG=0;TS=0;TT=0;
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

        Button goal2btn_Back = findViewById(R.id.goal2BackBtn);
        Button goal2btn_Percent = findViewById(R.id.goal2PercentageBtn);

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

        TextView goal2textView_Other = findViewById(R.id.goal2TotalOther);
        TextView goal2textView_Dining = findViewById(R.id.goal2TotalDinning);
        TextView goal2textView_Entertainment = findViewById(R.id.goal2TotalEntertainment);
        TextView goal2textView_Transport = findViewById(R.id.goal2TotalTransport);
        TextView goal2textView_Groceries = findViewById(R.id.goal2TotalGroceries);
        TextView goal2textView_Shopping = findViewById(R.id.goal2TotalShopping);

        goal2textView_Other.setText("$"+TO);
        goal2textView_Dining.setText("$"+TD);
        goal2textView_Entertainment.setText("$"+TE);
        goal2textView_Transport.setText("$"+TT);
        goal2textView_Groceries.setText("$"+TG);
        goal2textView_Shopping.setText("$"+TS);

        goal2btn_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });

        goal2btn_Percent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(expenseGoal2.this, ExpenseGoal.class));

            }
        });
    }
}