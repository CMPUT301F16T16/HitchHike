package com.cmput301f16t16.hitchhiker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.osmdroid.api.IMapController;
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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class driverViewMap extends AppCompatActivity implements Serializable {
    private List<Overlay> overlayList;
    private LocationManager lm;
    private String towers;
    private GeoPoint currentPoint;
    private GeoPoint startPoint;
    private GeoPoint endPoint;
    private Double distance;
    private Request request;
    private int lat, longi;


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
        map.setTileSource(TileSourceFactory.MAPNIK);
        map.setBuiltInZoomControls(true);
        map.setMultiTouchControls(true);
        /**
         * Using the geopoint coordinates until we can create REAL requests
         */
        startPoint = request.getStartGeo();
        endPoint = request.getEndGeo();
        mapController = map.getController();
        mapController.setZoom(9);
        mapController.setCenter(startPoint);
//        ArrayList<OverlayItem> overlayItemArray;
//        overlayItemArray = new ArrayList<>();
//        overlayItemArray.add(new OverlayItem("Starting Point", "This is the starting point", startPoint));
//        overlayItemArray.add(new OverlayItem("Destination", "This is the detination point", endPoint));
        if (startPoint != null && endPoint != null) {
            getRoadAsync(startPoint, endPoint);
            mapController.animateTo(startPoint);
            mapController.animateTo(endPoint);
        }
    }

    // http://stackoverflow.com/questions/38539637/osmbonuspack-roadmanager-networkonmainthreadexception
    // accessed on October 27th, 2016
    // author: yubaraj poudel

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
}
