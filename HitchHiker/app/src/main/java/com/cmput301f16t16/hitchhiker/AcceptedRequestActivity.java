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
 *
 * This class is the view class where a rider can go to for an Accepted Request.
 * <p> The rider is able to pay for the ride here.</p>
 * @author willyliao
 */
public class AcceptedRequestActivity extends Activity{

    private User user;
    private Request request;

    /**
     * Called when the activity is first created.
     * <p>This initiates TextViews to display: the trip, rider name, driver name
     *      , and fare.</p>
     * <p>The rider name TextView and the driverName textView are both clickable.
     *      They both lead to their respective userProfiles.</p>
     * <p>There is also a Pay button which is used to pay for the ride.</p>
     *
     * @param savedInstanceState
     */
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
        }
        else {
            View payButton = findViewById(R.id.paymentButton);
            payButton.setVisibility(View.GONE);
        }
    }

    /**
     * Rider page action.
     * <p>This is linked to the rider name TextView.</p>
     * <p>When clicked, it will send the user to the userProfileActivity displaying the riders info.</p>
     *
     * @param view the view
     * @see UserProfileActivity
     */
    public void RiderPageAction(View view){
        Intent Intent = new Intent(AcceptedRequestActivity.this, UserInfoOnlyActivity.class);
        String rider = request.getRiderName();
        UserListController ulc = new UserListController();
        ulc.findUser(rider);
        User user = ulc.findUser(rider);
        Intent.putExtra("user", user);
        startActivity(Intent);
    }

    /**
     * Driver page action.
     * <p>This is linked to the driver name TextView.</p>
     * <p>When clicked, it will send the user to the userProfileActivity displaying the drivers info.</p>
     * @param view the view
     * @see UserProfileActivity
     */
    public void DriverPageAction(View view){
        Intent intent = new Intent(AcceptedRequestActivity.this, UserInfoOnlyActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }

    public void ViewMap(View view){
        Intent intent = new Intent(AcceptedRequestActivity.this, driverViewMap.class);
        intent.putExtra("request", request);
        startActivity(intent);
    }

    /**
     * Pay now action.
     * <p> This button will change the current request status that it is on
     *      from ACCEPTED to COMPLETED.</p>
     *
     * @param view the view
     */
    public void PayNowAction(View view){
        Toast.makeText(this, "Payment completed, Thank You for using HitchHiker!", Toast.LENGTH_SHORT).show();
        RequestListController rc = new RequestListController();
        request.setRequestStatus("COMPLETED");
        rc.addRequest(request);

        finish();
    }
}
