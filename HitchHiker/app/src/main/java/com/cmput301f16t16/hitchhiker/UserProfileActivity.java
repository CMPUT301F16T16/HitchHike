package com.cmput301f16t16.hitchhiker;

import android.content.Intent;
import android.graphics.Color;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.Serializable;

/**
 * Created by Victoria Lee 11/12/2016
 */
public class UserProfileActivity extends AppCompatActivity{
    private User user;

    private String newEmail;
    private Integer newNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        Bundle bundle = getIntent().getExtras();
        user = (User) getIntent().getSerializableExtra("user");

        String username = user.getUserName();
        Integer oldNumber = user.getUserPhoneNumber();
        String oldEmail = user.getUserEmail();

        // setting the Username
        TextView userNameText = (TextView) findViewById(R.id.userNameUnique);
        userNameText.setText(username);

        // current contact information
        EditText oldNumberText = (EditText) findViewById(R.id.changeNumber);
        EditText oldEmailText = (EditText) findViewById(R.id.changeEmail);
        oldNumberText.setText(""+oldNumber);
        oldEmailText.setText(oldEmail);

    }


    public void editPhoneNumber(View view){
        //updates
        EditText editText = (EditText) findViewById(R.id.changeNumber);
        if ((editText.getText().toString()).equals("")){
            // include a textView to show a message: Cannot save an empty phonenumber.
        }
        else{
            try{
                newNumber = Integer.parseInt(editText.getText().toString());
                //editText.setText(newNumber);
                user.setUserPhoneNumber(newNumber);
            }
            catch(Exception e){
                // include a textView to show a message: Please only enter digits for phonenumber
            }
        }
    }

    public void editEmail(View view){
        //updates
        EditText editText = (EditText) findViewById(R.id.changeEmail);
        newEmail = editText.getText().toString();
        if (newEmail.equals("")){
            // include a textView to show a message: This field is empty! Please enter in an email.
        }
        else {
            //editText.setText(newEmail);
            user.setUserEmail(newEmail);
        }
    }

    public void savedChanges(View v){

        //updates ElasticSearch by creating the user again
        ElasticsearchUserController.AddUsersTask update = new ElasticsearchUserController.AddUsersTask();
        update.execute(user);

        // send updated user back
        Intent intent = new Intent(UserProfileActivity.this, RiderActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);


        finish();
    }



}
