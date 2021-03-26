package com.csis3175project.easymoney;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity {
    EMDatabase myhelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        myhelper = new EMDatabase(this);

        EditText userName = findViewById(R.id.userName);
        EditText userPassword = findViewById(R.id.userPassword);

        ImageView btnBack = findViewById(R.id.btnBack);
        Button btnLogin = findViewById(R.id.btnLogin);

        TextView btnSignUp = findViewById(R.id.btnGoSignUp);








    }
}