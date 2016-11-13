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
    @JestId
    // going to just grab the usename
    private String Rider;
    private String acceptedDriver = null;
    //Need a list of prospective Drivers to choose Final Driver Choice
    private ArrayList<User> prospectiveDrivers;
    private String pickUp;
    private String dropOff;
    // Fare is calculated and must be accepted before request is complete
    private double price;
   // requestStatus
    static final int CREATED = 1;
    static final int PENDING = 2;
    static final int ACCEPTED = 3;
    static final int FINISHED = 4;
    static final int CANCLED = 5;
    static int requestStatus; //Always starts at one when a request is created

    private Integer RequestId; //A separate ID from elasticSearch ID. This will be shown to both drivers and riders.
    private String id;

    public Request(String requestCreator, String pickUp, String dropOff, double price) { //}, Fare fare) {
        this.Rider = requestCreator;
        this.acceptedDriver = acceptedDriver;
        this.requestStatus = CREATED;
        this.price = getPrice();
        this.pickUp = pickUp;
        this.dropOff = dropOff;
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

    public String getTrip() {
        return this.pickUp + "\t ---> \t" + this.dropOff;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

//    public User getRider() {
//        return this.Rider;
//    }
}