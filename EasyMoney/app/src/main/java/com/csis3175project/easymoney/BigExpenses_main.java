package com.csis3175project.easymoney;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;


//TODO: implement the logic:
// 1. first to option substract from expence and saving
// 3. third lead to BigEpence_Loan activity
public class BigExpenses_main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_expenses);

        TextView result = findViewById(R.id.txtEnteredExp);
        TextView cutDailyExp = findViewById(R.id.txtAmountDaily);
        TextView cutSavingsAm = findViewById(R.id.txtCutSavings);
        TextView getLoanAm = findViewById(R.id.txtGetLoanAmount);

        TextView category = findViewById(R.id.txtEntBExpCat);
        TextView period = findViewById(R.id.txtEntBExpPeriod);

        Intent intent = getIntent();
        if(intent != null) {
            double data = getIntent().getDoubleExtra("cost", 0);
            result.setText("$ "+ data);
            cutSavingsAm.setText("$ "+ data);
            getLoanAm.setText("$ "+ data);
            String cat = getIntent().getStringExtra("category");
            category.setText(cat + " ");
            String per = getIntent().getStringExtra("period");
            period.setText(per + " ");
            
            double dailyexp = 0;
            switch (per) {
                case "Day":
                    dailyexp = data;
                    break;
                case "Week":
                    dailyexp = data / 7 ;
                    break;
                case "Month":
                    dailyexp = data / 30 ;
                    break;
                case "Year":
                    dailyexp = data / 365;
                    break;
            }
            cutDailyExp.setText("$ "+ dailyexp);
        }
    }
}
