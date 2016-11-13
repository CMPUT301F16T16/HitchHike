package com.cmput301f16t16.hitchhiker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class RiderActivity extends AppCompatActivity {

    private ListView theRequestList;
    private ArrayList<Request> requestsList = new ArrayList<Request>();
    private ArrayAdapter<Request> requestAdapter;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rider);

        user =(User) getIntent().getSerializableExtra("user");

        // display requests into the listview
        theRequestList = (ListView) findViewById(R.id.open_requests_listview);
        theRequestList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id){
                Intent intent = new Intent(RiderActivity.this, ProspectiveDriversActivity.class);
                intent.putExtra("requestsList", requestsList);
                intent.putExtra("index", position);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stubz
        super.onStart();

        requestsList.clear();
        ElasticsearchRequestController.GetRequestsTask getRequestsTask = new ElasticsearchRequestController.GetRequestsTask();
        getRequestsTask.setUserName(user.getUserName());
        getRequestsTask.execute("");

        try {
            requestsList = getRequestsTask.get();
        }
        catch (Exception e) {
            Log.i("Error", "Failed to get the tweets out of the async object.");
        }
        requestAdapter = new ArrayAdapter<Request>(this, R.layout.request_list_item, requestsList);
        theRequestList.setAdapter(requestAdapter);
        requestAdapter.notifyDataSetChanged();
    }


    //Only here for testing purposes.
    public void Refresh(View view){
        requestsList.clear();

        ElasticsearchRequestController.GetRequestsTask getRequestsTask = new ElasticsearchRequestController.GetRequestsTask();
//        getRequestsTask.execute("");
        getRequestsTask.setUserName(user.getUserName());
        getRequestsTask.execute("");

        try {
            requestsList = getRequestsTask.get();
        }
        catch (Exception e) {
            Log.i("Error", "Failed to get the tweets out of the async object.");
        }
        requestAdapter = new ArrayAdapter<Request>(this, R.layout.request_list_item, requestsList);
        theRequestList.setAdapter(requestAdapter);
        requestAdapter.notifyDataSetChanged();
    }
    public void CreateRequest(View view){
        Intent intent = new Intent(RiderActivity.this, CreateRequestActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }

    public void GoToUserProfilePage(View view) {
        Intent intent = new Intent(RiderActivity.this, UserProfileActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }

    public void onBackPressed(){
        // Do nothing
    }
}
