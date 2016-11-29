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

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * The type Create request activity.
 * <p> This View will allow the user to create a request by inputting correct info</p>
 * <p> The user is also to choose their pickUp and dropOff location through a map.</p>
 * @author willyliao
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

    /**
     * View map.
     *  <p> This sends the user to the MapView where they are able to set their start and end locations</p>
     * @param view the view
     * @see LocationViewActivity
     */
    public void ViewMap(View view){
        Intent intent = new Intent(CreateRequestActivity.this, LocationViewActivity.class);
        //intent.putExtra("user", user);
        startActivityForResult(intent,1);
    }


    /**
     * Create request with the user inputs.
     * <p> after User sets location through the map and enters in a "fare price" they can hit submit
     * and a request will be created.</p>
     * <p> The request is added by using elasticsearch through the RequestListController.</p>
     *
     * @param view the view
     * @see RequestListController
     */
    public void CreateRequest(View view){

        RequestListController rc = new RequestListController();
        Toast.makeText(this, "Creating Request", Toast.LENGTH_SHORT).show();
        EditText suggestedFareText = (EditText) findViewById(R.id.suggested_fare);


        GeoPoint start = location.getStartPoint();
        GeoPoint end = location.getEndPoint();

//
        String suggestedFare = suggestedFareText.getText().toString();

        if (pickUp.equals("") || dropOff.equals("") || suggestedFare.equals("")){

        }
        else{
            Double price = Double.parseDouble(suggestedFare);
            String userName = user.getUserName(); 
            Request newRequest = new Request(userName, pickUp, dropOff, price, start, end);
            String result = rc.addRequest(newRequest);

            if (result == null){
                //Toast.makeText(this, "result is null", Toast.LENGTH_SHORT).show();
                this.finish();
            }
            else {
                Toast.makeText(this, "else", Toast.LENGTH_SHORT).show();
            }

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 1){
            if (resultCode == RESULT_OK){
                location = (Location) data.getSerializableExtra("location");
                pickUp = location.getStringStartPoint();
                dropOff = location.getStringEndPoint();
                NumberFormat formatter = new DecimalFormat("#0.00");

                // http://stackoverflow.com/questions/16583604/formatting-numbers-using-decimalformat
                DecimalFormat df = new DecimalFormat("0.##");

                TextView pickUpText = (TextView) findViewById(R.id.pick_up_edittext);
                TextView dropOffText = (TextView) findViewById(R.id.drop_off_edittext);
                TextView FareEstimate = (TextView) findViewById(R.id.fare_estimate);
                FareEstimate.setText(String.valueOf(df.format(location.getFare())));
                pickUpText.setText("Pick up: " + pickUp);
                dropOffText.setText("Drop off: " + dropOff);

            }
        }
    }
}