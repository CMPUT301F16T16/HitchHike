package com.cmput301f16t16.hitchhiker;

import java.util.ArrayList;

import io.searchbox.annotations.JestId;

/**
 * Created by Jae-yeon on 10/14/2016.
 */
public class Request {

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
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    public String getRequestStatus() {
        return this.requestStatus;
    }







    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
