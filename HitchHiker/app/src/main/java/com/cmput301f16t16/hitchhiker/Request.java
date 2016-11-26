package com.cmput301f16t16.hitchhiker;


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
    private int CREATED = 1;
    /**
     * The Pending.
     */
    private int PENDING = 2;
    /**
     * The Accepted.
     */
    private int ACCEPTED = 3;
    /**
     * The Finished.
     */
    private int FINISHED = 4;


    private int requestStatus;

    private Integer RequestId; //A separate ID from elasticSearch ID. This will be shown to both drivers and riders.

    public String getDriver() {
        return Driver;
    }

    public void setDriver(String driver) {
        Driver = driver;
    }

    /**
     * Instantiates a new Request.
     *
     * @param requestCreator the request creator
     * @param pickUp         the pick up
     * @param dropOff        the drop off
     * @param price          the price
     */
    public Request(String requestCreator, String pickUp, String dropOff, Double price, String driver) {
        this.Rider = requestCreator;
        this.pickUp = pickUp;
        this.dropOff = dropOff;
        this.price = price;
        this.requestStatus = CREATED;
        this.prospectiveDrivers = new ArrayList<String>();
        this.Driver = driver;

    }

    /**
     * Gets request status.
     *
     * @return the request status
     */
    public int getRequestStatus() {
        return this.requestStatus;
    }

    @Override
    public String toString () {
        return this.pickUp + "\t ---> \t" + this.dropOff;
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

    public void setPENDING( int PENDING){
        this.PENDING = PENDING;
    }

    public void setACCEPTED(int ACCEPTED) {
        this.ACCEPTED = ACCEPTED;
    }

    public void setFINISHED(int FINISHED) {
        this.FINISHED = FINISHED;
    }


    public  int getPENDING() {
        return PENDING;
    }


    public int getACCEPTED() {
        return ACCEPTED;
    }

    public int getFINISHED() {
        return FINISHED;
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
}