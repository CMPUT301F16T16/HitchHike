package com.cmput301f16t16.hitchhiker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class RequestInfoActivity extends AppCompatActivity {
    private Request request;
//    private User user;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_info);

        request = (Request) getIntent().getSerializableExtra("request");
//        user = (User) getIntent().getSerializableExtra("user");
        name = getIntent().getStringExtra("user");

        TextView pickUpLocationText = (TextView) findViewById(R.id.pickUp_Location_TextView);
        pickUpLocationText.setText(request.getPickUp());

        TextView dropOffLocationText = (TextView) findViewById(R.id.dropOff_Location_textView);
        dropOffLocationText.setText(request.getDropOff());

        TextView userNameText = (TextView) findViewById(R.id.requestUserName_textView);
        userNameText.setText(name);
        //userNameText.setText(user.getUserName());

    }

    public void AcceptRequest(View view){
        RequestListController rc = new RequestListController();

        //String userName = user.getUserName();
        request.addProspectiveDriver(name);
        request.setDriver(name);

        rc.addRequest(request);
        finish();
    }

    public void GoToMap(View view){
        Intent intent = new Intent(RequestInfoActivity.this, LocationViewActivity.class);

        intent.putExtra("request", request);
        startActivity(intent);
    }

//    public void GoToUserProfile(View view){
//        Intent intent = new Intent(RequestInfoActivity.this, ShowUserProfileActivity.class);
//
//        intent.putExtra("user", user);
//        startActivity(intent);
//
//
//    }
}
