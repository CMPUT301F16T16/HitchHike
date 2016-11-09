package com.cmput301f16t16.hitchhiker;


import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Jae-yeon on 10/14/2016.
 * Edited by Angus on 11/3/2016
 */
public class Request implements Serializable {

    private static long serialVersionUID = 44L; // need this to access a same request from diff screens


    /**
     * instead of just grabbing the ID's driver and rider we can bring in the whole objects
     * and use what info we need
     */
    private User Rider;
    private User acceptedDriver;


    /**
     * Need a list of prospective Drivers to choose Final Driver Choice
     */
    private ArrayList<User> prospectiveDrivers;

    /**Need a Location A (start of where the rider is located)
     * and a Location B (End of the ride is located)
     */

    private Location pickUp;
    private Location dropOff;

    /**
     * Fare is calculated and must be accepted before request is complete
     */

    private int fare;
    /**
     * need some variables types (int maybe?) to know different states of request:
     * 1) a user has created a request but no drivers accepted yet
     * 2) atleast one or more drivers have offered to give the rider a ride
     * 3) A rider has chosen a specific driver for the ride
     * 4) A rider has reached the location (B) from (A) and has paid
     * 5) A canceled request
     */
    private int requestStatus = 1; //Always starts at one when a request is created


    public Request(User requestCreator, Location pickUp, Location dropOff) {
        this.Rider = requestCreator;
        this.pickUp = pickUp;
        this.dropOff = dropOff;
    }



    public void setRequestStatus(int requestStatus) {
//        this.requestStatus = requestStatus;
    }

    public int getRequestStatus() {
        return this.requestStatus;
    }

    public User getRider() {
        return this.Rider;
    }

    public Location getStartLocation() {
        return this.pickUp;
    }

    public Location getEndLocation() {
        return this.dropOff;
    }

//    public void acceptDriver() {
//        this.requestStatus = "Accepted";
//    }
//
//    public void completeRequest() {
//        this.requestStatus = "Completed";
//    }
//
//    public void driverAcceptsRequest() {
//        this.requestStatus = "Driver Accepted";
//    }
}
