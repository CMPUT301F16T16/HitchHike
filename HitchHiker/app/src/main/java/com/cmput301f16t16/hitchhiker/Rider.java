package com.cmput301f16t16.hitchhiker;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Jae-yeon on 10/14/2016.
 */
<<<<<<< HEAD
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
=======
public class Rider extends User{
>>>>>>> 1b56c156b5b8a7a5e64f53b7b252e6830816ea1d

    public Rider(String username, String firstName, String lastName, String phoneNumber, String userEmail, Integer userType) {
        super(username, firstName, lastName, phoneNumber, userEmail, userType);
    }

<<<<<<< HEAD
    public int getRiderID() {
        return this.riderID;
    }

    public void setRiderID(Integer riderID) {
        this.riderID = riderID;
    }

    public int getRiderId() {
        return this.riderID;
    }




=======
>>>>>>> 1b56c156b5b8a7a5e64f53b7b252e6830816ea1d
}
