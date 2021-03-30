package com.csis3175project.easymoney;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class Splash extends AppCompatActivity {
    EMDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        database = new EMDatabase(this);
        /*database.insertUSERData("bernardoasc", "bernardoasc@gmail.com", "password", 1);
        database.insertMISCData("bernardoasc", 45.98, 1050.67);
        database.insertINCOMEData("bernardo", "Main", 475.64, "Main");
        database.insertEXPENSEData("bernardoasc", "A&W", 24.99, "3/29/2021", "dining", 0, 0);*/
        TimerTask task = new TimerTask() {

            public void run() {
                finish();
                startActivity(new Intent(Splash.this, WelcomeActivity.class));
            }
        };

        Timer opening = new Timer();
        opening.schedule(task,5000);

    }
}