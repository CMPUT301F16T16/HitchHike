package com.cmput301f16t16.hitchhiker;

/**
 * Created by Angus on 11/3/2016.
 */

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
    static private RequestList requestList = null;
    static public RequestList getRequestList() {
        if (requestList == null) {
//            try {
//                    requestList = RequestListManager.getRequestManager().loadRequestList();
//                    requestList.addRequestListener(new RequestListener() {
//                        @Override
//                        public void update() {
//                            saveRequestList();
//                        }
//                    });
                requestList = new RequestList();
//            }
//            catch (IOException e) {
//                e.printStackTrace();
//                throw new RuntimeException("Couldnt deserialize RequestList from RequestListManager");
//            }
//            catch (ClassNotFoundException e) {
//                e.printStackTrace();
//                throw new RuntimeException("Couldnt deserialize RequetList from RequestListManager");
//            }
        }
        return requestList;
    }
//
//    private static void saveRequestList() {
//        try {
//            RequestListManager.getRequestManager().saveRequestList(getRequestList());
//        }
//        catch (IOException e) {
//            e.printStackTrace();
//            throw new RuntimeException("Couldnt deserialize RequestList from RequestListManager");
//        }
//    }


    public void addRequest(Request request) {

//        ElasticsearchRequestController.AddRequestsTask addRequestTask = new ElasticsearchRequestController.AddRequestsTask();
//        addRequestTask.execute(request);
//        return null;

//        getRequestList().addRequest(request);
    }

    public void removeRequest(Request request) {
        //remove request from browsingList and
        //requestList because of possibility of
        //canceling a request or A driver has accepted a offer
    }

//    public RequestList getRequests(User user) {
//        RequestList rl = new RequestList();
//        if (requestList != null){
//            for (Request request : requestList) {
//                if(request.getRider(user)== user.getUserName()){
//                    rl.addRequest(request);
//                }
//            }
//        }
//        return rl;

//    public ArrayList<Request> getRequestLoad(User rider) {
//        ArrayList<Request> requestLoad = new ArrayList<>();
//        for(Request request : requestLoad) {
//            if(request.getRider(rider) == rider.getUserName()) {
//                requestLoad.add(request);
//            }
//        }
//        return requestLoad;
//    }

}
