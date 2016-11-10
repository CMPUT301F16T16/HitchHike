package com.cmput301f16t16.hitchhiker;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collection;

public class RegisterActivity extends AppCompatActivity {

    private Activity activity = this;

    private static final String FILENAME = "file.sav";
    private EditText firstNameText;
    private EditText lastNameText;
    private EditText emailAddressText;
    private EditText passwordText;
    private EditText confirmPasswordText;
    private EditText phoneNumberText;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String userName;
    private String password;
    private int phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

    }

    public void CreateUser(View view) {
        EditText firstNameText = (EditText) findViewById(R.id.firstNameText);
        EditText lastNameText = (EditText) findViewById(R.id.lastNameText);
        EditText emailAddressText = (EditText) findViewById(R.id.emailAddressText);
        EditText passwordText = (EditText) findViewById(R.id.passwordText);
        EditText confirmPasswordText = (EditText) findViewById(R.id.confirmPasswordText);
        EditText phoneNumberText = (EditText) findViewById(R.id.phoneNumberText);

        String firstName = firstNameText.getText().toString();
        String lastName = lastNameText.getText().toString();
        String emailAddress = emailAddressText.getText().toString();
        String userName = emailAddressText.getText().toString();
        String password = passwordText.getText().toString();
        Integer phoneNumber = phoneNumberText.getInputType();

        User user = new User(userName);
        user.setUserFirstName(firstName);
        user.setUserLastName(lastName);
        user.setUserEmail(emailAddress);
        user.setUserPassword(password);
        user.setUserPhoneNumber(phoneNumber);


        ElasticsearchUserController.AddUserTask addUserTask = new ElasticsearchUserController.AddUserTask();
        addUserTask.execute(user);
        finish();
    }

}
