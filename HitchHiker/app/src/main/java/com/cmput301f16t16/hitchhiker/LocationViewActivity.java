package com.cmput301f16t16.hitchhiker;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.*;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
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

import java.util.ArrayList;
import java.util.List;

/**
 * The type Location view activity.
 */
public class LocationViewActivity extends Activity {

    private Request request;
    private String requestId;

    private LocationManager lm;
    private String towers;
    private int lat, longi;
    private GeoPoint currentPoint;
    private GeoPoint startPoint;
    private GeoPoint endPoint;
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
        setContentView(R.layout.activity_location_view);

//        request = (Request) getIntent().getSerializableExtra("request");
//        requestId = request.getId();
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
        GeoPoint startPoint = new GeoPoint(48.13, -1.63);
        GeoPoint endPoint = new GeoPoint(48.4, -1.9);

        IMapController mapController = map.getController();
        mapController.setZoom(9);
        mapController.setCenter(startPoint);

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
            setMarker(currentPoint);
            mapController.animateTo(currentPoint);


        } else {
            Toast.makeText(LocationViewActivity.this, "Couldn't get provider", Toast.LENGTH_SHORT).show();
        }


        // http://stackoverflow.com/questions/38539637/osmbonuspack-roadmanager-networkonmainthreadexception
        // accessed on October 27th, 2016
        // author: yubaraj poudel
        ArrayList<OverlayItem> overlayItemArray;
        overlayItemArray = new ArrayList<>();

        overlayItemArray.add(new OverlayItem("Starting Point", "This is the starting point", startPoint));
        overlayItemArray.add(new OverlayItem("Destination", "This is the detination point", endPoint));
        getRoadAsync(startPoint, endPoint);
    }

    /**
     * Sets marker.
     *
     * @param sp the sp
     */
    public void setMarker(GeoPoint sp) {
        Marker startMarker = new Marker(map);
        startMarker.setPosition(sp);
        startMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        map.getOverlays().clear();
        map.getOverlays().add(startMarker);
        map.invalidate();
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
            for (Road road: roads) {
                if(road.mLength < min || min == 0){
                    min = road.mLength;
                    path = road;
                }
            }
            String routeDesc = path.getLengthDurationText(ourActivity, -1);
            Polyline roadPolyline = RoadManager.buildRoadOverlay(path);
            roadPolyline.setTitle(getString(R.string.app_name) + " - " + routeDesc);
            roadPolyline.setInfoWindow(new BasicInfoWindow(org.osmdroid.bonuspack.R.layout.bonuspack_bubble, map));
            mapOverlays.add(0, roadPolyline);
            map.invalidate();
        }
    }

    /**
     * Gets start point.
     *
     * @return the start point
     */
    public GeoPoint getStartPoint() {
        return startPoint;
    }

    /**
     * Gets end point.
     *
     * @return the end point
     */
    public GeoPoint getEndPoint() {
        return endPoint;
    }

    /**
     * Gets current point.
     *
     * @return the current point
     */
    public GeoPoint getCurrentPoint() {
        return currentPoint;
    }
}
