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
 *
 * <p>browseList is an array of all pending requests
 * request available. The list view is clickable to a new activity to view a
 * map of the requests.</p>
 * @author victorialee
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

    /**
     * Radio Buttons for searching ie. GeoLocation Address Price
     */
    private RadioButton GeoPoint;
    private RadioButton Address;
    private RadioButton Price;

    private String driverName;
    private RequestListController rc = new RequestListController();

    /**
     * Called when the activity is first created.
     * <p>When called, it will populate the listView with requests that are available
     * for selection to the Driver.</p>
     * <p> The list is populated using the RequestListController</p>
     * @param savedInstanceState
     * @see RequestListController
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_request);

        // this is passing the driver
        user = (User) getIntent().getSerializableExtra("user");
        driverName = user.getUserName();

        GeoPoint = (RadioButton) findViewById(R.id.GPSradioButton);
        Address = (RadioButton) findViewById(R.id.AddressRadioButton);
        Price = (RadioButton) findViewById(R.id.PriceRadioButton);

        searchKeyText = (EditText) findViewById(R.id.searchKeyEditText);
        // display requests into the listview
        theBrowseList = (ListView) findViewById(R.id.browsing_requests_listview);
        // when you click on a request
        theBrowseList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id){

                Intent intent = new Intent(BrowseRequestActivity.this, RequestInfoActivity.class);
                Request chosenRequest = browseList.get(position);
                intent.putExtra("chosenRequest", chosenRequest);
                intent.putExtra("user", user);
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
     * <p>This button will update work according to whether the search EditText is filled,
     * and also which selection is currently chosen.</p>
     *
     * @param view the view
     * @see ElasticsearchRequestController
     * @see RequestListController
     *
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

    }

    /**
     * Go to driver profile page.
     * <p> This is linked to the profile button. </p>
     * <p> This will send the user to their profile page.</p>
     * @param view the view
     * @see UserProfileActivity
     */
    public void GoToDriverProfilePage(View view) {
        Intent intent = new Intent(BrowseRequestActivity.this, UserProfileActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }
}


