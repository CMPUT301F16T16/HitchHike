package com.cmput301f16t16.hitchhiker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class ProspectiveDriversActivity extends AppCompatActivity {
    private String requestId;
    private Request request;
    private RequestListController rc = new RequestListController();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prospective_drivers);

        request = (Request) getIntent().getSerializableExtra("request");

        requestId = request.getId();

        String requestTrip = request.getTrip();
        TextView displayTrip = (TextView) findViewById(R.id.loc_to_dest_textview);
        displayTrip.setText(requestTrip);

    }

    public void DeleteRequest(View view){
        rc.removeRequest(requestId);
        finish();
    }
}
