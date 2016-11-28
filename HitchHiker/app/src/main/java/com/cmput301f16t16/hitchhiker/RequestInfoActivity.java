package com.cmput301f16t16.hitchhiker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
<<<<<<< HEAD
 * The type Request info activity.
 * <p> Shows the information for the request clicked.</p>
=======
 * The type Request info activity. once a request has been pending from the driver POV, it will contain details about the request
 * @author Victoria Lee
>>>>>>> e4b0dc55c3ba15242475455c6e8feeed818578b1
 */
public class RequestInfoActivity extends AppCompatActivity {
    private Request request;
    private User user;
    private User riderUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_info);

        request = (Request) getIntent().getSerializableExtra("chosenRequest");
        // this is the driver being passed around
        user = (User) getIntent().getSerializableExtra("user");

        TextView pickUpLocationText = (TextView) findViewById(R.id.pickUp_Location_TextView);
        pickUpLocationText.setText(request.getPickUp());

        TextView dropOffLocationText = (TextView) findViewById(R.id.dropOff_Location_textView);
        dropOffLocationText.setText(request.getDropOff());

        TextView userNameText = (TextView) findViewById(R.id.requestUserName_textView);
        SpannableString content = new SpannableString(request.getRiderName());
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        userNameText.setText(content);

        TextView requestFareText = (TextView) findViewById(R.id.requestFare_textView);
        String price = request.getPrice().toString();
        requestFareText.setText(price);
    }

    /**
     * Accept request.
<<<<<<< HEAD
     * <p> When called, will change the status of the request to PENDING</p>
     * <p> Will add the request again through RequestListController</p>
     *
     * @param view the view
     * @see RequestListController
=======
     * @param view the view
>>>>>>> e4b0dc55c3ba15242475455c6e8feeed818578b1
     */
    public void AcceptRequest(View view){
        RequestListController rc = new RequestListController();
        String userName = user.getUserName();
        request.addProspectiveDriver(userName);
        // change the status
        request.setRequestStatus("PENDING");
        // update the status
        rc.addRequest(request);
        // we remove the request from the browslist, and put it in the pending list,
        finish();
    }

    /**
     * Go to map.
<<<<<<< HEAD
     * <p> This will go to another map view.</p>
     * @param view the view
     * @see driverViewMap
=======
     * @param view the view
>>>>>>> e4b0dc55c3ba15242475455c6e8feeed818578b1
     */
    public void GoToMap(View view){
        Intent intent = new Intent(RequestInfoActivity.this, driverViewMap.class);
        intent.putExtra("request", request);
        startActivity(intent);
    }

    /**
     * Go to user profile.
<<<<<<< HEAD
     * <p> This will go to the userProfilePage</p>
     * @param view the view
     * @see UserInfoOnlyActivity
=======
     * @param view the view
>>>>>>> e4b0dc55c3ba15242475455c6e8feeed818578b1
     */
    public void GoToUserProfile(View view){
        Intent intent = new Intent(RequestInfoActivity.this, UserInfoOnlyActivity.class);
        String riderName = request.getRiderName();
        ElasticsearchUserController.GetUserTask getUserTask = new ElasticsearchUserController.GetUserTask();
        getUserTask.execute(riderName);
        try {
            riderUser = getUserTask.get();
        }
        catch(Exception e){
        }
        intent.putExtra("user", riderUser);
        startActivity(intent);
    }
}
