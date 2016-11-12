package com.cmput301f16t16.hitchhiker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;

public class RiderActivity extends AppCompatActivity {

    private ListView oldRequestList;
    private ArrayList<Request> requestsList = new ArrayList<Request>();
    private ArrayAdapter<Request> adapter;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rider);

        // Can also use serializable
        Bundle bundle = getIntent().getExtras();
        user = (User) getIntent().getSerializableExtra("user");


        // display requests into the listview
        oldRequestList = (ListView) findViewById(R.id.open_requests_listview);

        oldRequestList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
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
        intent.putExtra("user", user);
        startActivity(intent);
    }

    public void GoToUserProfilePage(View view) {
        Intent intent = new Intent(RiderActivity.this, UserProfileActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }
}
