package com.cmput301f16t16.hitchhiker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.osmdroid.util.GeoPoint;
import org.w3c.dom.Text;

/**
 * The type Create request activity.
 */
public class CreateRequestActivity extends AppCompatActivity {
    private User user;
    private Location location;
    private String pickUp;
    private String dropOff;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_request);
        user = (User) getIntent().getSerializableExtra("user");

    }

    public void ViewMap(View view){
        Intent intent = new Intent(CreateRequestActivity.this, LocationViewActivity.class);
        //intent.putExtra("user", user);
        startActivity(intent);
    }


    /**
     * Create request with the user inputs.
     *
     * @param view the view
     */
    public void CreateRequest(View view){



        RequestListController rc = new RequestListController();
        Toast.makeText(this, "Creating Request", Toast.LENGTH_SHORT).show();
//
//        EditText pickUpText = (EditText) findViewById(R.id.pick_up_edittext);
//        EditText dropOffText = (EditText) findViewById(R.id.drop_off_edittext);
        EditText suggestedFareText = (EditText) findViewById(R.id.suggested_fare);

        //Fare estimate shouldnt be editable it should pop-up once the
        //dropOff and PickUp location are specified


        /**
         * Spei convert the dropOff Location and pickUp Location to
         * doubles long and lat and return them so they can be added to
         * the request object
         */

        GeoPoint dd = location.getStartPoint();

//        String pickUp = location.getStringStartPoint();
//        String dropOff = location.getStringEndPoint();
//
        String suggestedFare = suggestedFareText.getText().toString();

        if (pickUp.equals("") || dropOff.equals("") || suggestedFare.equals("")){

        }
        else{
            Double price = Double.parseDouble(suggestedFare);
            String userName = user.getUserName();
            Request newRequest = new Request(userName, pickUp, dropOff, price, "");
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
        location = (Location) getIntent().getSerializableExtra("location");

        if (location != null) {
            pickUp = location.getStringStartPoint();
            dropOff = location.getStringEndPoint();
            TextView pickUpText = (TextView) findViewById(R.id.pick_up_edittext);
            TextView dropOffText = (TextView) findViewById(R.id.drop_off_edittext);
            pickUpText.setText(pickUp);
            dropOffText.setText(dropOff);

        }
    }
}