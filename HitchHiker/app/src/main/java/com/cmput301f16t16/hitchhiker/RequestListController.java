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
    static public RequestList getRequestList() {
        if (requestList == null) {
            requestList = new RequestList();

            }

        return requestList;
    }

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

    public void removeRequest(String requestId) {
        ElasticsearchRequestController.DeleteRequestTask deleteRequestTask = new ElasticsearchRequestController.DeleteRequestTask();
        deleteRequestTask.setItemId(requestId);
        deleteRequestTask.execute();
    }

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

    public ArrayList<Request> getRequestLoad(User rider) {
        ArrayList<Request> requestLoad = new ArrayList<>();
        return requestLoad;
    }

}
