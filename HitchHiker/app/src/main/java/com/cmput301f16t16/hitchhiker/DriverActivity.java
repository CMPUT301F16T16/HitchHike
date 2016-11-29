package com.cmput301f16t16.hitchhiker;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Driver activity.
 * <p> This View allows the driver to see the requests he is currently a part of.
 * This could be a request he has accepted, waiting for response, or completed.</p>
 *
 * @author willyliao
 */
public class DriverActivity extends AppCompatActivity {
    private User user; // this is the driver
    private ListView theRequestList ;
    private ArrayList<Request> requestList = new ArrayList<Request>();

    private ArrayAdapter<Request> requestAdapter;
    private RequestListController rc = new RequestListController();
    private String driverName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver);

        Bundle bundle = getIntent().getExtras();
        user = (User) getIntent().getSerializableExtra("user");
        // the driver name
        driverName = user.getUserName();

        // populate the pending listview with request that the driver selected, whos status is all pending
        theRequestList = (ListView) findViewById(R.id.driverRequest_listview);


         // add the accepted requests

        theRequestList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Request request = requestList.get(position);
                Intent intent = new Intent(DriverActivity.this, AcceptedRequestActivity.class);
                // passing the driver, and the request
                intent.putExtra("user", user);
                intent.putExtra("request", request);
                startActivity(intent);
            }
        });

        requestList.clear();
        requestList = rc.getCurrentRequest(driverName);
        requestAdapter = new ArrayAdapter<Request>(this, R.layout.request_list_item, requestList);
        theRequestList.setAdapter(requestAdapter);
    }

    /**
     * Browse request action.
     * <p> This is linked to a button, and will take the user to the BrowseRequestActivity,
     * where the user will be able to browse and select requests that he may want to do.</p>
     *
     * @param view the view
     * @see BrowseRequestActivity
     */
    public void BrowseRequestAction(View view) {
        Intent intent = new Intent(DriverActivity.this, BrowseRequestActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }

    /**
     * User profile page action.
     * <p> This is linked to a button, and will take the user to their profile page.</p>
     * @see UserProfileActivity
     * @param view the view
     */
    public void UserProfilePageAction(View view) {
        Intent intent = new Intent(DriverActivity.this, UserProfileActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }

    /**
     * Refresh action.
     * <p> This is a refresh action in which it will recall elasticsearch to grab and
     * update the current list.</p>
     *
     * @see RequestListController
     * @param view the view
     */
    public void RefreshAction(View view){
        requestAdapter.clear();
        requestAdapter.addAll(rc.getCurrentRequest(driverName));
        requestAdapter.notifyDataSetChanged();
    }

}


