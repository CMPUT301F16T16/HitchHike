package com.cmput301f16t16.hitchhiker;

import android.content.Intent;
import android.graphics.Color;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import org.w3c.dom.Document;

import java.util.ArrayList;

/**
 * The type Create request activity.
 */
public class CreateRequestActivity extends AppCompatActivity {
    private User user;
    private Request newRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_request);
        user = (User) getIntent().getSerializableExtra("user");

    }

    /**
     * Create request with the user inputs.
     *
     * @param view the view
     */
    public void CreateRequest(View view){
        RequestListController rc = new RequestListController();
        Toast.makeText(this, "Creating Request", Toast.LENGTH_SHORT).show();

        EditText pickUpText = (EditText) findViewById(R.id.pick_up_edittext);
        EditText dropOffText = (EditText) findViewById(R.id.drop_off_edittext);
        EditText suggestedFareText = (EditText) findViewById(R.id.suggested_fare);


        String pickUp = pickUpText.getText().toString();
        String dropOff = dropOffText.getText().toString();
        String suggestedFare = suggestedFareText.getText().toString();

        if (pickUp.equals("") || dropOff.equals("") || suggestedFare.equals("")){

        }
        else{
            Double price = Double.parseDouble(suggestedFare);
            String userName = user.getUserName();
            newRequest = new Request(userName, pickUp, dropOff, price);
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

    public void ViewMapAction(View view) {
        Intent intent = new Intent(CreateRequestActivity.this, ViewRouteActivity.class);
        intent.putExtra("newRequest", newRequest);
        startActivity(intent);
    }
}
