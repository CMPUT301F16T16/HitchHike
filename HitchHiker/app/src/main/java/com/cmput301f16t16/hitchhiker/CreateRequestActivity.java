package com.cmput301f16t16.hitchhiker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CreateRequestActivity extends AppCompatActivity {

    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_request);
        Toast.makeText(this, "make a new request", Toast.LENGTH_SHORT).show();


//        Bundle bundle = getIntent().getExtras();
//        user = bundle.getParcelable("user");
    }

    public void CreateRequest(View view){

        EditText pickUpText = (EditText) findViewById(R.id.pick_up_edittext);
        EditText dropOffText = (EditText) findViewById(R.id.drop_off_edittext);
        //EditText estimate = (EditText) findViewById(R.id.suggested_fare);
        String pickUp = pickUpText.getText().toString();
        String dropOff = dropOffText.getText().toString();
        //Integer estimate = Integer.parseInt(estimate.getText().toString());

        //Request newRequest = new Request(user.getUserName(), pickUp, dropOff, estimate);
        Request newRequest = new Request(pickUp, dropOff);

        RequestListController rlc = new RequestListController();
        rlc.addRequest(newRequest);

        Toast.makeText(this, "Added a request", Toast.LENGTH_SHORT).show();




    }
}
