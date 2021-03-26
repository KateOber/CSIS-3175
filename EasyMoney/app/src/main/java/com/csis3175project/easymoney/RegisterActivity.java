package com.csis3175project.easymoney;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class RegisterActivity extends AppCompatActivity {
    EMDatabase myhelper;

    private String inputUserName;
    private String inputUserEmail;
    private String inputUsePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        myhelper = new EMDatabase(this);

        EditText userName = findViewById(R.id.userName);
        EditText userEmail = findViewById(R.id.userEmail);
        EditText userPassword = findViewById(R.id.userPassword);

        Button btnSignUp = findViewById(R.id.btnSignUp);
        TextView btnGoSignIn = findViewById(R.id.btnGoSignUp);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                inputUserName = userName.getText().toString();
                inputUserEmail = userEmail.getText().toString();
                inputUsePassword = userPassword.getText().toString();

                if(inputUserName ==null && inputUserEmail==null && inputUsePassword ==null) {
                    myhelper.insertUSERData(inputUserName, inputUserEmail, inputUsePassword, 0);
                    Toast.makeText(RegisterActivity.this, "Register succeeded", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(RegisterActivity.this, "Register failed", Toast.LENGTH_LONG).show();
                }

            }
        });

    }
}