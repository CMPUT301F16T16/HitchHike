package com.cmput301f16t16.hitchhiker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {

    private Boolean riderBool;
    private Boolean DriverBool;
    private int userType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


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


        User newUser = new User(username, firstName, lastName, phoneNumber, emailAddress, userType);

        ElasticsearchUserController.AddUsersTask addUsersTask = new ElasticsearchUserController.AddUsersTask();
        addUsersTask.execute(newUser);
        finish();
    }

}
