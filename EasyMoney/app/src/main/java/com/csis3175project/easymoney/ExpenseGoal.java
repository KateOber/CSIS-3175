package com.csis3175project.easymoney;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.ProgressBar;

public class ExpenseGoal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_goal);

        ProgressBar progress_bar = findViewById(R.id.goalCircleOther);
        progress_bar.setProgress(50);
    }

}