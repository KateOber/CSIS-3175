package com.csis3175project.easymoney;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputEditText;
import java.io.*;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    EMDatabase myhelper;

    private String inputUserName;
    private String inputUserEmail;
    private String inputUserPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        myhelper = new EMDatabase(this);

        TextInputEditText userName = findViewById(R.id.inputName);
        TextInputEditText userEmail = findViewById(R.id.inputEmail);
        TextInputEditText userPassword = findViewById(R.id.inputPassword);

        Button btnSignUp = findViewById(R.id.btnSignUp);
        TextView btnGoSignIn = findViewById(R.id.btnGoSignUpWelcome);
        ImageView btnBack = findViewById(R.id.btnBack);

//        btnSignUp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                inputUserName = userName.getText().toString();
//                inputUserEmail = userEmail.getText().toString();
//                inputUserPassword = userPassword.getText().toString();
//
//                if(!(inputUserName ==null && inputUserEmail==null && inputUserPassword ==null)) {
//                    myhelper.insertUSERData(inputUserName, inputUserEmail, inputUserPassword, 0);
//                    System.out.println("s"); //test
//                    Toast.makeText(RegisterActivity.this,
//                            "Has been successfully registered", Toast.LENGTH_SHORT).show();
//                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
//                }
//
//                if(inputUserName.length() ==0){
//                    Toast.makeText(RegisterActivity.this, "Please Enter user name", Toast.LENGTH_SHORT).show();
//                    userEmail.requestFocus();
//                    return;
//                }
//                if(inputUserEmail.length() ==0){
//                    Toast.makeText(RegisterActivity.this, "Please Enter your Email", Toast.LENGTH_SHORT).show();
//                }
//                if(inputUserPassword.length()==0){
//                    Toast.makeText(RegisterActivity.this, "Please Enter password", Toast.LENGTH_SHORT).show();
//                }
//
//            }
//        });
//        btnGoSignIn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
//            }
//        });
//        btnBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onBackPressed();
//            }
//        });

    }

    @Override
    public void onClick(View v) {
        //btn Sign up
        switch (v.getId()) {
            case R.id.btnSignUp: {

                TextInputEditText userName = findViewById(R.id.inputName);
                TextInputEditText userEmail = findViewById(R.id.inputEmail);
                TextInputEditText userPassword = findViewById(R.id.inputPassword);

                inputUserName = userName.getText().toString();
                inputUserEmail = userEmail.getText().toString();
                inputUserPassword = userPassword.getText().toString();

                if (!(inputUserName == null && inputUserEmail == null && inputUserPassword == null)) {
                    myhelper.insertUSERData(inputUserName, inputUserEmail, inputUserPassword, 0);
                    System.out.println("s"); //test
                    Toast.makeText(RegisterActivity.this,
                            "Has been successfully registered", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                }

                if (inputUserName.length() == 0) {
                    Toast.makeText(RegisterActivity.this, "Please Enter user name", Toast.LENGTH_SHORT).show();
                    userName.requestFocus();
                    return;
                }
                if (inputUserEmail.length() == 0) {
                    Toast.makeText(RegisterActivity.this,
                            "Please Enter your Email", Toast.LENGTH_SHORT).show();
                    userEmail.requestFocus();
                }
                if (inputUserPassword.length() == 0) {
                    Toast.makeText(RegisterActivity.this,
                            "Please Enter password", Toast.LENGTH_SHORT).show();
                    userPassword.requestFocus();
                }
                break;
            }

            case R.id.btnGoSignIn:{
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                break;
            }
            case R.id.btnBack:{
                onBackPressed();
            }

        }
    }
}