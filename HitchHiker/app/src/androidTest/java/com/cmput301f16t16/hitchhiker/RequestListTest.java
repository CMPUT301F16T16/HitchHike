package com.cmput301f16t16.hitchhiker;

import junit.framework.TestCase;

import java.util.Collection;

/**
 * Created by Jae-yeon on 10/14/2016.
 */

public class RequestListTest extends TestCase{
//    public void testAdd(){
//        RequestList requestList = new RequestList();
//        Request request = new Request(0);
//        requestList.add(request);
//        assertTrue(requestList.hasRequest(request));
//    }
//    public void testDelete() {
//        RequestList requestList = new RequestList();
//        Request request = new Request(0);
//        requestList.add(request);
//        requestList.delete(request);
//        assertFalse(requestList.hasRequest(request));
//    }
//    public void testHasRequest() {
//        RequestList requestList = new RequestList();
//        Request request = new Request(0);
//        requestList.add(request);
//        assertTrue(requestList.hasRequest(request));
//    }
    public void testGetRequest() {
        RequestList requestList = new RequestList();
        String name = "joseph";
        Request request = new Request(name, "alabama", "arkansas", 10.00);
        requestList.add(request);
        Collection<Request> requests = requestList.getRequests();
        assertTrue("RequestLIST size", requests.size() == 1);
        assertTrue("joseph is in", requestList.contains(request));
//        assertEquals("There is a request", requestList.hasRequest(request), 1);
//        Request returnedRequest = requestList.getRequest(0);
//        assertEquals(returnedRequest.getRequestID(),request.getRequestID());
    }
}
