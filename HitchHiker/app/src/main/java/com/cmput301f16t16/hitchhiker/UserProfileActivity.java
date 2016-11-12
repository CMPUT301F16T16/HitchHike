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
        TextView newNumber = (TextView) findViewById(R.id.changeNumber);
        TextView newEmail = (TextView) findViewById(R.id.changeEmail);

        Integer newNumberText = Integer.parseInt(newNumber.getText().toString());
        String newEmailText = newEmail.getText().toString();

        user.setUserPhoneNumber(newNumberText);
        user.setUserEmail(newEmailText);


        finish();
    }

}
