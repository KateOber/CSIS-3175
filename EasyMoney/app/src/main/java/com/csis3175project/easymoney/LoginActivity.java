package com.csis3175project.easymoney;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;


public class LoginActivity extends AppCompatActivity {
    EMDatabase myhelper;
    private String inputUserName;
    private String inputPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        myhelper = new EMDatabase(this);

//        EditText testUserName = findViewById(R.id.userName);
//        TextInputEditText testPassword = findViewById(R.id.userPassword);

        TextInputLayout testUserName = findViewById(R.id.userName);
        TextInputLayout testPassword = findViewById(R.id.userPassword);

        TextInputEditText

        ImageView btnBack = findViewById(R.id.btnBack);
        Button btnLogin = findViewById(R.id.btnSignUp);

        TextView btnSignUp = findViewById(R.id.btnGoSignUpWelcome);

//        btnLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                inputUserName = testUserName.getText().toString();
//                inputPassword = testPassword.getText().toString();
//                boolean ischecked =
//                        myhelper.checkLogin(inputUserName, inputPassword);
//
//                if(ischecked) {
////                    startActivity(new Intent(LoginActivity,this. --put the name of screen--.class)
//                    Toast.makeText(LoginActivity.this, "Valid user", Toast.LENGTH_LONG).show();
//                } else {
//                    Toast.makeText(LoginActivity.this, "Invalid user", Toast.LENGTH_LONG).show();
//                }
//            }
//        });
    }
}