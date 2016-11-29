package com.cmput301f16t16.hitchhiker;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * The type Show user profile activity. If the user being passed is a rider, they will have the
 * option to Accept the Driver. If the user being passed is a Driver, the accept button will not
 * be visible
 */
public class ShowUserProfileActivity extends AppCompatActivity {
    private User user;
    private Request request;
    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_user_profile);

        user = (User) getIntent().getSerializableExtra("user");
        request = (Request) getIntent().getSerializableExtra("request");

    }

    @Override
    protected void onStart(){
        super.onStart();
        TextView userNameText = (TextView) findViewById(R.id.username_textView);
        TextView firstNameText = (TextView) findViewById(R.id.firstName_textView);
        TextView lastNameText = (TextView) findViewById(R.id.lastName_textView);
        TextView emailText = (TextView) findViewById(R.id.email_textView);
        TextView phoneNumberText = (TextView) findViewById(R.id.phoneNumber_textView);
        TextView riderText = (TextView) findViewById(R.id.rider_textView);
        TextView driverText = (TextView) findViewById(R.id.driver_textView);
        TextView carDetails = (TextView) findViewById(R.id.carDetails);

        userName = user.getUserName();
        Integer userType = user.getUserType();
        String firstName = user.getUserFirstName();
        String lastName = user.getUserLastName();
        String email = user.getUserEmail();
        String phoneNumber = user.getUserPhoneNumber();
        String carDescription = user.getCarDescription();

        userNameText.setText(userName);
        firstNameText.setText(firstName);
        lastNameText.setText(lastName);
        emailText.setText(email);
        phoneNumberText.setText(phoneNumber);
        carDetails.setText(carDescription);


        if (userType == 1){
            riderText.setTextColor(Color.BLACK);
            driverText.setTextColor(Color.LTGRAY);
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
    }

    /**
     * Accept driver action.
     * @param view the view
     * when you accept a driver, driver gets notified, status of request changes to accepted
     * sets the driver to his username, and goes to a new activity with accepted driver, The prospective
     * drivers are removed, no more. Returns to Rider Activity
     */
    public void AcceptDriverAction(View view){
        RequestListController rc = new RequestListController();
        request.setRequestStatus("ACCEPTED");
        request.setDriver(userName);
        rc.addRequest(request);
        finish();

    }
}
