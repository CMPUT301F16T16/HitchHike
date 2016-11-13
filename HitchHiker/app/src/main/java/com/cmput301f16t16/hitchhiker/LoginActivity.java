package com.cmput301f16t16.hitchhiker;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    private int profession;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void GoToMainPage(View view){

        profession = determineUser();
        try {
            // return the user object in which the username corresponds to
            // If the user is a rider, then go to the rider page
            if (profession == 1) {

                Intent intent = new Intent(LoginActivity.this, RiderActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
                finish();
            }

            // If the user is a driver, then go to the driver page
            if (profession == 2 || profession == 3) {
                Intent intent = new Intent(LoginActivity.this, DriverActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
                finish();
            }

        }
        // If username does not exist, show text: The username you have entered does not exist
        catch (Exception e) {
            TextView userDoesNotExistText = (TextView) findViewById(R.id.userDoesNotExistText);
            userDoesNotExistText.setText("The username you have entered does not exist.");
            userDoesNotExistText.setTextColor(Color.RED);
            Log.i("Error", "Failed to get the user out of the async object");
        }


    }

    public int determineUser() {
        int type;
        // Get username the user has inputted.
        EditText userNameText = (EditText) findViewById(R.id.userNameText);
        String userName = userNameText.getText().toString();

        // Goes into elasticsearch to try and find if the username exists
        try {
            ElasticsearchUserController.GetUserTask getUserTask = new ElasticsearchUserController.GetUserTask();
            getUserTask.execute(userName);
            user = getUserTask.get();
//                    user = (User) getIntent().getSerializableExtra("user");
            user.setUserName(userName);
            type = user.getUserType();
            }

        catch (Exception e) {
            Log.i("Error","Elastic search didnt not return a User");
            TextView userDoesNotExistText = (TextView) findViewById(R.id.userDoesNotExistText);
            userDoesNotExistText.setText("The username you have entered can not be grabbed.");
            userDoesNotExistText.setTextColor(Color.BLUE);
//            userDoesNotExistText.setText("");
//            userDoesNotExistText.setTextColor(Color.RED);
            type = 0;
        }
        return type;

    }

    public void GoToRegisterPage(View view){
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);

    }
}