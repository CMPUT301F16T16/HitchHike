package com.cmput301f16t16.hitchhiker;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * The type Rider activity.
 */
public class RiderActivity extends AppCompatActivity {

    private ListView theRequestList;
    private ArrayList<Request> requestsList = new ArrayList<Request>();
    private ArrayAdapter<Request> requestAdapter;
    private User user;
    private RequestListController rc = new RequestListController();
    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rider);

        user =(User) getIntent().getSerializableExtra("user");
        userName = user.getUserName();

        // display requests into the listview
        theRequestList = (ListView) findViewById(R.id.open_requests_listview);

        requestsList = rc.getListOfRequest(userName);
        requestAdapter = new ArrayAdapter<Request>(this, R.layout.request_list_item, requestsList);
        theRequestList.setAdapter(requestAdapter);
        requestAdapter.notifyDataSetChanged();


        theRequestList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Request request = requestsList.get(position);

                if (request.getRequestStatus() == "ACCEPTED") {
                    Intent intent = new Intent(RiderActivity.this, AcceptedRequestActivity.class);
                    intent.putExtra("request", request);
                    startActivity(intent);

                } else {
                    Intent intent = new Intent(RiderActivity.this, ProspectiveDriversActivity.class);
                    intent.putExtra("request", request);
                    startActivity(intent);

                }
            }
        });
    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stubz
        super.onStart();

        requestsList.clear();
        requestsList = rc.getListOfRequest(userName);

        requestAdapter = new ArrayAdapter<Request>(this, R.layout.request_list_item, requestsList);
        theRequestList.setAdapter(requestAdapter);
        requestAdapter.notifyDataSetChanged();
    }

    /**
     * Refresh.
     *
     * @param view the view
     */
//Only here for testing purposes.
    public void Refresh(View view){
        requestsList.clear();
        requestsList = rc.getListOfRequest(userName);

        requestAdapter = new ArrayAdapter<Request>(this, R.layout.request_list_item, requestsList);
        theRequestList.setAdapter(requestAdapter);
        requestAdapter.notifyDataSetChanged();
    }

    /**
     * Create request.
     *
     * @param view the view
     */
    public void CreateRequest(View view){
        Intent intent = new Intent(RiderActivity.this, CreateRequestActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }

    /**
     * Go to user profile page.
     *
     * @param view the view
     */
    public void GoToUserProfilePage(View view) {
        Intent intent = new Intent(RiderActivity.this, UserProfileActivity.class);
        intent.putExtra("user", user);
        startActivityForResult(intent, 1);
    }


    //http://stackoverflow.com/questions/14292398/how-to-pass-data-from-2nd-activity-to-1st-activity-when-pressed-back-android
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request it is that we're responding to
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                user = (User) data.getSerializableExtra("updatedUser");

            }
        }
    }

    // Code taken from http://stackoverflow.com/questions/6413700/android-proper-way-to-use-onbackpressed-with-toast
    // on Nov 24, 2016
    @Override
    public void onBackPressed() {

        if( user.userType == 1) {
            new AlertDialog.Builder(this)
                    .setTitle("Exit App?")
                    .setMessage("Exit Hitch Hiker?")
                    .setNegativeButton("No", null)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface arg0, int arg1) {
                            finish();
                        }
                    }).create().show();
        }
        else{
            finish();
        }
    }


}
