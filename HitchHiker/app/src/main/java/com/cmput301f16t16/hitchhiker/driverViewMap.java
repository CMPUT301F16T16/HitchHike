package com.cmput301f16t16.hitchhiker;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.location.Address;
import android.location.Criteria;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
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

/**
 * The type Driver view map.
 * <p> This View will allow the driver to see the exact start and end point
 * of the request they are looking at.</p>
 * @author willyliao
 */
public class driverViewMap extends AppCompatActivity implements Serializable {
    private List<Overlay> overlayList;
    private Location location;
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
    private double rate = 5;
    private double fare;
    private Request request;

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
        setContentView(R.layout.activity_driver_view_map);

        request = (Request) getIntent().getSerializableExtra("request");


        map = (MapView) findViewById(R.id.mapDriverView);
        overlayList = map.getOverlays();
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

        startPoint = request.getStartGeo();
        endPoint = request.getEndGeo();

        // http://stackoverflow.com/questions/38539637/osmbonuspack-roadmanager-networkonmainthreadexception
        // accessed on October 27th, 2016
        // author: yubaraj poudel
        ArrayList<OverlayItem> overlayItemArray;
        overlayItemArray = new ArrayList<>();

        overlayItemArray.add(new OverlayItem("Starting Point", "This is the starting point", startPoint));
        overlayItemArray.add(new OverlayItem("Destination", "This is the detination point", endPoint));

        if (startPoint != null && endPoint != null) {
            getRoadAsync(startPoint, endPoint);

            mapController.animateTo(startPoint);

        } else {
            Toast.makeText(driverViewMap.this, "Fail to find a route !", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Sets start marker.
     * <p> Using omsDroids code, and from CMPUT301 Lab.</p>
     * <p> This will set the startPoint on the map.</p>
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
     * <p> Using omsDroids code, and from CMPUT301 Lab.</p>
     * <p> This will set the endPoint on the map.</p>
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
     * Gets route.
     *
     * @param view the view
     */
//  ROUTE
    public void getRoute(View view) {
        new Thread() {

            public void run() {
                if (startPoint != null && endPoint != null) {
                    getRoadAsync(startPoint, endPoint);
                    mapController.animateTo(startPoint);
                } else {
                    Toast.makeText(driverViewMap.this, "Fail to find a route !", Toast.LENGTH_LONG).show();
                }
            }
        }
                .start();
    }

    /**
     * Gets location.
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
     * <p> This will get the route of the trip.</p>
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
}
