package com.cmput301f16t16.hitchhiker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
<<<<<<< HEAD
import android.widget.EditText;
=======
>>>>>>> 1b56c156b5b8a7a5e64f53b7b252e6830816ea1d

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

<<<<<<< HEAD
    public void GoToRegisterPage(View view) {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    public void GoToMainPage(View view) {
        // TODO If the User's username doesn't exist, then throw the exception
        // TODO If the inputted password is incorrect, then throw an exception

        // TODO If the user is a rider, then go to the rider page
        EditText userNameText = (EditText) findViewById(R.id.userNameText);
        EditText userPasswordText = (EditText) findViewById(R.id.userPasswordText);

        String userName = userNameText.getText().toString();
        String userPassword = userPasswordText.getText().toString();

        ElasticsearchUserController.GetUsersTask getUsersTask = new ElasticsearchUserController.GetUsersTask();
        getUsersTask.execute(userName);


        // TODO If the user is a driver, then go to the driver page

        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
    }

=======
    public void GoToMainPage(View view){
        Intent intent = new Intent(LoginActivity.this, RiderActivity.class);
        startActivity(intent);
        finish();
    }

    public void GoToRegisterPage(View view){
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);

    }
>>>>>>> 1b56c156b5b8a7a5e64f53b7b252e6830816ea1d
}
