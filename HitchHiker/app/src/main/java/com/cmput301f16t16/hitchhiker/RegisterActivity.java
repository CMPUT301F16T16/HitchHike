package com.cmput301f16t16.hitchhiker;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {
    private int userType = 0;
    private ArrayList<User> usersList = new ArrayList<User>();
    private Boolean usernameExists = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    @Override
    protected void onStart(){
        super.onStart();
        /**
         * Pulls from elasticsearch and populate the usersList with the users
         */
        ElasticsearchUserController.GetUsersTask getUsersTask = new ElasticsearchUserController.GetUsersTask();
        getUsersTask.execute("");
        try {
            usersList = getUsersTask.get();
        }
        catch (Exception e) {
            Log.i("Error", "Failed to get the users out of the async object.");
        }
    }

    public void CreateUser(View view){
        EditText usernameText = (EditText) findViewById(R.id.userNameEditText);
        EditText firstNameText = (EditText) findViewById(R.id.firstNameEditText);
        EditText lastNameText = (EditText) findViewById(R.id.lastNameEditText);
        EditText phoneNumberText = (EditText) findViewById(R.id.phoneNumberEditText);
        EditText emailAddressText = (EditText) findViewById(R.id.emailAddressEditText);

        CheckBox riderCheckBox = (CheckBox) findViewById(R.id.riderCheckBox);
        CheckBox driverCheckBox = (CheckBox) findViewById(R.id.driverCheckBox);

        String userName = usernameText.getText().toString();
        String userFirstName = firstNameText.getText().toString();
        String userLastName = lastNameText.getText().toString();
        Integer userPhoneNumber = Integer.parseInt(phoneNumberText.getText().toString());
        String userEmail = emailAddressText.getText().toString();

        /**
         * Determines the userType
         */
        if (riderCheckBox.isChecked() && driverCheckBox.isChecked()){
            userType = 3;
        }
        else if (riderCheckBox.isChecked()){
            userType = 1;
        }
        else if (driverCheckBox.isChecked()){
            userType = 2;
        }

        /**
         * checks to see if the username inputted already exists.
         */
        for (User user: usersList){
            if ((user.getUserName()).contentEquals(userName)){
                usernameExists = true;
            }
        }

        /**
         * If the username exists already, output message.
         * ELSE create the new user
         */
        if (userName.equals("") || userFirstName.equals("") || userLastName.equals("") ||
                userPhoneNumber.equals("") || userEmail.equals("")){
            TextView uniqueUsernameText = (TextView) findViewById(R.id.choose_usertype_text);
            uniqueUsernameText.setText("Please fill out every field.");
            uniqueUsernameText.setTextColor(Color.RED);
            usernameExists = false;
        }
        else if (usernameExists){
            TextView uniqueUsernameText = (TextView) findViewById(R.id.unique_username_text);
            uniqueUsernameText.setText("Your username is not unique!");
            uniqueUsernameText.setTextColor(Color.RED);
            usernameExists = false;
        }
        else if (userType == 0) {
            TextView uniqueUsernameText = (TextView) findViewById(R.id.choose_usertype_text);
            uniqueUsernameText.setText("Please choose to be a rider, driver or both.");
            uniqueUsernameText.setTextColor(Color.RED);
            usernameExists = false;
        }
        else if (usernameExists == false) {
            User newUser = new User(userName, userFirstName, userLastName, userEmail, userPhoneNumber, userType);
            ElasticsearchUserController.AddUsersTask addUsersTask = new ElasticsearchUserController.AddUsersTask();
            addUsersTask.execute(newUser);
            finish();
        }
    }

}
