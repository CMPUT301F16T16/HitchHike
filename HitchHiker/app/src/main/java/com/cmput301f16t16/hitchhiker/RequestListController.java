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
    private static RequestList requestList = null;

    static public RequestList getRequestList() {
        if (requestList == null) {
            requestList = new RequestList();
        }
//            try {
//                requestList = new RequestList();
//                requestList = RequestListManager.getRequestManager().loadRequestList();
//                requestList.addRequestListener(new RequestListener() {
//                    @Override
//                    public void update() {
//                        saveRequestList();
//                    }
//                });
//            }
//            catch (IOException e) {
//                e.printStackTrace();
//                throw new RuntimeException("Couldnt deserialize HabitList from RequestListManager");
//            }
//            catch (ClassNotFoundException e) {
//                e.printStackTrace();
//                throw new RuntimeException("Couldnt deserialize RequetList from HabitListManager");
//            }
//        }
        return requestList;
    }

//    private static void saveRequestList() {
//        try {
//            RequestListManager.getRequestManager().saveRequesttList(getRequestList());
//        }
//        catch (IOException e) {
//            e.printStackTrace();
//            throw new RuntimeException("Couldnt deserialize HabitList from HabitListManager");
//        }
//    }


    public void addRequest(Request request) {
        requestList.addR(request);
        ElasticsearchRequestController.AddRequestsTask addRequestsTask = new ElasticsearchRequestController.AddRequestsTask();
        addRequestsTask.execute(request);

    }

    public void removeRequest(Request request) {
        //remove request from browsingList and
        //requestList because of possibility of
        //canceling a request or A driver has accepted a offer
    }

    public ArrayList<Request> getRequestLoad(User rider) {
        ArrayList<Request> requestLoad = new ArrayList<>();
//        for(Request request : requestList) {
//            if(request.getRider() == rider) {
//                requestLoad.add(request);
//            }
//        }
        return requestLoad;
    }

}
