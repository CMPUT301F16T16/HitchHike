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

public class Request implements Serializable {

    private static long serialVersionUID = 44L; // need this to access a same request from diff screens


    @JestId
    private String id;

    /**
     * instead of just grabbing the ID's driver and rider we can bring in the whole objects
     * and use what info we need
     */
//    private User Rider;
//    private User acceptedDriver = null;

    private String Rider;
    private String acceptedDriver = null;
    //Need a list of prospective Drivers to choose Final Driver Choice
    private ArrayList<User> prospectiveDrivers;
    private String pickUp;
    private String dropOff;
    private double price;

   // requestStatus
    static final int CREATED = 1;
    static final int PENDING = 2;
    static final int ACCEPTED = 3;
    static final int FINISHED = 4;
    static final int CANCLED = 5;
    static int requestStatus; //Always starts at one when a request is created

    private Integer RequestId; //A separate ID from elasticSearch ID. This will be shown to both drivers and riders.



    public Request(String requestCreator, String pickUp, String dropOff, Double price) {
        this.Rider = requestCreator;
            this.acceptedDriver = acceptedDriver;
        this.pickUp = pickUp;
        this.dropOff = dropOff;
        this.price = price;
        this.requestStatus = CREATED;
    }

    public int getRequestStatus() { return this.requestStatus; }

    public int getRequestID() {
        return this.RequestId;
    }

    public double getPrice(){
        return this.price;
    }

    public String getStartLocation() {
        return this.pickUp;
    }

    public String getEndLocation() {
        return this.dropOff;
    }

//    public Location getStartLocation() {
//        return this.pickUp;
//    }
//
//    public Location getEndLocation() {
//        return this.dropOff;
//    }


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

}