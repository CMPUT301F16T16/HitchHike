package com.cmput301f16t16.hitchhiker;

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
    private int userType;
    private ArrayList<User> usersList = new ArrayList<User>();
    private Boolean usernameExists = false;
    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    @Override
    protected void onStart(){
        super.onStart();
        ElasticsearchUserController.GetUsersTask getUsersTask = new ElasticsearchUserController.GetUsersTask();
        getUsersTask.execute("");
        try {
            usersList = getUsersTask.get();
        }
        catch (Exception e) {
            Log.i("Error", "Failed to get the tweets out of the async object.");
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


        String username = usernameText.getText().toString();
        String firstName = firstNameText.getText().toString();
        String lastName = lastNameText.getText().toString();
        String phoneNumber = phoneNumberText.getText().toString();
        String emailAddress = emailAddressText.getText().toString();

        if (riderCheckBox.isChecked() && driverCheckBox.isChecked()){
            userType = 3;
        }
        else if (riderCheckBox.isChecked()){
            userType = 1;
        }
        else if (driverCheckBox.isChecked()){
            userType = 2;
        }

        for (User user: usersList){
            if ((user.getUsername()).contentEquals(username)){
                usernameExists = true;
            }
        }

        /**
         * If the username exists already, output message.
         * ELSE create the new user
         */
        if (usernameExists){
            TextView uniqueUsernameText = (TextView) findViewById(R.id.unique_username_text);
            uniqueUsernameText.setText("Your username is not unique!");
            uniqueUsernameText.setTextColor(Color.RED);
            usernameExists = false;

        }
        else if (usernameExists == false) {
            User newUser = new User(username, firstName, lastName, phoneNumber, emailAddress, userType);
            ElasticsearchUserController.AddUsersTask addUsersTask = new ElasticsearchUserController.AddUsersTask();
            addUsersTask.execute(newUser);
            finish();
        }

    }

}
