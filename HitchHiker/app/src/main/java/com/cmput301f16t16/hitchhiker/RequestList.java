package com.cmput301f16t16.hitchhiker;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Jae-yeon on 10/14/2016.
 * Edited by Angus Abels on 11/11/2016
 */
public class RequestList implements Serializable {
    private static final long serialVersionUID = 123L;
    protected ArrayList<Request> requestList = null;

    //RequestListManager doesnt need to save the listeners
    protected transient ArrayList<RequestListener> requestListeners = null;

    public void addRequest(Request request) {
        requestList.add(request);
        notifyListeners();
    }

    public RequestList() {
        requestList = new ArrayList<Request>();
        requestListeners = new ArrayList<RequestListener>();
    }

    private ArrayList<RequestListener> getRequestListeners() {
        if (requestListeners == null) {
            requestListeners = new ArrayList<RequestListener>();
        }
        return requestListeners;
    }

    public void notifyListeners() {
        for (RequestListener requestListener : getRequestListeners()) {
            requestListener.update();
        }
    }

    public void addRequestListener(RequestListener R) {
        getRequestListeners().add(R);
    }

    public void removeRequestListener(RequestListener R) {
        getRequestListeners().remove(R);
    }

    public boolean hasRequest(Request request) {

        return requestList.contains(request);
    }

    public void delete(Request request) {

        requestList.remove(request);
    }

    public Request pickRequest(int index) {

        return requestList.get(index);
    }

    public Collection<Request> getRequests() {
        return requestList;
    }

    public int size() {
        return requestList.size();
    }
}
