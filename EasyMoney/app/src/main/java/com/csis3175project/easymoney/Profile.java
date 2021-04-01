package com.csis3175project.easymoney;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class Profile extends AppCompatActivity {
    EMDatabase databaseHelper;
    String email;
    StringBuilder name;
    Cursor income;
    Double savings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        //sharedPreference
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String username = sharedPref.getString("username","");

        //initialize dbHelper
        databaseHelper = new EMDatabase(this);
        savings = databaseHelper.getSavings(username);
        income = databaseHelper.getINCOMEData();
        double totalIncome=0;
        if(income.getCount()>0){
            while(income.moveToNext()){
                if(income.getString(1).equals(username)){
                    totalIncome += income.getDouble(3);
                }
            }
        }

        Button logoutBtn =  findViewById(R.id.profileLogOutbtn);
        Button gotoBills = findViewById(R.id.profileGoRecurringBtn);
        Button gotoGoals = findViewById(R.id.profileGoGoalBtn);

        TextView name_txt = findViewById(R.id.profileNametxt);
        TextView email_txt = findViewById(R.id.profileEmailtxt);
        TextView monthlyIncome = findViewById(R.id.profileMonthOutputtxt);
        monthlyIncome.setText("$"+totalIncome);
        TextView currentSavings = findViewById(R.id.profileSavingOutputtxt);
        currentSavings.setText("$"+savings);

        //footer buttons
        Button btnProfileFooter = findViewById(R.id.userProfileFoot);
        Button btnExpenseTrackerFooter = findViewById(R.id.trackerProfileFoot);
        Button btnBigExpenseFooter = findViewById(R.id.bEProfileFoot);
        Button btnReportFooter = findViewById(R.id.reportProfileFoot);

        email = databaseHelper.getEmail(username);

        name_txt.setText(username);
        email_txt.setText(email);

        //Footer Buttons
        btnExpenseTrackerFooter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Profile.this, ExpenseTracker.class));
            }
        });
        btnReportFooter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Profile.this, MainActivity.class));
            }
        });
        btnBigExpenseFooter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Profile.this, BigExpense_setup.class));
            }
        });
        btnProfileFooter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Profile.this, Profile.class));
            }
        });
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        gotoBills.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Profile.this, RecurringBillsCalendar.class));
            }
        });
        gotoGoals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Profile.this, ExpenseGoal.class));
            }
        });
    }
}