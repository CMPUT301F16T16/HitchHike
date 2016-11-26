package com.cmput301f16t16.hitchhiker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * The type Prospective drivers activity.
 */
public class ProspectiveDriversActivity extends AppCompatActivity {
    private String requestId;
    private Request request;
    private RequestListController rc = new RequestListController();
    private User user;

    private ArrayList<String> prospectiveDriverList = new ArrayList<String>();
    private ArrayAdapter<String> driverAdapter;
    private ListView theProspectiveDriversList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prospective_drivers);

        request = (Request) getIntent().getSerializableExtra("request");

        theProspectiveDriversList = (ListView) findViewById(R.id.prospective_driver_listview);

        //Populate the listview with prospective drivers
        prospectiveDriverList = request.getProspectiveDrivers();
        driverAdapter = new ArrayAdapter<String>(this, R.layout.request_list_item, prospectiveDriverList);
        theProspectiveDriversList.setAdapter(driverAdapter);
        driverAdapter.notifyDataSetChanged();

        requestId = request.getId();

        String requestTrip = request.getTrip();
        TextView displayTrip = (TextView) findViewById(R.id.loc_to_dest_textview);
        displayTrip.setText(requestTrip);


        theProspectiveDriversList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id){
                String userName;

                Intent intent = new Intent(ProspectiveDriversActivity.this, ShowUserProfileActivity.class);

                userName = prospectiveDriverList.get(position);

                ElasticsearchUserController.GetUserTask getUserTask = new ElasticsearchUserController.GetUserTask();
                getUserTask.execute(userName);

                try {
                    user = getUserTask.get();
                }
                catch(Exception e){

                }
                intent.putExtra("user", user);
                intent.putExtra("request", request);
                startActivity(intent);
                finish();
            }
        });

    }


    public void DeleteRequest(View view){
        rc.removeRequest(requestId);
        finish();
    }
}
