package com.cmput301f16t16.hitchhiker;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Driver activity.
 */
public class DriverActivity extends AppCompatActivity {
    private User user; // this is the driver
    private ListView thePendingList ;
    private ListView theAcceptedList;
    private ArrayList<Request> pendingList = new ArrayList<Request>();
    private ArrayList<Request> acceptedList = new ArrayList<Request>();
    private ArrayAdapter<Request> pendingAdapter;
    private ArrayAdapter<Request> acceptedAdapter;
    private RequestListController rc = new RequestListController();
    private String userName;
    private String accepted;
    private String pending;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver);

        Bundle bundle = getIntent().getExtras();
        user = (User) getIntent().getSerializableExtra("user");
        userName = user.getUserName();

        pending = "PENDING";

        // populate the pending listview with request that the driver selected, whos status is all pending
        thePendingList = (ListView) findViewById(R.id.pending_requests_listview);
        pendingList.clear();
        pendingList = rc.getListOfPendingRequest(userName, pending);
        try {
            ElasticsearchRequestController.GetPendingTask getPendingTask = new ElasticsearchRequestController.GetPendingTask();
            getPendingTask.execute("");
            pendingList = getPendingTask.get();
        } catch (Exception e) {
            Log.i("Error", "Failed to get the requests out of the async object.");
        }

        pendingAdapter = new ArrayAdapter<Request>(this, R.layout.request_list_item, pendingList);
        thePendingList.setAdapter(pendingAdapter);
        pendingAdapter.addAll(pendingList);

        pendingAdapter.notifyDataSetChanged();

        // accepted listview stuff
//        theAcceptedList = (ListView) findViewById(R.id.acceptedByRider_listview);
//        acceptedList = rc.getListOfAcceptedRequest(userName, accepted);
//        acceptedAdapter = new ArrayAdapter<Request>(this, R.layoutpen)
//
    }

        public void onStart(){

         super.onStart();
            pendingList.clear();
            pendingList = rc.getListOfPendingRequest(userName,pending);

            // populate the pending listview with request that the driver selected, whos status is all pending
            pending = "PENDING";
            thePendingList = (ListView) findViewById(R.id.pending_requests_listview);
//        try {
//            ElasticsearchRequestController.GetPendingTask getPendingTask = new ElasticsearchRequestController.GetPendingTask();
//            getPendingTask.execute("");
//            pendingList = getPendingTask.get();
//        } catch (Exception e) {
//            Log.i("Error", "Failed to get the requests out of the async object.");
//        }

            pendingAdapter = new ArrayAdapter<Request>(this, R.layout.request_list_item, pendingList);
            thePendingList.setAdapter(pendingAdapter);
           // pendingAdapter.addAll(pendingList);

            pendingAdapter.notifyDataSetChanged();

            // accepted listview stuff
//        theAcceptedList = (ListView) findViewById(R.id.acceptedByRider_listview);
//        acceptedList = rc.getListOfAcceptedRequest(userName, accepted);
//        acceptedAdapter = new ArrayAdapter<Request>(this, R.layoutpen)
//

    }







    /**
     * Browse request action.
     *
     * @param view the view
     */
    public void BrowseRequestAction(View view) {
        Intent intent = new Intent(DriverActivity.this, BrowseRequestActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }

    /**
     * User profile page action.
     *
     * @param view the view
     */
    public void UserProfilePageAction(View view) {
        Intent intent = new Intent(DriverActivity.this, UserProfileActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }

    //Only here for testing purposes.
    public void RefreshAction(View view){
        pendingList.clear();
        pendingList = rc.getListOfPendingRequest(userName, pending);

        pendingAdapter = new ArrayAdapter<Request>(this, R.layout.request_list_item, pendingList);
        thePendingList.setAdapter(pendingAdapter);
        pendingAdapter.notifyDataSetChanged();
    }


}


