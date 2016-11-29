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
    private Location newLocation;
    private LocationManager lm;
    private String towers;
    private GeoPoint currentPoint;
    private GeoPoint startPoint;
    private GeoPoint endPoint;
    private GeoPoint touchedPoint;
    private Double distance;
    private long start;
    private long stop;
    private int x, y, lat, longi;
    private EditText getOrigin;
    private EditText getDestination;
    private String startAddress;
    private String destAddress;


    /**
     * The Map controller.
     */
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

        getOrigin = (EditText) findViewById(R.id.origin);
        getDestination = (EditText) findViewById(R.id.destination);

        map = (MapView) findViewById(R.id.map);
        map.setTileSource(TileSourceFactory.MAPNIK);
        map.setBuiltInZoomControls(true);
        map.setMultiTouchControls(true);

        //Using fake coordinates until we can create REAL requests
        startPoint = new GeoPoint(53.527452, -113.529679);


        mapController = map.getController();
        mapController.setZoom(9);
        mapController.setCenter(startPoint);

        // User long click
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

    }


    /**
     * Set route.
     *
     * <p> This is linked to a button.</p>
     * <p> When called, it will create a new Location object that will contain
     * the start and end geopoints, the start and end addresses, and the distance of the route.</p>
     * <p> This will be returned back to the CreateRequestActivity.</p>
     *
     * @param view the view
     * @see CreateRequestActivity
     * @see Location
     */
    public void setRoute(View view){
        if (endPoint != null && startPoint != null) {
            newLocation = new Location(startPoint, endPoint, startAddress, destAddress, distance);

            Intent intent = new Intent();
            intent.putExtra("location", newLocation);
            setResult(AppCompatActivity.RESULT_OK, intent);
            finish();
        }
        else{Toast.makeText(LocationViewActivity.this, "Please set destination", Toast.LENGTH_SHORT).show();}
    }

    /**
     * Sets start marker.
     *
     *  <p> This sets the Location marker on the start point on the Map.</p>
     * @param sp the sp
     */
    public void setStartMarker(GeoPoint sp) {
        // set the map
        Marker startMarker = new Marker(map);
        startMarker.setPosition(sp);
        startMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        startMarker.setTitle("START");
        startMarker.setInfoWindow(new BasicInfoWindow(org.osmdroid.bonuspack.R.layout.bonuspack_bubble, map));
        overlayList.add(startMarker);
    }

    /**
     * Sets end marker.
     *
     *  <p> This sets the Location marker on the end point on the Map.</p>
     */
    public void setEndMarker() {
        Marker endMarker = new Marker(map);
        endMarker.setPosition(endPoint);
        endMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        endMarker.setTitle("END");
        endMarker.setInfoWindow(new BasicInfoWindow(org.osmdroid.bonuspack.R.layout.bonuspack_bubble, map));
        overlayList.add(endMarker);
    }


    /**
     * Set Location
     *
     * <p> This is for the touch interface of the map.</p>
     * <p> This allows a startPoint or an endPoint to be selected and set.</p>
     * <p> The route will also be drawn.</p>
     **/
    private class Touchy extends Overlay {
        @Override
        protected void draw(Canvas canvas, MapView mapView, boolean b) {
        }
        public boolean onTouchEvent(MotionEvent e, MapView m) {
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
                alert.setButton(DialogInterface.BUTTON_POSITIVE, "Set StartPoint", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        // TODO Auto-generated method stub
                        startPoint = new GeoPoint(touchedPoint.getLatitude(), touchedPoint.getLongitude());
                        if (endPoint == null){
                            clearMap();

                            setStartMarker(startPoint);

                            startAddress = getTheAddress(startPoint.getLatitude(), startPoint.getLongitude());
                            getOrigin.setText(startAddress);

                            map.invalidate();

                        }
                        else{
                            clearMap();

                            setStartMarker(startPoint);

                            startAddress = getTheAddress(startPoint.getLatitude(), startPoint.getLongitude());
                            getOrigin.setText(startAddress);

                            destAddress = getTheAddress(endPoint.getLatitude(), endPoint.getLongitude());
                            getDestination.setText(destAddress);

                            getRoadAsync(startPoint,endPoint);

                            map.invalidate();
                        }


                        Toast.makeText(LocationViewActivity.this, "Set Start Point", Toast.LENGTH_LONG).show();
                    }


                });
                alert.setButton(DialogInterface.BUTTON_NEGATIVE, "Set Destination", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        // TODO Auto-generated method stub
                        endPoint = new GeoPoint(touchedPoint.getLatitude(), touchedPoint.getLongitude());

                        if (startPoint == null){
                            clearMap();

                            destAddress = getTheAddress(endPoint.getLatitude(), endPoint.getLongitude());
                            getDestination.setText(destAddress);

                            getRoadAsync(startPoint, endPoint);
                            mapController.animateTo(startPoint);

                            map.invalidate();

                        }
                        else{
                            clearMap();

                            startAddress = getTheAddress(startPoint.getLatitude(), startPoint.getLongitude());
                            getOrigin.setText(startAddress);

                            destAddress = getTheAddress(endPoint.getLatitude(), endPoint.getLongitude());
                            getDestination.setText(destAddress);

                            getRoadAsync(startPoint,endPoint);
                            mapController.animateTo(startPoint);

                            map.invalidate();

                        }

                        Toast.makeText(LocationViewActivity.this, "Set destination", Toast.LENGTH_LONG).show();
                    }
                    //setEndPoint(touchedpoint);
                    //Toast.makeText(MainActivity.this, "Set destination", Toast.LENGTH_LONG).show();
                });

                alert.show();
                return true;
            }
            return false;
        }
    }

    /**
     * Gets route.
     * <p> This sets the route according to what is typed in the editText.</p>
     * <p> It will draw a rout efrom the Start and End points</p>
     * @param view the view
     */
//  ROUTE
    public void getRoute(View view) {


        final String o = getOrigin.getText().toString();
        final String d = getDestination.getText().toString();

        new Thread() {

            public void run() {
                startPoint = getLocation(o);
                endPoint = getLocation(d);
                startAddress = o;
                destAddress = d;

                if (startPoint != null && endPoint != null) {
                    getRoadAsync(startPoint, endPoint);

                    mapController.animateTo(startPoint);

                } else {
                    Toast.makeText(LocationViewActivity.this, "Fail to find a route !", Toast.LENGTH_LONG).show();
                }
            }
        }
                .start();
    }

    /**
     * Gets location.
     *
     * @param location the location
     * @return the location
     */
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

    /**
     * The type Update road task.
     */
    class UpdateRoadTask extends AsyncTask<Object, Void, Road[]> {

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
            Polyline roadPolyline = RoadManager.buildRoadOverlay(path);
            roadPolyline.setTitle(getString(R.string.app_name) + " - " + routeDesc);
            roadPolyline.setInfoWindow(new BasicInfoWindow(org.osmdroid.bonuspack.R.layout.bonuspack_bubble, map));
            mapOverlays.add(0, roadPolyline);
            setStartMarker(startPoint);
            setEndMarker();
            map.invalidate();
        }
    }


    /**
     * This function clears the map
     */
    private void clearMap(){
        overlayList.clear();
        map.invalidate();

        Touchy t = new Touchy();
        overlayList = map.getOverlays();
        overlayList.add(t);
    }

    /**
     * This function reverse geocodes. Address -> Geopoints
     * @param latitude
     * @param longitude
     * @return
     */
    private String getTheAddress(double latitude, double longitude){
        String theAddress;
        try {
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());

            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
            StringBuilder sb = new StringBuilder();
            if(addresses.size() > 0) {
                Address address = addresses.get(0);

                    sb.append(address.getAddressLine(0));
                }
                theAddress = new String(sb);


        } catch (Exception e) {
            theAddress = null;
        }
        return theAddress;
    }


}
