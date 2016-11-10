package com.cmput301f16t16.hitchhiker;

<<<<<<< HEAD

import java.io.Serializable;
import java.util.ArrayList;

=======
import java.io.Serializable;
import java.util.ArrayList;

import io.searchbox.annotations.JestId;

>>>>>>> 1b56c156b5b8a7a5e64f53b7b252e6830816ea1d
/**
 * Created by Jae-yeon on 10/14/2016.
 * Edited by Angus on 11/3/2016
 */
<<<<<<< HEAD
public class Request extends Fare implements Serializable  {

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

    /**Need a Location A (start of where the rider is located)
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


    public Request(User requestCreator, Location pickUp, Location dropOff, Fare fare) {
        this.Rider = requestCreator;
        this.acceptedDriver = acceptedDriver;
        this.requestStatus = getRequestStatus();
        this.price = fare.getFare();
        this.pickUp = pickUp;
        this.dropOff = dropOff;
    }

//    public void setRequestStatus(int requestStatus) {
////        this.requestStatus = requestStatus;
//    }

    public int getRequestStatus() {
        return this.requestStatus;
    }

    public User getRider() {
        return this.Rider;
=======
public class Request implements Serializable{

    private static final long serialVersionUID = 25L;

    @JestId
    private String id;
    private Integer RequestId; //A separate ID from elasticSearch ID. This will be shown to both drivers and riders.
    private String requestStatus;
    private User acceptedDriver;
    private ArrayList<User> prospectiveDrivers;
    private String pickUp; // Will be changed to Location type
    private String dropOff; // Will be changed to Location type



    public Request(String pickUp, String dropOff) {
        this.pickUp = pickUp;
        this.dropOff = dropOff;
        this.requestStatus = "Not Completed";
    }

    @Override
    public String toString(){
        return this.pickUp + "\t ---> \t" + this.dropOff;
    }

    public int getRequestID() {
        return this.RequestId;
>>>>>>> 1b56c156b5b8a7a5e64f53b7b252e6830816ea1d
    }

    public Location getStartLocation() {
        return this.pickUp;
    }

    public Location getEndLocation() {
        return this.dropOff;
    }

<<<<<<< HEAD

    //private Location pickUp;
//    private Location dropOff;
////
    public double getPrice() {
        return this.price;
    }

    /**
     *When multiple drivers are adding the request
     */
    public void prospectiveDrivers() {
        //send rider notifications when a new driver has accepted his request
        this.requestStatus = PENDING;
    }

    public void RiderAcceptsRequest(User driver) {
        // Notifcation to driver that his requests has been accepted
        this.acceptedDriver = driver;
        this.requestStatus = ACCEPTED;
=======
    public String getTrip(){
        return this.pickUp + "\t ---> \t" + this.dropOff;
    }




    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
>>>>>>> 1b56c156b5b8a7a5e64f53b7b252e6830816ea1d
    }

    public void paidForDrive() {
        //NOTIFY driver that the payment has been recieved
        this.requestStatus = FINISHED;
    }

    public void cancleRequest() {
        //notify the drivers and rider that request has been cancled
        //delete requests from the list views via controller
        this.requestStatus = CANCLED;
    }

}
