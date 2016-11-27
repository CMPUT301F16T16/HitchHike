package com.cmput301f16t16.hitchhiker;

import junit.framework.TestCase;

import org.osmdroid.util.GeoPoint;

import java.util.Collection;

/**
 * Created by Jae-yeon on 10/14/2016.
 */

public class RequestListTest extends TestCase{
    /**
     *    US 01.02.01 - As a rider, I want to see current requests I have open.
     */
    public void testAdd(){
        RequestList requestList = new RequestList();
        User paying_Rider = new User("HitchHiker2", "Angus", "Abels", "aabels@ualberta.ca", "2065714768", 1, "");
        User driver = new User("Hitcher", "Willy", "Liao", "tingwei@ualberta.ca", "77899635662", 2, "Subaru");
        GeoPoint A = new GeoPoint(12.00, 11.00);
        GeoPoint B = new GeoPoint(132.00, 131.00);
        String pickUpA = "11512 Groat Rd Edmonton";
        String dropOffB = "1234 Luxury Ln Edmonton";
        Request request = new Request(paying_Rider.getUserName(), pickUpA, dropOffB, 5.00, A, B);
        requestList.add(request);
        assertTrue(requestList.hasRequest(request));
    }
    /**
     * US 01.04.01
     As a rider, I want to cancel requests.
     */
    public void testDelete() {
        RequestList requestList = new RequestList();
        User paying_Rider = new User("HitchHiker2", "Angus", "Abels", "aabels@ualberta.ca", "2065714768", 1, "");
        User driver = new User("Hitcher", "Willy", "Liao", "tingwei@ualberta.ca", "77899635662", 2, "Subaru");
        GeoPoint A = new GeoPoint(12.00, 11.00);
        GeoPoint B = new GeoPoint(132.00, 131.00);
        String pickUpA = "11512 Groat Rd Edmonton";
        String dropOffB = "1234 Luxury Ln Edmonton";
        Request request = new Request(paying_Rider.getUserName(), pickUpA, dropOffB, 5.00, A, B);
        requestList.add(request);
        requestList.delete(request);
        assertFalse(requestList.hasRequest(request));
    }
    /**
     *    US 01.02.01 - As a rider, I want to see current requests I have open.
     */
    public void testHasRequest() {
        RequestList requestList = new RequestList();
        User rider = new User("HitchHiker4", "Kevin", "Abels", "kabels@ualberta.ca", "2065714788", 1, "");
        User driver = new User("Hitcher1", "Vic", "Lee", "vlee@ualberta.ca", "7803334444", 2, "Civic");
        GeoPoint A = new GeoPoint(12.00, 11.00);
        GeoPoint B = new GeoPoint(132.00, 131.00);
        String pickUp = "1123 CandyLane Rd Edmonton";
        String dropOff = "1222 DeerBourne Ln Edmonton";
        Request request = new Request(rider.getUserName(), pickUp, dropOff, 5.00, A, B);
        requestList.add(request);
        assertTrue(requestList.hasRequest(request));
    }

    /**
     * US 02.01.01
     As a rider or driver, I want to see the status of a request that I am involved in
     */

    public void testGetRequest() {
        RequestList requestList = new RequestList();
        User rider = new User("HitchHiker6", "Wilson", "Sponarski", "wsponarski@ualberta.ca", "20657342788", 1, "");
        User driver = new User("Hitcher3", "Leo", "Yoon", "jyoon@ualberta.ca", "78031347744", 2, "Escalade");
        GeoPoint A = new GeoPoint(12.00, 11.00);
        GeoPoint B = new GeoPoint(132.00, 131.00);
        String pickUp = "1123 SpruceGrove Rd Edmonton";
        String dropOff = "1222 DeerRed Ln Edmonton";
        Request request = new Request(rider.getUserName(), pickUp, dropOff, 5.00, A, B);
        requestList.add(request);
        Collection<Request> requests = requestList.getRequests();
        assertTrue("RequestLIST size", requests.size() == 1);
        assertTrue("joseph is in", requestList.contains(request));
        assertEquals("There is a request", requestList.hasRequest(request), 1);
        Request returnedRequest = requestList.getRequest(0);
        assertEquals(returnedRequest.getRequestID(),request.getRequestID());
    }
}
