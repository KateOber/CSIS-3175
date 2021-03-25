package com.csis3175project.easymoney;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.csis3175project.easymoney.ui.login.LoginActivity;

import java.util.Timer;
import java.util.TimerTask;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

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