package com.csis3175project.easymoney;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
<<<<<<< HEAD
import android.widget.ImageView;
=======
>>>>>>> parent of 100111f (Merge branch 'main' of https://github.com/Koberpunk/CSIS-3175 into main)
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class AddSavings extends AppCompatActivity {

    EMDatabase databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_savings);

        //sharedPreference
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String username = sharedPref.getString("username","");

        //initialize dbHelper
        databaseHelper = new EMDatabase(this);

        Button addSavingsbtn = findViewById(R.id.addSavingsbtn);
<<<<<<< HEAD
        ImageView addSavingsBackbtn = findViewById(R.id.btnBack);
=======
        Button addSavingsBackbtn = findViewById(R.id.addSavingsBackbtn);
>>>>>>> parent of 100111f (Merge branch 'main' of https://github.com/Koberpunk/CSIS-3175 into main)
        TextInputEditText savingsAmount_txt = findViewById(R.id.SavingsammountInput);

        addSavingsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(savingsAmount_txt.getText().toString().isEmpty())
                    savingsAmount_txt.setBackgroundColor(Color.parseColor("#20D81B60"));
                else
                    {
                        savingsAmount_txt.setBackgroundColor(Color.parseColor("#E0E0E0"));
                        double amount = Double.parseDouble(savingsAmount_txt.getText().toString());
                        double currentSavings = databaseHelper.getSavings(username);
                        boolean savingsUpdated = databaseHelper.updateSavings(currentSavings+amount, username);

                        if(savingsUpdated)
                        Toast.makeText(AddSavings.this,
                                "Saving Added", Toast.LENGTH_SHORT).show();

                        else
                            Toast.makeText(AddSavings.this,
                            "Error", Toast.LENGTH_SHORT).show();
                    }
            }
        });

        addSavingsBackbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
<<<<<<< HEAD
                onBackPressed();
            }
        });
    }
}

=======
                startActivity(new Intent(AddSavings.this, ExpenseTracker.class));
            }
        });
    }
}
>>>>>>> parent of 100111f (Merge branch 'main' of https://github.com/Koberpunk/CSIS-3175 into main)
