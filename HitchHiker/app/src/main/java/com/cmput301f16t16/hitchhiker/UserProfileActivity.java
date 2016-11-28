package com.cmput301f16t16.hitchhiker;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * The User profile activity displays the users profile information. The user can edit certain
 * fields of information when they click the edit button
 */
public class UserProfileActivity extends AppCompatActivity {
    // this user depends on where it is being sent from
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        user = (User) getIntent().getSerializableExtra("user");
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
        TextView carText = (TextView) findViewById(R.id.carTitle) ;
        TextView carDetails = (TextView) findViewById(R.id.carDetails);

        String userName = user.getUserName();
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


        if (userType == 1){
            riderText.setTextColor(Color.BLACK);
            driverText.setTextColor(Color.LTGRAY);
            carText.setVisibility(View.GONE);
        }
        else if (userType == 2){
            riderText.setTextColor(Color.LTGRAY);
            driverText.setTextColor(Color.BLACK);
            carDetails.setText(carDescription);

        }
        else if (userType == 3){
            riderText.setTextColor(Color.BLACK);
            driverText.setTextColor(Color.BLACK);
            carDetails.setText(carDescription);

        }
    }

    /**
     * Edit profile. A button that goes to an activity where the user cna edit information
     * @param view the view
     */
    public void editProfile(View view){
        Intent intent = new Intent(this, UserProfileEditActivity.class);
        intent.putExtra("user", user);
        startActivityForResult(intent, 1);
    }

    //http://stackoverflow.com/questions/14292398/how-to-pass-data-from-2nd-activity-to-1st-activity-when-pressed-back-android
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request it is that we're responding to
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                user = (User) data.getSerializableExtra("updatedUser");
            }
        }
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent();
        intent.putExtra("updatedUser", user);
        setResult(AppCompatActivity.RESULT_OK, intent);
        finish();
    }
}
