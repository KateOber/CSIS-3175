package com.csis3175project.easymoney;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;


public class LoginActivity extends AppCompatActivity {
    EMDatabase myhelper;
    private String inputUserName;
    private String inputUserPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        myhelper = new EMDatabase(this);

        TextInputEditText password = findViewById(R.id.inputPassword);
        TextInputEditText userName = findViewById(R.id.inputUserName);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();


        ImageView btnBack = findViewById(R.id.btnBack);
        Button btnLogin = findViewById(R.id.btnLogin);

        TextView btnSignUp = findViewById(R.id.btnGoSignUpWelcome);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myhelper.insertUSERData("username", "username@gmail.com", "password", 1);
                myhelper.insertEXPENSEData("username", "DQ", 19.99, "3/31/2021", "Dining", 0, 0);
                inputUserName = userName.getText().toString();
                inputUserPassword = password.getText().toString();

                System.out.println(inputUserName + " : " + inputUserPassword);//test

                boolean ischecked =
                        myhelper.checkLogin(inputUserName, inputUserPassword);


                if(ischecked) {

                    if(myhelper.getAdmin(inputUserName) == 1){
                        startActivity(new Intent(LoginActivity.this,UpdatePassword.class));
                    }

                    Toast.makeText(LoginActivity.this, "Welcome, " + inputUserName, Toast.LENGTH_LONG).show();
                    editor.putString("username", inputUserName);
                    editor.commit();
                    startActivity(new Intent(LoginActivity.this, ExpenseTracker.class));

                } else {
                    Toast.makeText(LoginActivity.this, "Please check username or password again", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}