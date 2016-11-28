package com.cmput301f16t16.hitchhiker;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by V1CTORIA2LEE on 2016-11-26.
 */
public class AcceptedRequestActivity extends Activity{

    private User user;
    private Request request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accepted_request);

        //passing the driver and the request
        user = (User) getIntent().getSerializableExtra("user");
        request = (Request) getIntent().getSerializableExtra("request");

        TextView tripText = (TextView) findViewById(R.id.request_textView);
        TextView rider = (TextView) findViewById(R.id.rider_name);
        TextView driver = (TextView) findViewById(R.id.driver_name);
        TextView fare = (TextView) findViewById(R.id.fare_price);

        tripText.setText(request.getTrip());

        driver.setText(request.getDriver());
        driver.setTextColor(Color.BLUE);

        rider.setText(request.getRiderName());
        rider.setTextColor(Color.BLUE);

        Double d = request.getPrice();
        String str = Double.toString(d);
        fare.setText(str);

        Integer user_type = user.getUserType();

        if (user_type == 1){
            View payButton = findViewById(R.id.paymentButton);
            //payButton.setVisibility(View.VISIBLE);
        }
        else {
            View payButton = findViewById(R.id.paymentButton);
            payButton.setVisibility(View.GONE);
        }
    }

    public void RiderPageAction(View view){
        Intent Intent = new Intent(AcceptedRequestActivity.this, UserInfoOnlyActivity.class);
        String rider = request.getRiderName();
        UserListController ulc = new UserListController();
        ulc.findUser(rider);
        User user = ulc.findUser(rider);
        Intent.putExtra("user", user);
        startActivity(Intent);

    }

    public void DriverPageAction(View view){
        Intent intent = new Intent(AcceptedRequestActivity.this, UserInfoOnlyActivity.class);
//        String driver = request.getDriver();
//        UserListController ulc = new UserListController();
//        ulc.findUser(driver);
        intent.putExtra("user", user);
        startActivity(intent);
    }


    public void PayNowAction(View view){
        Toast.makeText(this, "Payment completed, Thank You for using HitchHiker!", Toast.LENGTH_SHORT).show();
        RequestListController rc = new RequestListController();
        request.setRequestStatus("COMPLETED");
        rc.addRequest(request);

        finish();
    }
}
