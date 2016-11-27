package com.cmput301f16t16.hitchhiker;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.location.*;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.osmdroid.api.IMapController;
import org.osmdroid.bonuspack.location.GeocoderNominatim;
import org.osmdroid.bonuspack.routing.OSRMRoadManager;
import org.osmdroid.bonuspack.routing.Road;
import org.osmdroid.bonuspack.routing.RoadManager;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.Overlay;
import org.osmdroid.views.overlay.OverlayItem;
import org.osmdroid.views.overlay.Polyline;
import org.osmdroid.views.overlay.infowindow.BasicInfoWindow;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * The type Location view activity.
 */
public class LocationViewActivity extends Activity implements Serializable {

    private List<Overlay> overlayList;

    private LocationManager lm;
    private String towers;
    private GeoPoint currentPoint;
    private GeoPoint startPoint;
    private GeoPoint endPoint;
    private GeoPoint touchedPoint;
    private String startAddress;
    private String destinationAddress;
    private Double distance;
    private long start;
    private long stop;
    private int x, y, lat, longi;
    private double rate = 5;
    private double fare;
    private Location location;

    final String userAgent = "tingwei@ualberta.ca";

    IMapController mapController;
    /**
     * The Our activity.
     */
    Activity ourActivity = this;
    /**
     * The Map.
     */
    MapView map;
    /**
     * The M roads.
     */
    Road[] mRoads;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

//        request = (Request) getIntent().getSerializableExtra("request");

//        String requestView = request.getTrip();
//        TextView displayTrip = (TextView) findViewById(R.id.loc_display_req_textview);
//        displayTrip.setText(requestView);


        map = (MapView) findViewById(R.id.map);
        map.setTileSource(TileSourceFactory.MAPNIK);
        map.setBuiltInZoomControls(true);
        map.setMultiTouchControls(true);

        /**
         * Using fake coordinates until we can create REAL requests
         */
        startPoint = new GeoPoint(53.527452, -113.529679);


        mapController = map.getController();
        mapController.setZoom(9);
        mapController.setCenter(startPoint);

        /**
         * User long click screen
         */
        Touchy t = new Touchy();
        overlayList = map.getOverlays();
        overlayList.add(t);


        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria crit = new Criteria();
        towers = lm.getBestProvider(crit, false);
        android.location.Location location = lm.getLastKnownLocation(towers);

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(LocationViewActivity.this, "First enable LOCATION ACCESS in settings.", Toast.LENGTH_LONG).show();
            return;
        }

        if (location != null) {
            lat = (int) (location.getLatitude() * 1E6);
            longi = (int) (location.getLongitude() * 1E6);
            currentPoint = new GeoPoint(lat, longi);
            setStartMarker(currentPoint);
            mapController.animateTo(currentPoint);


        }


        // http://stackoverflow.com/questions/38539637/osmbonuspack-roadmanager-networkonmainthreadexception
        // accessed on October 27th, 2016
        // author: yubaraj poudel
        ArrayList<OverlayItem> overlayItemArray;
        overlayItemArray = new ArrayList<>();

        overlayItemArray.add(new OverlayItem("Starting Point", "This is the starting point", startPoint));
        overlayItemArray.add(new OverlayItem("Destination", "This is the detination point", endPoint));

        //getRoadAsync(startPoint, endPoint);
    }
    public void setRoute(View view){
        if (endPoint != null) {
//            Intent intent = new Intent(LocationViewActivity.this, CreateRequestActivity.class);
            Intent intent = new Intent();

            intent.putExtra("startAddress", startAddress);
            intent.putExtra("destinationAddress",destinationAddress);
            intent.putExtra("startGeoLoc", (Serializable) startPoint);
            intent.putExtra("destGeoLoc", (Serializable) endPoint);

            //intent.putExtra("location", location);
            setResult(AppCompatActivity.RESULT_OK, intent);
            finish();
        }
        else{Toast.makeText(LocationViewActivity.this, "Please set destination", Toast.LENGTH_SHORT).show();}
    }

    public void setStartMarker(GeoPoint sp) {
        // set the map
        Marker startMarker = new Marker(map);

        startMarker.setPosition(sp);
        startMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        startMarker.setTitle("START");
        startMarker.setInfoWindow(new BasicInfoWindow(org.osmdroid.bonuspack.R.layout.bonuspack_bubble, map));


        overlayList.add(startMarker);

    }

    public void setEndMarker() {
        Marker endMarker = new Marker(map);

        endMarker.setPosition(endPoint);
        endMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        endMarker.setTitle("END");
        endMarker.setInfoWindow(new BasicInfoWindow(org.osmdroid.bonuspack.R.layout.bonuspack_bubble, map));

        overlayList.add(endMarker);


    }


//long click

    /**
     * Set Location
     */
    private class Touchy extends Overlay {


        @Override
        protected void draw(Canvas canvas, MapView mapView, boolean b) {


        }

        public boolean onTouchEvent(MotionEvent e, MapView m) {
            //Log.d("MAPTAG", "testing");
            if (e.getAction() == MotionEvent.ACTION_DOWN) {
                start = e.getEventTime();
                x = (int) e.getX();
                y = (int) e.getY();
                touchedPoint = (GeoPoint) map.getProjection().fromPixels(x, y);

            }

            if (e.getAction() == MotionEvent.ACTION_UP) {
                stop = e.getEventTime();
            }

            if (stop - start > 1500) {
                AlertDialog alert = new AlertDialog.Builder(LocationViewActivity.this).create();
                alert.setTitle("Pick an Option");
                alert.setMessage("Your current location will be your default start point");
                alert.setButton(DialogInterface.BUTTON_POSITIVE, "Set SatrtPoint", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        // TODO Auto-generated method stub
                        startPoint = new GeoPoint(touchedPoint.getLatitude(), touchedPoint.getLongitude());
                        startAddress = getAddress(startPoint.getLatitude(), startPoint.getLongitude());
                        setStartMarker(startPoint);

                        EditText etOrigin = (EditText) findViewById(R.id.origin);
                        etOrigin.setText(startAddress);

                        TextView text = (TextView) findViewById(R.id.textView1) ;
                        text.setText(startAddress);

                        map.invalidate();
                        Toast.makeText(LocationViewActivity.this, "Set Start Point", Toast.LENGTH_LONG).show();
                    }


                });
                alert.setButton(DialogInterface.BUTTON_NEGATIVE, "Set Destination", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        // TODO Auto-generated method stub
                        endPoint = new GeoPoint(touchedPoint.getLatitude(), touchedPoint.getLongitude());

                        EditText etOrigin = (EditText) findViewById(R.id.origin);
                        EditText etDestination = (EditText) findViewById(R.id.destination);

                        destinationAddress = getAddress(endPoint.getLatitude(), endPoint.getLongitude());
                        etDestination.setText(destinationAddress);

                        getRoadAsync(startPoint, endPoint);

                        mapController.animateTo(startPoint);
                        Toast.makeText(LocationViewActivity.this, "Set destination", Toast.LENGTH_LONG).show();
                    }
                    //setEndPoint(touchedpoint);
                    //Toast.makeText(MainActivity.this, "Set destination", Toast.LENGTH_LONG).show();
                });

                alert.setButton(DialogInterface.BUTTON_NEUTRAL, "Clear", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        // TODO Auto-generated method stub
                        overlayList.clear();
                        map.invalidate();

                        Touchy t = new Touchy();
                        overlayList = map.getOverlays();
                        overlayList.add(t);

                    }

                });


                alert.show();
                return true;
            }
            return false;
        }
    }


    //  ROUTE
    public void getRoute(View view) {

        EditText etOrigin = (EditText) findViewById(R.id.origin);
        EditText etDestination = (EditText) findViewById(R.id.destination);

        final String o = etOrigin.getText().toString();
        final String d = etDestination.getText().toString();

        if (o.equals("") || d.equals("")) {
            Toast.makeText(LocationViewActivity.this, "Please enter in a Start Location and Destination!", Toast.LENGTH_LONG).show();
        }
        else {
            startAddress = o;
            destinationAddress = d;
            new Thread() {

                public void run() {
                    startPoint = getLocation(o);
                    endPoint = getLocation(d);

                    if (startPoint != null && endPoint != null) {

                        getRoadAsync(startPoint, endPoint);

                        mapController.animateTo(startPoint);

                        location = new Location(startPoint, endPoint, o, d);


                    } else {
                        Toast.makeText(LocationViewActivity.this, "Fail to find a route !", Toast.LENGTH_LONG).show();
                    }
                }
            }
                    .start();
        }
    }

    public GeoPoint getLocation(String location) {

        GeocoderNominatim gn = new GeocoderNominatim(location);
        GeoPoint gp = null;

        ArrayList<Address> al;
        try {
            al = (ArrayList<Address>) gn.getFromLocationName(location, 1);

            if (al != null && al.size() > 0) {
                Log.i("Script", "Street: " + al.get(0).getThoroughfare());
                Log.i("Script", "City: " + al.get(0).getSubAdminArea());
                Log.i("Script", "Province: " + al.get(0).getAdminArea());
                Log.i("Script", "Country: " + al.get(0).getCountryName());

                gp = new GeoPoint(al.get(0).getLatitude(), al.get(0).getLongitude());
                Log.d("GEOPOINT", "Lat: " + String.valueOf(al.get(0).getLatitude()));
                Log.d("GEOPOINT", "Lon: " + String.valueOf(al.get(0).getLongitude()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return (gp);
    }

    /**
     * Gets road async.
     *
     * @param startPoint       the start point
     * @param destinationPoint the destination point
     */
    public void getRoadAsync(GeoPoint startPoint, GeoPoint destinationPoint) {
        mRoads = null;

        ArrayList<GeoPoint> waypoints = new ArrayList<GeoPoint>(2);
        waypoints.add(startPoint);
        waypoints.add(destinationPoint);
        new UpdateRoadTask().execute(waypoints);
    }

    private class UpdateRoadTask extends AsyncTask<Object, Void, Road[]> {

        protected Road[] doInBackground(Object... params) {
            @SuppressWarnings("unchecked")
            ArrayList<GeoPoint> waypoints = (ArrayList<GeoPoint>) params[0];
            RoadManager roadManager = new OSRMRoadManager(ourActivity);
            return roadManager.getRoads(waypoints);
        }

        @Override
        //recieved help by looking at https://github.com/CMPUT301F16T01/Carrier/blob/master/app/src/main/java/comcmput301f16t01/github/carrier/ViewLocationsActivity.java
        protected void onPostExecute(Road[] roads) {
            mRoads = roads;
            Road path = null;
            double min = 0;
            if (roads == null)
                return;
            if (roads[0].mStatus == Road.STATUS_TECHNICAL_ISSUE)
                Toast.makeText(map.getContext(), "Technical issue when getting the route", Toast.LENGTH_SHORT).show();
            else if (roads[0].mStatus > Road.STATUS_TECHNICAL_ISSUE) //functional issues
                Toast.makeText(map.getContext(), "No possible route here", Toast.LENGTH_SHORT).show();
            Polyline[] mRoadOverlays = new Polyline[roads.length];
            List<Overlay> mapOverlays = map.getOverlays();
            for (Road road : roads) {
                if (road.mLength < min || min == 0) {
                    min = road.mLength;
                    path = road;
                }
            }
            String routeDesc = path.getLengthDurationText(ourActivity, -1);
            distance = path.mLength;
            //Log.d("distance:", Double.toString(distance));
            Polyline roadPolyline = RoadManager.buildRoadOverlay(path);
            roadPolyline.setTitle(getString(R.string.app_name) + " - " + routeDesc);
            roadPolyline.setInfoWindow(new BasicInfoWindow(org.osmdroid.bonuspack.R.layout.bonuspack_bubble, map));
            mapOverlays.add(0, roadPolyline);
            setStartMarker(startPoint);
            setEndMarker();
            map.invalidate();
        }
    }

    private String getAddress(double latitude, double longitude){
        String theAddress;
        try {
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
//            GeocoderNominatim geocoder = new GeocoderNominatim(current, userAgent);
//            geocoder.setService("http://open.mapquestapi.com/nominatim/v1/");
//            geocoder.setKey("G2m83JSnHrnZ9nMb9Uc0KcDPTfCPxTSo");

            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
            StringBuilder sb = new StringBuilder();
            if(addresses.size() > 0) {
                Address address = addresses.get(0);
                int n = address.getMaxAddressLineIndex();
                for(int i = 0; i <= n; i++) {
                    if(i != 0) {
                        sb.append(", ");
                    }
                    sb.append(address.getAddressLine(i));
                }
                theAddress = new String(sb);
            } else {
                theAddress = null;
            }
        } catch (Exception e) {
            theAddress = null;
        }
        return theAddress;
    }

}
