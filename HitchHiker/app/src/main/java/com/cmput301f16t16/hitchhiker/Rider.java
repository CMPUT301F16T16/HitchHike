package com.cmput301f16t16.hitchhiker;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Jae-yeon on 10/14/2016.
 */
public class Rider extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rider);

        Button homeButton = (Button) findViewById(R.id.home_button);
        Button makeButton = (Button) findViewById(R.id.make_button);
        Button profileButton = (Button) findViewById(R.id.profile_button);

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent HomeIntent = new Intent(Rider.this, Rider.class);
                startActivity(HomeIntent);
            }
        });

        makeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent MakeIntent = new Intent(Rider.this, Request.class);
                startActivity(MakeIntent);
            }

        });

    }



    private Integer riderID;

    public Rider(int RiderID) {
        this.riderID = riderID;
    }

    public int getRiderID() {
        return this.riderID;
    }

    public void setRiderID(Integer riderID) {
        this.riderID = riderID;
    }

    public int getRiderId() {
        return this.riderID;
    }




}
