package com.cmput301f16t16.hitchhiker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class ProspectiveDriversActivity extends AppCompatActivity {
    private int index;
    private ArrayList<Request> requestsList;
    private String requestId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prospective_drivers);

        requestsList = (ArrayList<Request>) getIntent().getSerializableExtra("requestsList");
        index = getIntent().getIntExtra("index", 0);

        Request request = requestsList.get(index);

        requestId = request.getId();



        String requestTrip = request.getTrip();
        TextView displayTrip = (TextView) findViewById(R.id.loc_to_dest_textview);
        displayTrip.setText(requestTrip);

    }

    public void DeleteRequest(View view){
        ElasticsearchRequestController.DeleteRequestTask deleteRequestTask = new ElasticsearchRequestController.DeleteRequestTask();
        deleteRequestTask.setItemId(requestId);
        deleteRequestTask.execute();
        //requestsList.remove(index);
        finish();
    }
}
