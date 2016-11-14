package com.example.cmput301.osmlab8;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import org.osmdroid.api.IMapController;
import org.osmdroid.bonuspack.location.GeocoderNominatim;
import org.osmdroid.bonuspack.routing.MapQuestRoadManager;
import org.osmdroid.bonuspack.routing.OSRMRoadManager;
import org.osmdroid.bonuspack.routing.Road;
import org.osmdroid.bonuspack.routing.RoadManager;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.Overlay;
import org.osmdroid.views.overlay.OverlayItem;
import org.osmdroid.views.overlay.Polyline;
import org.osmdroid.views.overlay.infowindow.BasicInfoWindow;

import java.io.IOException;
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

    private GeoPoint currentPoint;

    private GeoPoint startPoint;
    private GeoPoint endPoint;
    private long start;
    private long stop;
    private int x,y,lat,longi;
    private GeoPoint touchedPoint;

    private List<Overlay> overlayList;
    private  LocationManager lm;
    private String towers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        map = (MapView) findViewById(R.id.map);
        map.setTileSource(TileSourceFactory.MAPNIK);
        map.setBuiltInZoomControls(true);
        map.setMultiTouchControls(true);


        startPoint = new GeoPoint(48.13, -1.63);
        endPoint = new GeoPoint(48.4, -1.9);




        IMapController mapController = map.getController();
        mapController.setZoom(9);
        mapController.setCenter(startPoint);






        Touchy t = new Touchy();
        overlayList = map.getOverlays();
        overlayList.add(t);





        //  setMarker(startPoint);




        // http://stackoverflow.com/questions/38539637/osmbonuspack-roadmanager-networkonmainthreadexception
        // accessed on October 27th, 2016
        // author: yubaraj poudel
        ArrayList<OverlayItem> overlayItemArray;
        overlayItemArray = new ArrayList<>();

        overlayItemArray.add(new OverlayItem("Starting Point", "This is the starting point", startPoint));
        overlayItemArray.add(new OverlayItem("Destination", "This is the detination point", endPoint));
//        getRoadAsync(startPoint, endPoint);
    }

//    public void setMarker(GeoPoint sp) {
//        Marker startMarker = new Marker(map);
//        startMarker.setPosition(sp);
//        startMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
//        map.getOverlays().clear();
//        map.getOverlays().add(startMarker);
//        map.invalidate();
//    }


//  ROUTE
//    public void getRoute (View view) {
//
//        EditText etOrigin = (EditText) findViewById(R.id.origin);
//        EditText etDestination = (EditText) findViewById(R.id.destination);
//
//        final String o = etOrigin.getText().toString();
//        final String d = etDestination.getText().toString();
//
//        new Thread() {
//
//            public void run() {
//                GeoPoint startPoint = getLocation(o);
//                GeoPoint endPoint = getLocation(d);
//
//                if (startPoint != null && endPoint != null) {
//
//                    getRoadAsync(startPoint, endPoint);
//                }
//                else {
//                    Toast.makeText(MainActivity.this, "Fail!", Toast.LENGTH_LONG).show();
//                }
//            }
//        }.start();
//    }
//
//    public GeoPoint getLocation(String location) {
//
//        GeocoderNominatim gn = new GeocoderNominatim(MainActivity.this);
//        GeoPoint gp = null;
//
//        ArrayList<Address> al;
//        try{
//            al = (ArrayList<Address>) gn.getFromLocationName(location, 1);
//
//            if (al != null && al.size() > 0) {
//                Log.i("Script", "Street: "+al.get(0).getThoroughfare());
//                Log.i("Script", "City: "+al.get(0).getSubAdminArea());
//                Log.i("Script", "Province: "+al.get(0).getAdminArea());
//                Log.i("Script", "Country: "+al.get(0).getCountryName());
//
//                gp = new GeoPoint(al.get(0).getLatitude(), al.get(0).getLongitude());
//            }
//        }
//        catch (IOException e) {e.printStackTrace();}
//
//        return (gp);
//    }


    private class Touchy extends Overlay {


        @Override
        protected void draw(Canvas canvas, MapView mapView, boolean b) {

        }

        public boolean onTouchEvent(MotionEvent e, MapView m) {
            if (e.getAction() == MotionEvent.ACTION_DOWN) {
                start = e.getEventTime();
                x = (int) e.getX();
                y = (int) e.getY();
                touchedPoint = (GeoPoint) map.getProjection().fromPixels(x,y);
            }

            if (e.getAction() == MotionEvent.ACTION_UP) {
                stop = e.getEventTime();
            }

            if (stop - start > 1500) {
                AlertDialog alert = new AlertDialog.Builder(MainActivity.this).create();
                alert.setTitle("Pick an Option");
                alert.setMessage("Your current location will be your default start point");
                alert.setButton(DialogInterface.BUTTON_POSITIVE, "Set SatrtPoint", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        // TODO Auto-generated method stub
                        startPoint = new GeoPoint(touchedPoint.getLatitude(), touchedPoint.getLongitude());
                        Toast.makeText(MainActivity.this, "Set Start Point", Toast.LENGTH_LONG).show();
                    }
                    //setStartPoint(touchedpoint);
                    //Toast.makeText(MainActivity.this, "Set Start Point", Toast.LENGTH_LONG).show();

                });
                alert.setButton(DialogInterface.BUTTON_NEGATIVE, "Set Destination", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        // TODO Auto-generated method stub
                        endPoint = new GeoPoint(touchedPoint.getLatitude(), touchedPoint.getLongitude());
//                        map.getOverlays().clear();
                        getRoadAsync(startPoint, endPoint);
                        map.invalidate();
                        Toast.makeText(MainActivity.this, "Set destination", Toast.LENGTH_LONG).show();
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



}


