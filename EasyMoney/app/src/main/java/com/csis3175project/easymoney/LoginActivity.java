package com.csis3175project.easymoney;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EMDatabase myhelper;
    private String inputUserName;
    private String inputPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        myhelper = new EMDatabase(this);

        EditText userName = findViewById(R.id.userName);
        EditText userPassword = findViewById(R.id.userPassword);

        ImageView btnBack = findViewById(R.id.btnBack);
        Button btnLogin = findViewById(R.id.btnSignUp);

        TextView btnSignUp = findViewById(R.id.btnGoSignUp);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                inputUserName = userName.getText().toString();
                inputPassword = userPassword.getText().toString();
                boolean ischecked =
                        myhelper.checkLogin(inputUserName, inputPassword);

                if(ischecked) {
//                    startActivity(new Intent(LoginActivity,this. --put the name of screen--.class)
                    Toast.makeText(LoginActivity.this, "Valid user", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(LoginActivity.this, "Invalid user", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}