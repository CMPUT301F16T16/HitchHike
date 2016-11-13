package com.cmput301f16t16.hitchhiker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RiderActivity extends AppCompatActivity {

    //Added a request change observer to tell when the list needs to be updated


//    private ListView oldRequestList;
//    private ArrayList<Request> requestsList = new ArrayList<Request>();
//    private ArrayAdapter<Request> adapter;

    //    public ListView getOldRequestList(){
//        return oldRequestList;
//    }
//    private User user;
//    private ListView requestListView;
//    private ArrayList<Request> requestList = new ArrayList<Request>();
//    private ArrayAdapter<Request> adapter;

    private ListView theRequestList;
    private ArrayList<Request> requestsList = new ArrayList<Request>();
    private ArrayAdapter<Request> requestAdapter;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rider);

        // Can also use serializable\
        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");


        //initialize request manager to save/load the requestList
//        RequestListManager.initRequestListManager(this.getApplicationContext());
        theRequestList = (ListView) findViewById(R.id.open_requests_listview);
//        Collection<Request> requests = RequestListController.getRequestList();

        theRequestList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id){
                Intent intent = new Intent(RiderActivity.this, ProspectiveDriversActivity.class);
                intent.putExtra("requestsList", requestsList);
                intent.putExtra("index", position);
                startActivity(intent);
            }
        });



        try {
            requestsList.clear();
            ElasticsearchRequestController.GetRequestsTask getRequestsTask = new ElasticsearchRequestController.GetRequestsTask();
            getRequestsTask.setUserName(user.getUserName());
            getRequestsTask.execute("");
            requestsList = getRequestsTask.get();
        } catch (Exception e) {
            Log.i("Error", "Failed to get the requests out of the async object.");
        }
        requestAdapter = new ArrayAdapter<Request>(this, R.layout.request_list_item, requestsList);
        theRequestList.setAdapter(requestAdapter);
        requestAdapter.notifyDataSetChanged();

//        adapter = new ArrayAdapter<Request>(this, R.layout.request_list_item, requestList);
//        requestListView.setAdapter(adapter);
//        adapter.notifyDataSetChanged();
    }


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

//        adapter = new ArrayAdapter<Request>(this, R.layout.request_list_item, requestList);
//        requestListView.setAdapter(adapter);
//        adapter.notifyDataSetChanged();
    }

    public void CreateRequest(View view) {
        Intent intent = new Intent(RiderActivity.this, CreateRequestActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }

    public void GoToUserProfilePage(View view) {
        Intent intent = new Intent(RiderActivity.this, UserProfileActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }
}
