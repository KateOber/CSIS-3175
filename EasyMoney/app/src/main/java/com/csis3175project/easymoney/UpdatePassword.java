package com.csis3175project.easymoney;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class UpdatePassword extends AppCompatActivity {

    EMDatabase myhelper;
    String inputNewPassword  = null;
    String oldPassword = null;
    String currentUserName = null;
    int code = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_password);

        myhelper = new EMDatabase(this);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String userName = preferences.getString("username","");


        TextInputEditText newPassword = findViewById(R.id.inputNewPassword);
        Button btnUpdatePassword = findViewById(R.id.btnSignUp);
        ImageView btnBack = findViewById(R.id.btnBackBills);

       btnUpdatePassword.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               inputNewPassword = newPassword.getText().toString();
               oldPassword = myhelper.getPassword(userName);
               currentUserName = userName;

               boolean isUpdated = myhelper.updatePassword(oldPassword,inputNewPassword,currentUserName);

               if(isUpdated){

                   Toast.makeText(UpdatePassword.this,
                           "Password has been successfully updated", Toast.LENGTH_LONG).show();
                   onBackPressed();
                   myhelper.updateAACCESS(0, userName);

               } else{
                   Toast.makeText(UpdatePassword.this,
                           "something went wrong", Toast.LENGTH_LONG).show();

               }

           }
       });



    }
}