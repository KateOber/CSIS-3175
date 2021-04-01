package com.csis3175project.easymoney;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputEditText;

public class RegisterActivity extends AppCompatActivity {
    EMDatabase myhelper;

    private String inputUserName = null;
    private String inputUserEmail = null;
    private String inputUserPassword = null;
    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        myhelper = new EMDatabase(this);

        TextInputEditText userName = findViewById(R.id.inputName);
        TextInputEditText userEmail = findViewById(R.id.inputEmail);
        TextInputEditText userPassword = findViewById(R.id.inputPassword);

        userName.setFocusableInTouchMode(true);
        userEmail.setFocusableInTouchMode(true);
        userPassword.setFocusableInTouchMode(true);

        AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);

        Button btnSignUp = findViewById(R.id.btnSignUp);
        TextView btnGoSignIn = findViewById(R.id.btnGoSignIn);
        ImageView btnBack = findViewById(R.id.btnBack);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                inputUserName = userName.getText().toString();
                inputUserEmail = userEmail.getText().toString();
                inputUserPassword = userPassword.getText().toString();

                if (inputUserName.equals("")) {
                    dialog = builder.setMessage("Please Enter user name").setPositiveButton("OK", null).create();
                    dialog.show();
                    userName.requestFocus();
                    return;
                }

                if (inputUserEmail.equals("")) {
                    dialog = builder.setMessage("Please Enter user name").setPositiveButton("OK", null).create();
                        userEmail.requestFocus();
                        return;
                }
                if (inputUserPassword.equals("")) {
                    dialog = builder.setMessage("Please Enter password").setPositiveButton("OK", null).create();
                        userPassword.requestFocus();
                        return;
                }

                if (inputUserName != null && inputUserEmail != null && inputUserPassword != null) {
                    myhelper.insertUSERData(inputUserName, inputUserEmail, inputUserPassword, 0);
                    Toast.makeText(RegisterActivity.this,
                            "Has been successfully registered", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                }
            }
        });

        btnGoSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
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