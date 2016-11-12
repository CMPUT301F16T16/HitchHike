package com.cmput301f16t16.hitchhiker;

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
        newNumber = Integer.parseInt(editText.getText().toString());

    }

    public void editEmail(View view){
        //updates
        EditText editText = (EditText) findViewById(R.id.changeEmail);
        newEmail = editText.getText().toString();
    }

    public void savedChanges(View v){
        TextView newNumberText = (TextView) findViewById(R.id.changeNumber);
        TextView newEmailText = (TextView) findViewById(R.id.changeEmail);

        Integer newNumber = Integer.parseInt(newNumberText.getText().toString());
        String newEmail = newEmailText.getText().toString();

        newNumberText.setText(""+newNumber);
        newEmailText.setText(newEmail);

        //saves in user
        user.setUserPhoneNumber(newNumber);
        user.setUserEmail(newEmail);

        finish();
    }

}
