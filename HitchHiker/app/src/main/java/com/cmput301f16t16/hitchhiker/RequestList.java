package com.cmput301f16t16.hitchhiker;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Jae-yeon on 10/14/2016.
 * Edited by Angus 11/11/2016
 * Edited by Angus Abels 11/14/2016
 */


public class RequestList implements Serializable {

    private static final long serialVersionUID = 12L;

    protected ArrayList<Request> requestList = null;

    public RequestList() {
        ArrayList<Request> requestList = new ArrayList<Request>();
    }

    public void add(Request request) {
        requestList.add(request);
    }

    public boolean hasRequest(Request request) {
        return requestList.contains(request);
    }

    public void delete(Request request) {
        requestList.remove(request);
    }

    public Request getRequest(int index) {
        return requestList.get(index);
    }

    public Collection<Request> getRequests() {
        return requestList;
    }

    public boolean contains(Request request) {
        return requestList.contains(request);
    }
}
