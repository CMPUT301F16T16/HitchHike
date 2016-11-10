package com.cmput301f16t16.hitchhiker;


import java.io.Serializable;
import java.util.ArrayList;
import java.io.Serializable;
import java.util.ArrayList;
import io.searchbox.annotations.JestId;

/**
 * Created by Jae-yeon on 10/14/2016.
 * Edited by Angus on 11/3/2016
 */

public class Request extends Fare implements Serializable {

    private static long serialVersionUID = 44L; // need this to access a same request from diff screens


    /**
     * instead of just grabbing the ID's driver and rider we can bring in the whole objects
     * and use what info we need
     */
    private User Rider;
    private User acceptedDriver = null;


    /**
     * Need a list of prospective Drivers to choose Final Driver Choice
     */
    private ArrayList<User> prospectiveDrivers;
    //    private ArrayList<User> prospectiveDrivers;

    /**
     * Need a Location A (start of where the rider is located)
     * and a Location B (End of the ride is located)
     */

    private Location pickUp;
    private Location dropOff;

    /**
     * Fare is calculated and must be accepted before request is complete
     */

    private double price;
    /**
     * need some variables types (int maybe?) to know different states of request:
     * 1) a user has created a request but no drivers accepted yet CREATED = 1
     * 2) atleast one or more drivers have offered to give the rider a ride
     * 3) A rider has chosen a specific driver for the ride
     * 4) A rider has reached the location (B) from (A) and has paid
     * 5) A canceled request
     */
    static final int CREATED = 1;
    static final int PENDING = 2;
    static final int ACCEPTED = 3;
    static final int FINISHED = 4;
    static final int CANCLED = 5;
    static int requestStatus = CREATED; //Always starts at one when a request is created

    @JestId

    private Integer RequestId; //A separate ID from elasticSearch ID. This will be shown to both drivers and riders.
    private String id;

    public Request(User requestCreator, Location pickUp, Location dropOff, Fare fare) {
        this.Rider = requestCreator;
        this.acceptedDriver = acceptedDriver;
        this.requestStatus = getRequestStatus();
        this.price = fare.getFare();
        this.pickUp = pickUp;
        this.dropOff = dropOff;
    }


    public int getRequestStatus() {
        return this.requestStatus;
    }

    //    public User getRider() {
//        return this.Rider;
//
//
//        public Request(String pickUp, String dropOff) {
//            this.pickUp = pickUp;
//            this.dropOff = dropOff;
//            this.requestStatus = "Not Completed";
//        }
//
//        @Override
//        public String toString () {
//            return this.pickUp + "\t ---> \t" + this.dropOff;
//        }
//    }
    public int getRequestID() {
        return this.RequestId;
    }

    public Location getStartLocation() {
        return this.pickUp;
    }

    public Location getEndLocation() {
        return this.dropOff;
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
}