package com.cmput301f16t16.hitchhiker;

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
public class CreateRequestActivity extends AppCompatActivity implements OnMapReadyCallback {
    private User user;

    private GoogleMap googleMap;
    private MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_request);
        user = (User) getIntent().getSerializableExtra("user");
        mapView = (MapView) this.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.onResume();
        mapView.getMapAsync(this);


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
            Request newRequest = new Request(userName, pickUp, dropOff, price);
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


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap mMap) {
        googleMap = mMap;
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        // move camera to edmonton

        //LatLng defaultLocation = new LatLng(53.5444,-113.4904);

        // 51°03′N 114°04′W
        double x1 = 51.03;
        double y1 = -114.04;
        double x2 = 53.54;
        double y2 = -113.49;

        LatLng startPoint = new LatLng(x1,y1);
        LatLng endPoint = new LatLng(x2,y2);

        // zoom to the mid point of the two coordinates dynamically
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        builder.include(startPoint);
        builder.include(endPoint);
        LatLngBounds bound = builder.build();
        googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bound,600,600,5));

        //make a marker

        Marker start = googleMap.addMarker(new MarkerOptions()
                .position(startPoint)
                .title("Start Point")
                .icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_RED))
        );
        //start.setTag(0);
        Marker destination = googleMap.addMarker(new MarkerOptions()
                .position(endPoint)
                .title("End Point")
                .icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
        );
        //destination.setTag(0);

        // routing
        // http://stackoverflow.com/questions/14444228/android-how-to-draw-route-directions-google-maps-api-v2-from-current-location-t
        GMapV2Direction md = new GMapV2Direction();

        Document doc = null;
        doc = md.getDocument(startPoint,endPoint,
                GMapV2Direction.MODE_DRIVING);
        if (doc == null){
            Toast.makeText(this,"Null",Toast.LENGTH_LONG).show();
        }

        ArrayList<LatLng> directionPoint = md.getDirection(doc);
        PolylineOptions rectLine = new PolylineOptions().width(10).color(
                Color.CYAN);

        for (int i = 0; i < directionPoint.size(); i++) {
            rectLine.add(directionPoint.get(i));
        }

        Polyline polylin = googleMap.addPolyline(rectLine);





        //LatLng sydney = new LatLng(-34, 151);
        //googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));

        //googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(defaultLocation,10));
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }


}
