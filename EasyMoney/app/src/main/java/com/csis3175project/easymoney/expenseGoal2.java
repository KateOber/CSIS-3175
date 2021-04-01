package com.csis3175project.easymoney;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class expenseGoal2 extends AppCompatActivity {
    double TO, TD, TE, TG, TS, TT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_goal2);

        TO=0;TD=0;TE=0;TG=0;TS=0;TT=0;
        Button goal2btn_Back = findViewById(R.id.goal2BackBtn);
        Button goal2btn_Percent = findViewById(R.id.goal2PercentageBtn);

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