package com.cmput301f16t16.hitchhiker;

import java.util.ArrayList;

/**
 * Created by Jae-yeon on 10/14/2016.
 */
public class RequestList {
    private ArrayList<Request> requestList = new ArrayList<Request>();

    public void add(Request request) { requestList.add (request); }

    public boolean hasRequest(Request request) { return requestList.contains(request); }

    public void delete(Request request) { requestList.remove(request); }

    public Request getRequest(int index) { return requestList.get(index); }
}
