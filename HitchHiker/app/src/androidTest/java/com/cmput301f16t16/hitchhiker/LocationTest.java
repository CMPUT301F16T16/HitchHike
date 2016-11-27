package com.cmput301f16t16.hitchhiker;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Overlay;

import java.util.List;

/**
 * Created by spei on 11/27/16.
 */
public class LocationTest extends TestCase {
    private final GeoPoint gp = new GeoPoint(53.527452,-113.529679);
    private final String ad = "9107 116 St NW, Edmonton";
    private GeoPoint test;
    private double fare = 50;
    private double rate = 5;
    private double dis = 10;
    Location location;




    public void testGetLocation () {
        test = location.getLocation(ad);
        Assert.assertEquals(test,gp);

    }

    public void testFare () {
        Assert.assertEquals(fare, location.getFare(dis,rate));

    }


}
