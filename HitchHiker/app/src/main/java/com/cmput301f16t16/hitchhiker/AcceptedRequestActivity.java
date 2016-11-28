package com.cmput301f16t16.hitchhiker;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by V1CTORIA2LEE on 2016-11-26.
 */
public class AcceptedRequestActivity extends Activity{
    // hi
    private User user;
    private Request request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accepted_request);

        //user = (User) getIntent().getSerializableExtra("user");
        request = (Request) getIntent().getSerializableExtra("request");

        TextView rider = (TextView) findViewById(R.id.rider_name);
        TextView driver = (TextView) findViewById(R.id.driver_name);
        TextView fare = (TextView) findViewById(R.id.fare);

        driver.setText(request.getDriver());
        rider.setText(request.getRiderName());
        Double d = request.getPrice();
        String str = Double.toString(d);
        fare.setText(str);

    }
}
