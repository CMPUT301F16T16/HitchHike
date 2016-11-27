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

    /**
     * Need a list of prospective Drivers to choose Final Driver Choice
     */

    //    private ArrayList<User> prospectiveDrivers;
    private ArrayList<String> prospectiveDrivers;

    /**
     * Need a Location A (start of where the rider is located)
     * and a Location B (End of the ride is located)
     */
    private String pickUp;
    private String dropOff;

    /**
     * Fare is calculated and must be accepted before request is complete
     */
    private Double price;
    /**
     * need some variables types (int maybe?) to know different states of request:
     * 1) a user has created a request but no drivers accepted yet CREATED = 1
     * 2) atleast one or more drivers have offered to give the rider a ride
     * 3) A rider has chosen a specific driver for the ride
     * 4) A rider has reached the location (B) from (A) and has paid
     * 5) A canceled request
     */

    private String requestStatus;

    private Integer RequestId; //A separate ID from elasticSearch ID. This will be shown to both drivers and riders.

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
     * @return the GeoPoint
     */
    public GeoPoint getStartGeo() {return this.start;}

    /**
     * Get price double.
     *
     * @return the double
     */
    public Double getPrice(){
        return this.price;
    }

    public void addProspectiveDriver(String driverName){
        this.prospectiveDrivers.add(driverName);
    }

    public String getTrip() {
        return this.pickUp + "\t ---> \t" + this.dropOff;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getRiderName(){
        return this.Rider;
    }

    public String getPickUp() {
        return pickUp;
    }

    public String getDropOff() {
        return dropOff;
    }

    public ArrayList<String> getProspectiveDrivers() {
        return prospectiveDrivers;
    }
    public void setDriver(String Driver){
        this.Driver = Driver;
    }
}