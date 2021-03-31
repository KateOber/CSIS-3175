package com.csis3175project.easymoney;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
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
    Double income,savings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        //sharedPreference
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String username = sharedPref.getString("username","");

        //initialize dbHelper
        databaseHelper = new EMDatabase(this);

        ConstraintLayout logoutCart = findViewById(R.id.profileLogOutcard);
        Button logoutBtn =  findViewById(R.id.profileLogOutBtn);
        Button editMontlyIncome = findViewById(R.id.profileEditIncomebtn);
        Button editCurrSavings = findViewById(R.id.profileEditSavingbtn);
        ConstraintLayout editExpenseGoal = findViewById(R.id.profileExpenseGoalcard);
        ConstraintLayout editRecurringBills = findViewById(R.id.profileRecBillcard);
        Button editProfile = findViewById(R.id.editProfileBtn);

        TextView name_txt = findViewById(R.id.profileNametxt);
        TextView email_txt = findViewById(R.id.profileEmailtxt);
        TextView monthlyIncome = findViewById(R.id.profileMonthOutputtxt);
        TextView currentSavings = findViewById(R.id.profileSavingOutputtxt);

        //footer buttons
        Button btnProfileFooter = findViewById(R.id.userTrackerFoot);
        Button btnExpenseTrackerFooter = findViewById(R.id.trackerTrackerFoot);
        Button btnBigExpenseFooter = findViewById(R.id.bETrackerFoot);
        Button btnReportFooter = findViewById(R.id.reportTrackerFoot);

        name = databaseHelper.getEName(username);
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
                startActivity(new Intent(Profile.this, MainActivity.class));
            }
        });
        btnProfileFooter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Profile.this, Profile.class));
            }
        });
    }
}