package com.cmput301f16t16.hitchhiker;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * This Activity displays the information of the User and allows for certain fields of
 * information to be editable. Depending on whether the user is a rider only or both, the
 * car information field will change visibility.
 * @author Willy Liao
 */
public class UserProfileEditActivity extends AppCompatActivity {
    private EditText emailEditText;
    private EditText phoneNumberEditText;
    private EditText carDetails;
    private String currentEmail;
    private String currentPhoneNumber;
    private User user;
    private TextView errorMessage;
    private String carDescription;

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
        errorMessage = (TextView) findViewById(R.id.profile_warning_textview);
        TextView carText = (TextView) findViewById(R.id.carTitle);
        carDetails = (EditText) findViewById(R.id.carDetails);


        String userName = user.getUserName();
        Integer userType = user.getUserType();
        String firstName = user.getUserFirstName();
        String lastName = user.getUserLastName();
        carDescription = user.getCarDescription();

        userNameText.setText(userName);
        firstNameText.setText(firstName);
        lastNameText.setText(lastName);
        carDetails.setText(carDescription);

        if (userType == 1){
            riderText.setTextColor(Color.BLACK);
            driverText.setTextColor(Color.LTGRAY);
            carText.setVisibility(View.GONE);
            carDetails.setVisibility(View.GONE);
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
        phoneNumberEditText.setText(currentPhoneNumber);
    }

    /**
     * Save profile information after it was edited. If the user is a driver (aka both) the they
     * are required to enter car details, it cannot be left blank. To update the user information
     * we add the user again, and it replaces the old user because they have the same jestId
     * @param view the view
     * @see UserListController
     */
    public void saveProfile(View view){

        String newEmail = emailEditText.getText().toString();
        String newPhoneNumber = phoneNumberEditText.getText().toString();
        String newCarDetails = carDetails.getText().toString();

        if (user.userType != 1) {
            if (newEmail.equals(currentEmail) && newPhoneNumber.equals(currentPhoneNumber) && newCarDetails.equals(carDetails)) {
                // say nothing has been changed
                errorMessage.setText("Nothing was changed. Cannot save.");
                errorMessage.setTextColor(Color.BLUE);
            } else if (newEmail.equals("") || newPhoneNumber.equals("") || newCarDetails.equals("")) {
                errorMessage.setText("Please fill in all fields.");
                errorMessage.setTextColor(Color.RED);

            } else {
                user.setUserEmail(newEmail);
                user.setUserPhoneNumber(newPhoneNumber);
                user.setCarDescription(newCarDetails);

                ElasticsearchUserController.AddUsersTask update = new ElasticsearchUserController.AddUsersTask();
                update.execute(user);

                Intent intent = new Intent();
                intent.putExtra("updatedUser", user);
                setResult(AppCompatActivity.RESULT_OK, intent);
                finish();

            }
        }
        else{
            if (newEmail.equals(currentEmail) && newPhoneNumber.equals(currentPhoneNumber)) {
                // say nothing has been changed
                errorMessage.setText("Nothing was changed. Cannot save.");
                errorMessage.setTextColor(Color.BLUE);
            } else if (newEmail.equals("") || newPhoneNumber.equals("")) {
                errorMessage.setText("Please fill in all fields.");
                errorMessage.setTextColor(Color.RED);

            } else {
                user.setUserEmail(newEmail);
                user.setUserPhoneNumber(newPhoneNumber);

                ElasticsearchUserController.AddUsersTask update = new ElasticsearchUserController.AddUsersTask();
                update.execute(user);

                Intent intent = new Intent();
                intent.putExtra("updatedUser", user);
                setResult(AppCompatActivity.RESULT_OK, intent);
                finish();
            }

        }
    }
}
