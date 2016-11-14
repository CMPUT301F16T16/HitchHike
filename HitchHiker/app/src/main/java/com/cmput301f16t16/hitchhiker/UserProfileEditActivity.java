package com.cmput301f16t16.hitchhiker;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * The type User profile edit activity.
 */
public class UserProfileEditActivity extends AppCompatActivity {
    private EditText emailEditText;
    private EditText phoneNumberEditText;
    private String currentEmail;
    private Integer currentPhoneNumber;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile_edit);

        user = (User) getIntent().getSerializableExtra("user");

        TextView userNameText = (TextView) findViewById(R.id.username_textView);
        TextView firstNameText = (TextView) findViewById(R.id.firstName_textView);
        TextView lastNameText = (TextView) findViewById(R.id.lastName_textView);
        TextView riderText = (TextView) findViewById(R.id.rider_textView);
        TextView driverText = (TextView) findViewById(R.id.driver_textView);

        String userName = user.getUserName();
        Integer userType = user.getUserType();
        String firstName = user.getUserFirstName();
        String lastName = user.getUserLastName();

        userNameText.setText(userName);
        firstNameText.setText(firstName);
        lastNameText.setText(lastName);

        if (userType == 1){
            riderText.setTextColor(Color.BLACK);
            driverText.setTextColor(Color.LTGRAY);
        }
        else if (userType == 2){
            riderText.setTextColor(Color.LTGRAY);
            driverText.setTextColor(Color.BLACK);
        }
        else if (userType == 3){
            riderText.setTextColor(Color.BLACK);
            driverText.setTextColor(Color.BLACK);
        }

        emailEditText = (EditText) findViewById(R.id.email_edittext);
        phoneNumberEditText = (EditText) findViewById(R.id.phoneNumber_edittext);

        currentEmail = user.getUserEmail();
        currentPhoneNumber = user.getUserPhoneNumber();

        emailEditText.setText(currentEmail);
        phoneNumberEditText.setText(Integer.toString(currentPhoneNumber));

    }

    /**
     * Save profile.
     *
     * @param view the view
     */
    public void saveProfile(View view){

        String newEmail = emailEditText.getText().toString();
        String newPhoneNumber = phoneNumberEditText.getText().toString();


        if (newEmail.equals(currentEmail) && newPhoneNumber.equals(Integer.toString(currentPhoneNumber))){
            // say nothing has been changed
        }

        else if (newEmail.equals("") || newPhoneNumber.equals("")){
            // say cannot leave fields blank
        }
        else{
            user.setUserEmail(newEmail);
            user.setUserPhoneNumber(Integer.parseInt(newPhoneNumber));

            ElasticsearchUserController.AddUsersTask update = new ElasticsearchUserController.AddUsersTask();
            update.execute(user);

            Intent intent = new Intent();
            intent.putExtra("updatedUser", user);
            setResult(AppCompatActivity.RESULT_OK, intent);
            finish();


        }
    }
}
