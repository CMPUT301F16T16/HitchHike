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
    private ListView theRequestList ;
    private ArrayList<Request> requestList = new ArrayList<Request>();
    private ArrayList<Request> acceptedList = new ArrayList<Request>();
    private ArrayList<Request> pendingList = new ArrayList<Request>();

    private ArrayAdapter<Request> requestAdapter;
    private RequestListController rc = new RequestListController();
    private String driverName;
    private String accepted;
    private String pending;

    //private Request request;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver);

        Bundle bundle = getIntent().getExtras();
        user = (User) getIntent().getSerializableExtra("user");
        // the driver name
        driverName = user.getUserName();
        pending = "PENDING";
        accepted = "ACCEPTED";

        // populate the pending listview with request that the driver selected, whos status is all pending
        theRequestList = (ListView) findViewById(R.id.driverRequest_listview);

        acceptedList = rc.getListOfAccepted(accepted);
        ArrayList<Request> TempList = new ArrayList<Request>();

        for(Request request : acceptedList){
            if (driverName.equals(request.getDriver())) {
                requestList.add(request);
            }
        }
        pendingList = rc.getListOfPending(pending);
        // temp list has all the pending request, we must narrow down to driver in prospectiveDriverList
        TempList.addAll(pendingList);

        for(Request request: TempList){
            // makes a new tempDriver list for each request
            ArrayList<String> TempDriver = new ArrayList<String>();
            TempDriver.addAll(request.getProspectiveDrivers());
            for (String driver: TempDriver){
                if (driverName.equals(driver)){
                    requestList.add(request);
                }
            }
        }

//        for (int i = 0 ; i < TempList.size(); i++){
//            TempDriver.addAll(request.getProspectiveDrivers());
//            for (int j = 0; j < TempDriver.size(); i++) {
//                // get all the prospective drivers
//                if (driverName == TempDriver[j]){
//                    requestList.add(request);
//                }
//            }
//        }
        requestAdapter = new ArrayAdapter<Request>(this, R.layout.request_list_item, requestList);
        theRequestList.setAdapter(requestAdapter);
        requestAdapter.notifyDataSetChanged();
    }



//
//        try {
//            // how to populate the browslist view
//            browseList.clear();
//            ElasticsearchRequestController.GetBrowsingRequestsTask getBrowsingRequestsTask = new ElasticsearchRequestController.GetBrowsingRequestsTask();
//            getBrowsingRequestsTask.execute("");
//            browseList = getBrowsingRequestsTask.get();
//        } catch (Exception e) {
//            Log.i("Error", "Failed to get the requests out of the async object.");
//        }
//        browseAdapter = new ArrayAdapter<Request>(this, R.layout.request_list_item, browseList);
//        theBrowseList.setAdapter(browseAdapter);
//        browseAdapter.notifyDataSetChanged();
//    }

    //}

//        public void onStart(){
//            super.onStart();
//            // populate the pending listview with request that the driver selected, whos status is all pending
//            theRequestList = (ListView) findViewById(R.id.driverRequest_listview);
//            requestList.clear();
//            requestList = rc.getListOfPendingRequest(userName, pending, accepted);
//            try {
//                ElasticsearchRequestController.GetPendingTask getPendingTask = new ElasticsearchRequestController.GetPendingTask();
//                getPendingTask.execute("");
//                requestList = getPendingTask.get();
//            } catch (Exception e) {
//                Log.i("Error", "Failed to get the requests out of the async object.");
//            }
//
//            requestAdapter = new ArrayAdapter<Request>(this, R.layout.request_list_item, requestList);
//            theRequestList.setAdapter(requestAdapter);
//            requestAdapter.addAll(requestList);
//
//            requestAdapter.notifyDataSetChanged();
//
//    }


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

//    //Only here for testing purposes.
//    public void RefreshAction(View view){
//        requestList.clear();
//        requestList = rc.getListOfPendingRequest(userName, pending, accepted);
//
//        requestAdapter = new ArrayAdapter<Request>(this, R.layout.request_list_item, requestList);
//        theRequestList.setAdapter(requestAdapter);
//        requestAdapter.notifyDataSetChanged();
//    }


}


