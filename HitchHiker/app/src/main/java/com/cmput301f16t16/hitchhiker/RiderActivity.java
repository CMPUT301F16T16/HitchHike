package com.cmput301f16t16.hitchhiker;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class RiderActivity extends AppCompatActivity {
    //private Activity activity = this;

    private ListView oldRequestList;
    private ArrayList<Request> requestsList = new ArrayList<Request>();
    private ArrayAdapter<Request> adapter;

    public ListView getOldRequestList(){
        return oldRequestList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rider);

        // display requests into the listview
        oldRequestList = (ListView) findViewById(R.id.open_requests_listview);


    }
    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        ElasticsearchController.GetRequestsTask getRequestsTask = new ElasticsearchController.GetRequestsTask();
        getRequestsTask.execute("");
        try {
            requestsList = getRequestsTask.get();
        }
        catch (Exception e) {
            Log.i("Error", "Failed to get the tweets out of the async object.");
        }
        adapter = new ArrayAdapter<Request>(this, R.layout.request_list_item, requestsList);
        oldRequestList.setAdapter(adapter);
    }

    public void CreateRequest(View view){
        Intent intent = new Intent(RiderActivity.this, CreateRequestActivity.class);
        startActivity(intent);
    }
}
