package com.cmput301f16t16.hitchhiker;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    private User user;
    private ListView thePendingList ;
    private ArrayList<Request> pendingList = new ArrayList<Request>();
    private ArrayAdapter<Request> pendingAdapter;
    private RequestListController rc = new RequestListController();
    private String userName;
    private String created;
    private String pending;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver);

        Bundle bundle = getIntent().getExtras();
        user = (User) getIntent().getSerializableExtra("user");
        userName = user.getUserName();

        created = "CREATED";
        pending = "PENDING";

        thePendingList = (ListView) findViewById(R.id.pending_requests_listview);


        //pendingList.clear();
        pendingList = rc.getListOfPendingRequest(userName,created);


        pendingAdapter = new ArrayAdapter<Request>(this, R.layout.request_list_item, pendingList);
        thePendingList.setAdapter(pendingAdapter);
        pendingAdapter.notifyDataSetChanged();

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


}


