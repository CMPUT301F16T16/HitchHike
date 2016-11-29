package com.cmput301f16t16.hitchhiker;


import org.osmdroid.util.GeoPoint;

import java.io.Serializable;
import java.util.ArrayList;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.searchbox.annotations.JestId;

/**
 * Created by Jae-yeon on 10/14/2016.
 * Edited by Angus on 11/3/2016
 */
public class Request implements Serializable {

    private static long serialVersionUID = 44L; // need this to access a same request from diff screens
    @JestId
    private String id;
    /**
     * instead of just grabbing the ID's driver and rider we can bring in the whole objects
     * and use what info we need
     */
    private String Rider;
    private String Driver;

    private ArrayList<String> prospectiveDrivers;
    private String pickUp;
    private String dropOff;
    private Double price;
    private String requestStatus;
    private Integer RequestId; //A separate ID from elasticSearch ID. This will be shown to both drivers and riders.

    /**
     * Gets driver.
     *
     * @return the driver
     */
    public String getDriver() {
        return Driver;
    }

    private GeoPoint start;
    private GeoPoint end;

    /**
     * Instantiates a new Request.
     *
     * @param requestCreator the request creator
     * @param pickUp         the pick up
     * @param dropOff        the drop off
     * @param price          the price
     * @param start          the start
     * @param end            the end
     */
    public Request(String requestCreator, String pickUp, String dropOff, Double price, GeoPoint start, GeoPoint end) {
        this.Rider = requestCreator;
        this.pickUp = pickUp;
        this.dropOff = dropOff;
        this.price = price;
        this.requestStatus = "CREATED";
        this.prospectiveDrivers = new ArrayList<String>();
        this.start = start;
        this.end = end;

    }

    /**
     * Gets request status.
     *
     * @return the request status
     */
    public String getRequestStatus() {
        return this.requestStatus;
    }

    /**
     * Set request status.
     *
     * @param requestStatus the request status
     */
    public void setRequestStatus(String requestStatus){this.requestStatus = requestStatus; }

    @Override
    public String toString () {
        return this.pickUp + "\t ---> \t" + this.dropOff+"\t\t\t\t\t\t"+ this.requestStatus;
    }

    /**
     * Gets request id.
     *
     * @return the request id
     */
    public int getRequestID() {
        return this.RequestId;
    }

    /**
     * Get start location string.
     *
     * @return the string
     */
    public String getStartLocation(){
        return this.pickUp;
    }

    /**
     * Get end location string.
     *
     * @return the string
     */
    public String getEndLocation(){
        return this.dropOff;
    }

    /**
     * gets start location GeoPoint
     *
     * @return the GeoPoint
     */
    public GeoPoint getStartGeo() {return this.start;}
    public void setStartGeo(GeoPoint start) {this.start = start;}
    public void setEndGeo(GeoPoint end) {this.end = end;}

    /**
     * Sets price.
     *
     * @param price the price
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * Get price double.
     *
     * @return the double
     */
    public Double getPrice(){
        return this.price;
    }

    /**
     * Add prospective driver.
     *
     * @param driverName the driver name
     */
    public void addProspectiveDriver(String driverName){
        this.prospectiveDrivers.add(driverName);
    }

    /**
     * Gets trip.
     *
     * @return the trip
     */
    public String getTrip() {
        return this.pickUp + "\t ---> \t" + this.dropOff;
    }


    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(String id) {
        this.id = id;
    }


    /**
     * Get rider name string.
     *
     * @return the string
     */
    public String getRiderName(){
        return this.Rider;
    }

    /**
     * Gets pick up.
     *
     * @return the pick up
     */
    public String getPickUp() {
        return pickUp;
    }

    /**
     * Gets drop off.
     *
     * @return the drop off
     */
    public String getDropOff() {
        return dropOff;
    }

    /**
     * Gets prospective drivers.
     *
     * @return the prospective drivers
     */
    public ArrayList<String> getProspectiveDrivers() {
        return prospectiveDrivers;
    }

    /**
     * Set driver.
     *
     * @param Driver the driver
     */
    public void setDriver(String Driver){
        this.Driver = Driver;
    }

    /**
     * Gets end geo.
     *
     * @return the end geo
     */
    public GeoPoint getEndGeo() {
        return this.end;
    }

}