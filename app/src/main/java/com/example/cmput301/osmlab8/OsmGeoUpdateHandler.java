package com.example.cmput301.osmlab8;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.widget.Toast;

import org.osmdroid.util.GeoPoint;

/**
 * Created by spei on 11/9/16.
 */
public class OsmGeoUpdateHandler implements LocationListener {

    private MainActivity mMapActivity;

    public OsmGeoUpdateHandler(MainActivity aMapActivity)
    {
        this.mMapActivity = aMapActivity;

    }

    @Override
    public void onLocationChanged(Location location)
    {
        Toast.makeText(mMapActivity,
                "latitude = " + location.getLatitude() * 1e6 + " longitude = " + location.getLongitude() * 1e6,
                Toast.LENGTH_SHORT).show();

        int latitude = (int) (location.getLatitude() * 1E6);
        int longitude = (int) (location.getLongitude() * 1E6);
        GeoPoint point = new GeoPoint(latitude, longitude);
        mMapActivity.updatePosition(point);
    }

    @Override
    public void onProviderDisabled(String provider)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void onProviderEnabled(String provider)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras)
    {
        // TODO Auto-generated method stub

    }
}
