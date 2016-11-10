package com.cmput301f16t16.hitchhiker;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.google.gson.Gson;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.zip.CheckedInputStream;

public class RegisterActivity extends AppCompatActivity {

    //    private Activity activity = this;
    private static final String FILENAME = "file.sav";

    Collection<User> users = UserListController.getUserList().getUsers();
    private ArrayList<User> userList = new ArrayList<User>(users);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Setting up userList
//        UserListManager.initUserManager(this.getApplicationContext());
//        UserListController.getUserList().addUserListener(new UserListener() {
//            @Override
//            public void update() {
//                userList.clear();
//                Collection<User> users = UserListController.getUserList().getUsers();
//                userList.addAll(users);
//            }
//        });

    }
}
//    static public int userType = 0;
//    public int onCheckboxUserTypeClicked (View view) {
//        CheckBox rider_check = (CheckBox)findViewById(R.id.riderCheckBox);
//        CheckBox driver_check = (CheckBox)findViewById(R.id.driverCheckBox);
//        int userType = 0;
//        boolean checked = ((CheckBox) view ).isChecked();
//        if (rider_check.isChecked()) {
//            userType = 1;
//        }
//        if (driver_check.isChecked()) {
//            userType = 2;
//        }
//        if (driver_check.isChecked() && rider_check.isChecked()) {
//            userType = 3;
//        }
//        return userType;
//    }
//
//
//    public void submission (View view) {
//        final EditText firstNameText = (EditText) findViewById(R.id.firstNameText);
//        final EditText lastNameText = (EditText) findViewById(R.id.lastNameText);
//        final EditText emailAddressText = (EditText) findViewById(R.id.emailAddressText);
//        final EditText phoneNumberText = (EditText) findViewById(R.id.phoneNumberText);
//
//        Button registerButton = (Button) findViewById(R.id.registerButton);
//
//        registerButton.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
////                User user = new User(firstNameText.getText().toString());
//                User user = new User(firstNameText.getText().toString());
//                user.setUserFirstName(lastNameText.getText().toString());
//                user.setUserEmail(emailAddressText.getText().toString());
//                user.setUserType(userType);
//                user.setUserPhoneNumber(Integer.parseInt(phoneNumberText.getText().toString()));
//
//                ElasticsearchUserController.AddUserTask addUserTask = new ElasticsearchUserController.AddUserTask();
//                addUserTask.execute(user);
//                setResult(RESULT_OK);
//
//                //Intent RegisterIntent = new Intent(RegisterActivity.this, )
//            }
//        });



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

