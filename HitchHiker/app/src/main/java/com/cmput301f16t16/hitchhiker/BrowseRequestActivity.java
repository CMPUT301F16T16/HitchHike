package com.cmput301f16t16.hitchhiker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by V1CTORIA2LEE on 2016-11-12.
 */
public class BrowseRequestActivity extends AppCompatActivity{
    private EditText searchKeyAddressText;
    private EditText searchKeyPriceText;
    private EditText searchKeyText;
    private String searchKeyPriceString;
    private double searchKeyPrice;
    private String searchKey;
    private String searchKeyAddress;
    private ListView theBrowseList;
    private ArrayList<Request> browseList = new ArrayList<Request>();
    private ArrayAdapter<Request> browseAdapter;
    private User user;
    private User driverUser;

    /**
     * Radio Buttons for searching ie. GeoLocation Address Price
     */
    private RadioButton GeoPoint;
    private RadioButton Address;
    private RadioButton Price;

    private String driverName;
    private RequestListController rc = new RequestListController();



    /**
     * browseList is an array of all pending requests
     * request available. The list view is clickable to a new activity to view a
     * map of the requests.
     *
     * request object is passed to the
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_request);

        user = (User) getIntent().getSerializableExtra("user");
        driverName = user.getUserName();

        GeoPoint = (RadioButton) findViewById(R.id.GPSradioButton);
        Address = (RadioButton) findViewById(R.id.AddressRadioButton);
        Price = (RadioButton) findViewById(R.id.PriceRadioButton);

        searchKeyText = (EditText) findViewById(R.id.searchKeyEditText);
        // display requests into the listview
        theBrowseList = (ListView) findViewById(R.id.browsing_requests_listview);
        theBrowseList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id){

                Intent intent = new Intent(BrowseRequestActivity.this, RequestInfoActivity.class);
//                intent.putExtra("requestsList", browseList);
//                intent.putExtra("index", position);
                Request chosenRequest = browseList.get(position);
                intent.putExtra("request", chosenRequest);

                String driverUserName = user.getUserName();
                ElasticsearchUserController.GetUserTask getUserTask = new ElasticsearchUserController.GetUserTask();
                getUserTask.execute(driverUserName);

                try {
                    driverUser = getUserTask.get();
                }
                catch(Exception e){

                }
                intent.putExtra("user", driverUser);
                startActivity(intent);
            }
        });

        try{
            browseList.clear();
            browseList = rc.getBrowseRequest(driverName);
        }
        catch (Exception e){

        }
        browseAdapter = new ArrayAdapter<Request>(this, R.layout.request_list_item, browseList);
        theBrowseList.setAdapter(browseAdapter);

    }


    /**
     * Update browse list.
     *
     *If the Search Edit Text is filled in it will search according to key address word in pickUp and dropOff
     *
     * @param view the view
     */
    public void updateBrowseList(View view) {

        try {
            searchKey = searchKeyText.getText().toString();
            if (Address.isChecked() & (!searchKey.equals(""))) {

                browseList.clear();

                searchKeyAddress = searchKeyText.getText().toString();
                ElasticsearchRequestController.GetKeySearchAddressRequestsTask getKeySearchAddressRequestsTask =
                        new ElasticsearchRequestController.GetKeySearchAddressRequestsTask();
                getKeySearchAddressRequestsTask.setSearchKeyAddress(searchKeyAddress);
                getKeySearchAddressRequestsTask.execute();
                browseList = getKeySearchAddressRequestsTask.get();
                browseAdapter.clear();
                browseAdapter.addAll(browseList);
                browseAdapter.notifyDataSetChanged();


            }
//            if (GeoPoint.isChecked()){
//
//            }
            if (Price.isChecked() & (!searchKey.equals(""))){

                browseList.clear();

                searchKeyPriceString =  searchKeyText.getText().toString();
                searchKeyPrice = Double.parseDouble(searchKeyPriceString);
                ElasticsearchRequestController.GetKeySearchPriceRequestsTask getKeySearchPriceRequestsTask =
                        new ElasticsearchRequestController.GetKeySearchPriceRequestsTask();
                getKeySearchPriceRequestsTask.setSearchKeyPrice(searchKeyPrice);
                getKeySearchPriceRequestsTask.execute();
                browseList = getKeySearchPriceRequestsTask.get();
                browseAdapter.clear();
                browseAdapter.addAll(browseList);
                browseAdapter.notifyDataSetChanged();

            }
            if (searchKey.equals("")) {

                browseAdapter.clear();
                browseAdapter.addAll(rc.getBrowseRequest(driverName));
                browseAdapter.notifyDataSetChanged();

            }
        } catch (Exception e) {
            Log.i("Error", "Failed to get the requests out of the async object.");
        }

//        browseAdapter.clear();
//        browseAdapter.addAll(rc.getBrowseRequest(driverName));
//        browseAdapter.notifyDataSetChanged();
    }

    /**
     * Go to driver profile page.
     *
     * @param view the view
     */
    public void GoToDriverProfilePage(View view) {
        Intent intent = new Intent(BrowseRequestActivity.this, UserProfileActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }


}


