package com.cmput301f16t16.hitchhiker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.osmdroid.util.GeoPoint;

import java.util.ArrayList;

/**
 * The type Create request activity.
 */
public class CreateRequestActivity extends AppCompatActivity {
    private User user;
    private Location location;

    private String startAddress;
    private String destAddress;
    private GeoPoint startPoint;
    private GeoPoint endPoint;
    private EditText pickUpText;
    private EditText dropOffText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_request);

        pickUpText = (EditText) findViewById(R.id.pick_up_edittext);
        dropOffText = (EditText) findViewById(R.id.drop_off_edittext);

        user = (User) getIntent().getSerializableExtra("user");

    }

    public void ViewMap(View view){
        Intent intent = new Intent(CreateRequestActivity.this, LocationViewActivity.class);
        startActivityForResult(intent, 1);
    }


    /**
     * Create request with the user inputs.
     *
     * @param view the view
     */
    public void CreateRequest(View view){



        RequestListController rc = new RequestListController();
        Toast.makeText(this, "Creating Request", Toast.LENGTH_SHORT).show();

        EditText suggestedFareText = (EditText) findViewById(R.id.suggested_fare);

        //Fare estimate shouldnt be editable it should pop-up once the
        //dropOff and PickUp location are specified
        EditText estimate = (EditText) findViewById(R.id.suggested_fare);

        /**
         * Spei convert the dropOff Location and pickUp Location to
         * doubles long and lat and return them so they can be added to
         * the request object
         */

        String pickUp = pickUpText.getText().toString();
        String dropOff = dropOffText.getText().toString();
        String suggestedFare = suggestedFareText.getText().toString();

        if (pickUp.equals("") || dropOff.equals("") || suggestedFare.equals("")){

        }
        else{
            Double price = Double.parseDouble(suggestedFare);
            String userName = user.getUserName();
            Request newRequest = new Request(userName, pickUp, dropOff, price, startPoint, endPoint);
            String result = rc.addRequest(newRequest);

            if (result == null){
                Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
                this.finish();
            }
            else {
                Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
            }
        }
    }

    protected void onStart() {
        super.onStart();
    }

    //http://stackoverflow.com/questions/14292398/how-to-pass-data-from-2nd-activity-to-1st-activity-when-pressed-back-android
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request it is that we're responding to
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                startAddress = data.getStringExtra("startAddress");
                destAddress = data.getStringExtra("destinationAddress");
                startPoint = (GeoPoint) data.getSerializableExtra("startGeoLoc");
                endPoint = (GeoPoint) data.getSerializableExtra("destGeoLoc");

                pickUpText.setText(startAddress);
                dropOffText.setText(destAddress);
            }
        }

    }
}