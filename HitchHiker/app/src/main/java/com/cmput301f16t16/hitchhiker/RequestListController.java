package com.cmput301f16t16.hitchhiker;

/**
 * Created by Angus on 11/3/2016.
 */

import android.util.Log;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

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

    /**
     * Remove request.
     * @param requestId the request id
     */
    public void removeRequest(String requestId) {
        ElasticsearchRequestController.DeleteRequestTask deleteRequestTask = new ElasticsearchRequestController.DeleteRequestTask();
        deleteRequestTask.setItemId(requestId);
        deleteRequestTask.execute();
    }

    /**
     * Get list of request array list.
     *  this returns a list of array by the username, it's only good populating Riders open request list
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
//
//    public ArrayList<Request> setListOfDriveRequests(ArrayList requestList){
//        ArrayList<Request> containsRequestList = new ArrayList<Request>();
//        containsRequestList.clear();
//        containsRequestList.addAll(requestList);
//        return containsRequestList;
//    }

//    public ArrayList<Request> getListOfDriverRequests(String status){
//        ArrayList<Request> requestsList = new ArrayList<Request>();
//        ElasticsearchRequestController.GetDriverTask getRequestsTask = new ElasticsearchRequestController.GetDriverTask();
//        getRequestsTask.setStatus(status);
//        getRequestsTask.execute("");
//        try {
//            requestsList = getRequestsTask.get();
//        }
//        catch (Exception e) {
//            Log.i("Error", "Failed to get the tweets out of the async object.");
//        }
//        return requestsList;
//    }







//    public ArrayList<Request> getDriverRequest(String status, String stat){
//        ArrayList<Request> requestsList = new ArrayList<Request>();
//        ElasticsearchRequestController.GetDriverRequestTask getRequestsTask = new ElasticsearchRequestController.GetDriverRequestTask();
//        getRequestsTask.setPending(status);
//        getRequestsTask.setPending(stat);
//
//        getRequestsTask.execute("");
//        try {
//            requestsList = getRequestsTask.get();
//        }
//        catch (Exception e) {
//            Log.i("Error", "Failed to get the tweets out of the async object.");
//        }
//        return requestsList;
//    }







    /**
     * This will get a list of pending request, we must query on two constraints, based on the username a
     * nd the status being 2 or 3
     * @param userName, status
     * @return
     */
//    public ArrayList<Request> getListOfPendingRequest(String userName, String pending ){
//        ArrayList<Request> pendingList = new ArrayList<Request>();
//        ElasticsearchRequestController.GetPendingTask getPendingTask = new ElasticsearchRequestController.GetPendingTask();
//        try {
//            getPendingTask.setUserName(userName);
//            getPendingTask.setPending(pending);
//            getPendingTask.execute("");
//            pendingList = getPendingTask.get();
//        }
//        catch (Exception e) {
//            Log.i("Error", "Failed to get the tweets out of the async object.");
//        }
//        return pendingList;
//    }
//
//    public ArrayList<Request> getListOfAcceptedRequest(String userName, String accepted ){
//        ArrayList<Request> acceptedList = new ArrayList<Request>();
//        ElasticsearchRequestController.GetPendingTask getPendingTask = new ElasticsearchRequestController.GetPendingTask();
//        try {
//            getPendingTask.setUserName(userName);
//            getPendingTask.setAccepted(accepted);
//            getPendingTask.execute("");
//            acceptedList = getPendingTask.get();
//        }
//        catch (Exception e) {
//            Log.i("Error", "Failed to get the tweets out of the async object.");
//        }
//        return acceptedList;
//    }




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

    /**
     * Gets the DriverBrowseList
     *
     * @param driverName
     * @return requests that are pending and created
     */
    public ArrayList<Request> getBrowseRequest(String driverName){
        ArrayList<Request> finalBrowseList = new ArrayList<Request>();
        ArrayList<Request> browseList = new ArrayList<Request>();
        ElasticsearchRequestController.GetBrowse getBrowsingRequests = new ElasticsearchRequestController.GetBrowse();
        try {
            getBrowsingRequests.execute();
            browseList = getBrowsingRequests.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        ArrayList<Request> TempList = new ArrayList<Request>();

        TempList.addAll(browseList);
        for (Request request : TempList) {
            // makes a new tempDriver list for each request
            ArrayList<String> TempDriver = new ArrayList<String>();
            TempDriver.addAll(request.getProspectiveDrivers());
            Boolean yes = false;
            if ((request.getRiderName()).equals(driverName)){

            }
            else {
                if (TempDriver.size() == 0) {
                    finalBrowseList.add(request);
                } else {
                    Boolean exists = false;
                    for (String driver : TempDriver) {
                        if (driverName.equals(driver)) {
                            exists = true;
                        }
                    }
                    if (exists == false) {
                        finalBrowseList.add(request);
                    }
                }
            }
        }

        return finalBrowseList;
    }


    public ArrayList<Request> getCurrentRequest(String driverName){
        ArrayList<Request> finalCurrentList = new ArrayList<Request>();
        ArrayList<Request> currentList = new ArrayList<Request>();
        ElasticsearchRequestController.GetCurrent getCurrentRequests = new ElasticsearchRequestController.GetCurrent();
        try {
            getCurrentRequests.execute();
            currentList = getCurrentRequests.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        ArrayList<Request> TempList = new ArrayList<Request>();

        TempList.addAll(currentList);
        for (Request request : TempList) {
            // makes a new tempDriver list for each request
            ArrayList<String> TempDriver = new ArrayList<String>();
            TempDriver.addAll(request.getProspectiveDrivers());
            Boolean yes = false;
            if ((request.getRiderName()).equals(driverName)){

            }
            else {
                if (TempDriver.size() == 0) {
                    finalCurrentList.add(request);
                } else {
                    Boolean exists = false;
                    for (String driver : TempDriver) {
                        if (driverName.equals(driver)) {
                            exists = true;
                        }
                    }
                    if (exists) {
                        finalCurrentList.add(request);
                    }
                }
            }
        }

        return finalCurrentList;
    }

}
