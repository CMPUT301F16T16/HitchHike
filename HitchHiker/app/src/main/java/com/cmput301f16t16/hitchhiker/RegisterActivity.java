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

        // Setting up userList
        UserListManager.initUserManager(this.getApplicationContext());
        Collection<User> users = UserListController.getUserList().getUsers();
        final ArrayList<User> userList = new ArrayList<User>(users);


        UserListController.getUserList().addUserListener(new UserListener() {
            @Override
            public void update() {
                userList.clear();
                Collection<User> users = UserListController.getUserList().getUsers();
                userList.addAll(users);
            }
        });

        firstNameText = (EditText) findViewById(R.id.firstNameText);
        lastNameText = (EditText) findViewById(R.id.lastNameText);
        emailAddressText = (EditText) findViewById(R.id.emailAddressText);
        passwordText = (EditText) findViewById(R.id.passwordText);
        confirmPasswordText = (EditText) findViewById(R.id.confirmPasswordText);
        phoneNumberText = (EditText) findViewById(R.id.phoneNumberText);

        firstName = firstNameText.getText().toString();
        lastName = lastNameText.getText().toString();
        emailAddress = emailAddressText.getText().toString();
        userName = emailAddressText.getText().toString();
        password = passwordText.getText().toString();
        phoneNumber = phoneNumberText.getInputType();

        Button registerButton = (Button) findViewById(R.id.registerButton);

        registerButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                User user = new User(userName);
                user.setUserFirstName(firstName);
                user.setUserLastName(lastName);
                user.setUserEmail(emailAddress);
                user.setUserPassword(password);
                user.setUserPhoneNumber(phoneNumber);

                ElasticsearchUserController.AddUserTask addUserTask = new ElasticsearchUserController.AddUserTask();
                addUserTask.execute(user);
                setResult(RESULT_OK);

                //Intent RegisterIntent = new Intent(RegisterActivity.this, )
            }
            });
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        ElasticsearchUserController.GetUsersTask getUsersTask = new ElasticsearchUserController.GetUsersTask();
//        getUsersTask.execute("");
//        try {
//            userList = getUsersTask.get();
//        } catch (Exception e) {
//            Log.i("Error", "Failed to get the users out of the async object.");
//        }
//    }
}
