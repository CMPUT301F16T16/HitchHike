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

    /**
     * The Request list.
     */
    protected ArrayList<Request> requestList = null;

    /**
     * Instantiates a new Request list.
     */
    public RequestList() {
        ArrayList<Request> requestList = new ArrayList<Request>();
    }

    /**
     * Add.
     *
     * @param request the request
     */
    public void add(Request request) {
        requestList.add(request);
    }

    /**
     * Has request boolean.
     *
     * @param request the request
     * @return the boolean
     */
    public boolean hasRequest(Request request) {
        return requestList.contains(request);
    }

    /**
     * Delete.
     *
     * @param request the request
     */
    public void delete(Request request) {
        requestList.remove(request);
    }

    /**
     * Gets request.
     *
     * @param index the index
     * @return the request
     */
    public Request getRequest(int index) {
        return requestList.get(index);
    }

    /**
     * Gets requests.
     *
     * @return the requests
     */
    public Collection<Request> getRequests() {
        return requestList;
    }

    /**
     * Contains boolean.
     *
     * @param request the request
     * @return the boolean
     */
    public boolean contains(Request request) {
        return requestList.contains(request);
    }
}
