package com.cmput301f16t16.hitchhiker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void GoToMainPage(View view){
        Intent intent = new Intent(LoginActivity.this, RiderActivity.class);
        startActivity(intent);
        finish();
    }

    public void GoToRegisterPage(View view){
        // TODO get user from Elastic Search

        // TODO If the user is a rider, then go to the rider page

        // Todo If the user is a driver, then go to the driver page
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);

    }
}
