package com.cmput301f16t16.hitchhiker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;

public class RiderActivity extends AppCompatActivity {
    //private Activity activity = this;

//    private ListView oldRequestList;
//    private ArrayList<Request> requestsList = new ArrayList<Request>();
//    private ArrayAdapter<Request> adapter;

//    public ListView getOldRequestList(){
//        return oldRequestList;
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rider);



        //initialize request manager to save/load the requestList
        RequestListManager.initRequestListManager(this.getApplicationContext());
        final ListView listView = (ListView) findViewById(R.id.open_requests_listview);
        Collection<Request> requests = RequestListController.getRequestList().getRequest();
        final ArrayList list = new ArrayList<Request>(requests);

//        ElasticsearchRequestController.GetRequestsTask getRequestsTask = new ElasticsearchRequestController.GetRequestsTask();
//        getRequestsTask.execute("");
//        try {
//            list = getRequestsTask.get();
//        }
//        catch (Exception e) {
//            Log.i("Error", "Failed to get the tweets out of the async object.");
//        }


        final ArrayAdapter<Request> requestAdapter = new ArrayAdapter<Request>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(requestAdapter);


        //Added a request change observer to tell when the list needs to be updated
        RequestListController.getRequestList().addRequestListener(new RequestListener() {
            @Override
            public void update() {
                list.clear();
                Collection<Request> requests = RequestListController.getRequestList().getRequest();
                list.addAll(requests);
                requestAdapter.notifyDataSetChanged();
            }
        });
        //################################################################################################

        // display requests into the listview
//        oldRequestList = (ListView) findViewById(R.id.open_requests_listview);

//        oldRequestList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
//            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id){
//                Intent intent = new Intent(RiderActivity.this, ProspectiveDriversActivity.class);
//                intent.putExtra("requestsList", requestsList);
//                intent.putExtra("index", position);
//                startActivity(intent);
//            }
//        });
    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        ElasticsearchRequestController.GetRequestsTask getRequestsTask = new ElasticsearchRequestController.GetRequestsTask();
        getRequestsTask.execute("");
        try {
            requestsList = getRequestsTask.get();
        }
        catch (Exception e) {
            Log.i("Error", "Failed to get the requests out of the async object.");
        }
        adapter = new ArrayAdapter<Request>(this, R.layout.request_list_item, requestsList);
        oldRequestList.setAdapter(adapter);

    }


    public void CreateRequest(View view){
        Intent intent = new Intent(RiderActivity.this, CreateRequestActivity.class);
        startActivity(intent);
    }

    public void GoToUserProfilePage(View view) {
        Intent intent = new Intent(RiderActivity.this, UserProfileActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }
}
