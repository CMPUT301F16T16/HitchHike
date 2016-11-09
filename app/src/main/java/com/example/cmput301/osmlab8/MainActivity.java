package com.example.cmput301.osmlab8;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import org.osmdroid.api.IMapController;
import org.osmdroid.bonuspack.routing.MapQuestRoadManager;
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

public class MainActivity extends Activity {

    // Cmput 301 lab 8 based on open resources.
    // based on https://github.com/MKergall/osmbonuspack/wiki/Tutorial_0
    // October 25th, 2016
    // updated with http://stackoverflow.com/questions/38539637/osmbonuspack-roadmanager-networkonmainthreadexception
    // October 25th, 2016
    // answered by: yubaraj poudel



    final private double MAP_DEFAULT_LATITUDE = 53.52676;
    final private double MAP_DEFAULT_LONGITUDE = -113.52715;

    private MapView mMapView;
    private ResourceProxy mResourceProxy;
    private OsmMapsItemizedOverlay mItemizedOverlay;
    private MyLocationOverlay mMyLocationOverlay;
    private LocationManager locationManager;
    private OverlayItem lastPosition = null;
    private Order mOrder;
    OsmGeoUpdateHandler mUpdateHandler;
    private ArrayList<OverlayItem> mItems = new ArrayList<OverlayItem>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        map = (MapView) findViewById(R.id.map);
        map.setTileSource(TileSourceFactory.MAPNIK);
        map.setBuiltInZoomControls(true);
        map.setMultiTouchControls(true);
        map.setClickable(true);
        map.setUseDataConnection(false);

        /* location manager */
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        mUpdateHandler = new OsmGeoUpdateHandler(this);
        Location location = null;

        for (String provider : locationManager.getProviders(true))
        {
            location = locationManager.getLastKnownLocation(provider);
            if (location != null)
            {
                //location.setLatitude(MAP_DEFAULT_LATITUDE);
                //location.setLongitude(MAP_DEFAULT_LONGITUDE);
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, mUpdateHandler);
                break;
            }
        }

        //add car position
        if (location == null)
        {
            location = new Location(LocationManager.GPS_PROVIDER);
            location.setLatitude(MAP_DEFAULT_LATITUDE);
            location.setLongitude(MAP_DEFAULT_LONGITUDE);
            updatePosition(new GeoPoint(location));
        }



        GeoPoint startPoint = new GeoPoint(48.13, -1.63);
        IMapController mapController = map.getController();
        mapController.setZoom(9);
        mapController.setCenter(startPoint);

        Marker startMarker = new Marker(map);
        startMarker.setPosition(startPoint);
        startMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        map.getOverlays().add(startMarker);

        GeoPoint destinationPoint = new GeoPoint(48.4, -1.9);





        // http://stackoverflow.com/questions/38539637/osmbonuspack-roadmanager-networkonmainthreadexception
        // accessed on October 27th, 2016
        // author: yubaraj poudel
        ArrayList<OverlayItem> overlayItemArray;
        overlayItemArray = new ArrayList<>();

        overlayItemArray.add(new OverlayItem("Starting Point", "This is the starting point", startPoint));
        overlayItemArray.add(new OverlayItem("Destination", "This is the detination point", destinationPoint));
        getRoadAsync(startPoint, destinationPoint);
    }

    public void updatePosition(GeoPoint aPoint)
    {
        if (mItemizedOverlay == null)
        {
            return;
        }
        OverlayItem overlayItem;

        /* remove last position marker */
        removeLastPosition(lastPosition);

        overlayItem = new OverlayItem("Center", "Center", (GeoPoint) aPoint);

        lastPosition = overlayItem;

        mItemizedOverlay.addOverlay(overlayItem);


        map.getOverlays().add(mItemizedOverlay);
        map.getController().animateTo(aPoint);

    }


    Activity ourActivity = this;
    MapView map;
    Road[] mRoads;

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
            RoadManager roadManager = new MapQuestRoadManager("L1fY0M61AxWmso7ZUmsjZEtILXjzU3A6");
            return roadManager.getRoads(waypoints);
        }

        @Override
        protected void onPostExecute(Road[] roads) {
            mRoads = roads;
            if (roads == null)
                return;
            if (roads[0].mStatus == Road.STATUS_TECHNICAL_ISSUE)
                Toast.makeText(map.getContext(), "Technical issue when getting the route", Toast.LENGTH_SHORT).show();
            else if (roads[0].mStatus > Road.STATUS_TECHNICAL_ISSUE) //functional issues
                Toast.makeText(map.getContext(), "No possible route here", Toast.LENGTH_SHORT).show();
            Polyline[] mRoadOverlays = new Polyline[roads.length];
            List<Overlay> mapOverlays = map.getOverlays();
            for (int i = 0; i < roads.length; i++) {
                Polyline roadPolyline = RoadManager.buildRoadOverlay(roads[i]);
                mRoadOverlays[i] = roadPolyline;
                String routeDesc = roads[i].getLengthDurationText(ourActivity, -1);
                roadPolyline.setTitle(getString(R.string.app_name) + " - " + routeDesc);
                roadPolyline.setInfoWindow(new BasicInfoWindow(org.osmdroid.bonuspack.R.layout.bonuspack_bubble, map));
                roadPolyline.setRelatedObject(i);
//                roadPolyline.setOnClickListener(new RoadOnClickListener());
                mapOverlays.add(1, roadPolyline);
                //selectRoad(0);
//                map.invalidate();
                //we insert the road overlays at the "bottom", just above the MapEventsOverlay,
                //to avoid covering the other overlays.
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    public GeoPoint getCurrentLocation() {
        return currentLocation;
    }
}
