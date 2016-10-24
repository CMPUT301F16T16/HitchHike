package com.cmput301f16t16.hitchhiker;

/**
 * Created by Jae-yeon on 10/14/2016.
 */
public class Request {
    private Integer RID; //RID = Request ID
    private String requestStatus;
    public Request(int RID) {
        this.RID = RID;
        this.requestStatus = new String(); // Maybe change it to another type later?
    }

    public int getRequestID() {
        return this.RID;
    }

    public void setRequestID(Integer RID) {
        this.RID = RID;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    public String getRequestStatus() {
        return this.requestStatus;
    }

    public void acceptDriver() {
        this.requestStatus = "Accepted";
    }

    public void completeRequest() {
        this.requestStatus = "Completed";
    }
}
