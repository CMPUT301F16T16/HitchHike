package com.cmput301f16t16.hitchhiker;

/**
 * Created by Angus on 11/3/2016.
 */

import android.util.Log;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * possibly change this to a request controller instead
 */
public class RequestListController {

    /*
    *Lazy Singleton talk too the activity and elastic search
     */
    private static RequestList requestList = null;

    /**
     * Gets request list.
     *
     * @return the request list
     */
    static public RequestList getRequestList() {
        if (requestList == null) {
            requestList = new RequestList();

            }

        return requestList;
    }

    /**
     * Add request string.
     *
     * @param request the request
     * @return the string
     */
    public String addRequest(Request request) {
        if (request.getStartLocation() == null || request.getEndLocation() == null)
        {
            return "something went wrong with your Request...";
        }
        if (request.getPrice() == null){
            return "You didnt enter a price...";
        }
        else {
            ElasticsearchRequestController.AddRequestsTask addRequestsTask = new ElasticsearchRequestController.AddRequestsTask();
            addRequestsTask.execute(request);
        }
        return null;
    }

    /**
     * Remove request.
     *
     * @param requestId the request id
     */
    public void removeRequest(String requestId) {
        ElasticsearchRequestController.DeleteRequestTask deleteRequestTask = new ElasticsearchRequestController.DeleteRequestTask();
        deleteRequestTask.setItemId(requestId);
        deleteRequestTask.execute();
    }

    /**
     * Get list of request array list.
     *
     * @param userName the user name
     * @return the array list
     */
    public ArrayList<Request> getListOfRequest(String userName){
        ArrayList<Request> requestsList = new ArrayList<Request>();
        ElasticsearchRequestController.GetRequestsTask getRequestsTask = new ElasticsearchRequestController.GetRequestsTask();
        getRequestsTask.setUserName(userName);
        getRequestsTask.execute("");
        try {
            requestsList = getRequestsTask.get();
        }
        catch (Exception e) {
            Log.i("Error", "Failed to get the tweets out of the async object.");
        }

        return requestsList;

    }

    /**
     * Gets request load.
     *
     * @param rider the rider
     * @return the request load
     */
    public ArrayList<Request> getRequestLoad(User rider) {
        ArrayList<Request> requestLoad = new ArrayList<>();
        return requestLoad;
    }

}
