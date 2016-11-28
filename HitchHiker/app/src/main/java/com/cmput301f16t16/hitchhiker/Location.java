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
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.osmdroid.api.IMapController;
import org.osmdroid.bonuspack.location.GeocoderNominatim;
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

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Angus on 11/3/2016.
 * <p> This class is solely used to "package" and send data from the
 * MapView to the Create a Request View.</p>
 * @see LocationViewActivity
 * @see CreateRequestActivity
 */
public class Location implements Serializable {
    private GeoPoint currentPoint;
    private GeoPoint startPoint;
    private GeoPoint endPoint;
    private String stringStartPoint;
    private String stringEndPoint;
    private GeoPoint touchedPoint;
    private Double distance;
    private double fare;
    private double rate = 5;


    /**
     * Instantiates a new Location.
     *
     * @param startPoint       the start point
     * @param endPoint         the end point
     * @param stringStartPoint the string start point
     * @param stringEndPoint   the string end point
     * @param distance         the distance
     */
    public Location(GeoPoint startPoint, GeoPoint endPoint, String stringStartPoint, String stringEndPoint, Double distance) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.stringEndPoint = stringEndPoint;
        this.stringStartPoint = stringStartPoint;
        this.distance = distance;
    }

    /**
     * Gets start point.
     *
     * @return the start point
     */
    public GeoPoint getStartPoint() {
        if (startPoint != null){
            return startPoint;
        }
        else {
            return currentPoint;
        }
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
     * Gets the string Address of EndPoint
     *
     * @return the end point string address
     */
    public String getStringEndPoint() { return stringEndPoint;}

    /**
     * Gets string start point.
     *
     * @return the string start point
     */
    public String getStringStartPoint() {return stringStartPoint;}

    /**
     * Gets current point.
     *
     * @return the current point
     */
    public GeoPoint getCurrentPoint() {
        return currentPoint;
    }

    /**
     * gets distance
     *
     * @return distance distance
     */
    public Double getDistance() {
        return distance;
    }

    /**
     * get fare
     *
     * @return fare fare
     */
    public double getFare() {
        fare = distance*rate;

        return fare;
    }
}
