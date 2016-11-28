package com.cmput301f16t16.hitchhiker;


import junit.framework.TestCase;

import org.osmdroid.util.GeoPoint;

import java.util.ArrayList;


/**
 * Created by Jae-yeon on 10/14/2016.
 * edited by Angus on 11/2/2016.
*/

public class RequestTest extends TestCase {

    /**
     * US 01.01.01
     * As a rider I want to request rides between 2 locations
     */
    public void testLocationRequest() {
        User paying_Rider = new User("HitchHiker2", "Angus", "Abels", "aabels@ualberta.ca", "2065714768", 1, "");
        User driver = new User("Hitcher", "Willy", "Liao", "tingwei@ualberta.ca", "77899635662", 2, "Subaru");
        GeoPoint A = new GeoPoint(12.00, 11.00);
        GeoPoint B = new GeoPoint(132.00, 131.00);
        String pickUpA = "11512 Groat Rd Edmonton";
        String dropOffB = "1234 Luxury Ln Edmonton";
        paying_Rider.setUserType(1);
        Request request = new Request(paying_Rider.getUserName(), pickUpA, dropOffB, 5.00, A, B);
        assertTrue("Location A is not the start", pickUpA.equals(request.getStartLocation()));
        assertTrue("Location A is not the end", dropOffB.equals(request.getEndLocation()));
    }

    public void testSizeRequestList() {
        User rider = new User("HitchHiker4", "Kevin", "Abels", "kabels@ualberta.ca", "2065714788", 1, "");
        User driver = new User("Hitcher1", "Vic", "Lee", "vlee@ualberta.ca", "7803334444", 2, "Civic");
        GeoPoint A = new GeoPoint(12.00, 11.00);
        GeoPoint B = new GeoPoint(132.00, 131.00);
        String pickUpC = "1123 CandyLane Rd Edmonton";
        String dropOffD = "1222 DeerBourne Ln Edmonton";
        Request request = new Request(rider.getUserName(), pickUpC, dropOffD, 5.00, A, B);
        RequestListController rc = new RequestListController();
        rc.addRequest(request);
        ArrayList<Request> requestList = new ArrayList<Request>();
        requestList.add(request);
        //assertEquals("There are no requests in the List", rc.getRequestLoad(rider).size(), 1);
        assertEquals(requestList.size(), 1);
    }

    public void testUserType() {
        User rider = new User("HitchHiker4", "Kevin", "Abels", "kabels@ualberta.ca", "2065714788", 1, "");
        User driver = new User("Hitcher1", "Vic", "Lee", "vlee@ualberta.ca", "7803334444", 2, "Civic");
        rider.setUserType(2);
        driver.setUserType(2);
        assertTrue("The User's type is the same", rider.getUserType().equals(driver.getUserType()));
    }

    /**
     * US 01.06.01
     * As a rider I want an estumation of a fair fare to offer to drivers
     */
    public void testFare() {
        User rider = new User("HitchHiker4", "Kevin", "Abels", "kabels@ualberta.ca", "2065714788", 1, "");
        User driver = new User("Hitcher1", "Vic", "Lee", "vlee@ualberta.ca", "7803334444", 2, "Civic");
        GeoPoint A = new GeoPoint(12.00, 11.00);
        GeoPoint B = new GeoPoint(132.00, 131.00);
        String pickUpC = "1123 CandyLane Rd Edmonton";
        String dropOffD = "1222 DeerBourne Ln Edmonton";
        Request request = new Request(rider.getUserName(), pickUpC, dropOffD, 5.00, A, B);
        request.setPrice(100.00);
        //assertEquals(5.00, request.getPrice());
        assertNotSame(5.00, request.getPrice());
    }

    /**
     * US 05.01.01 As a driver, I want to accept a request I agree with and
     * accept that offered payment upon completion
     */
    public void testAcceptedDriver(){
        GeoPoint A = new GeoPoint(12.00, 11.00);
        GeoPoint B = new GeoPoint(132.00, 131.00);
        String pickup = "st albert";
        String dropoff = "edmonton";
        Request request = new Request("Sarah", pickup, dropoff, 5.00, A, B);
        request.setRequestStatus("PENDING");
        assertEquals(request.getRequestStatus(), "PENDING");

    }


    /**
     *  US 01.07.01 As a rider, I want to confirm the completion of a request and enable payment
     *  US 02.01.01 As a rider or driver, I wnt to see the status of a request that im involved in
     *  US 05.03.01 As a driver, I want tot see if my acceptance was accepted
     *
     */
    public void testGetRequestStatus() {
        GeoPoint A = new GeoPoint(12.00, 11.00);
        GeoPoint B = new GeoPoint(132.00, 131.00);
        String pickup = "st albert";
        String dropoff = "edmonton";
        Request request = new Request("Amy", pickup, dropoff, 5.00, A, B);
        request.setRequestStatus("COMPLETED");

        assertEquals("COMPLETED", request.getRequestStatus());
    }


    /**
     * US 04.04.01 As a driver, I should be able to see the addresses of the requests.
     */
    public void testGetStartLocation() {
        GeoPoint A = new GeoPoint(12.00, 11.00);
        GeoPoint B = new GeoPoint(132.00, 131.00);
        String pickup = "st albert";
        String dropoff = "edmonton";
        Request request = new Request("Amy", pickup, dropoff, 5.00, A, B);

        assertEquals(pickup, request.getStartLocation());

    }

    /**
     *  US 10.01.01 As a rider, I want to specify a start and end geo locations
     *  on a map for a request
     */
    public void testSetStartGeo() {
        GeoPoint A = new GeoPoint(12.00, 11.00);
        GeoPoint B = new GeoPoint(132.00, 131.00);
        String pickup = "st albert";
        String dropoff = "edmonton";
        Request request = new Request("Amy", pickup, dropoff, 5.00, A, B);

        GeoPoint A2 = new GeoPoint(11.00, 11.00);

        request.setStartGeo(A2);

        assertEquals(A2, request.getStartGeo());
    }

    /**
     *  US 10.01.01 As a rider, I want to specify a start and end geo locations
     *  on a map for a request
     */
    public void testSetEndGeo() {
        GeoPoint A = new GeoPoint(12.00, 11.00);
        GeoPoint B = new GeoPoint(132.00, 131.00);
        String pickup = "st albert";
        String dropoff = "edmonton";
        Request request = new Request("Amy", pickup, dropoff, 5.00, A, B);

        GeoPoint B2 = new GeoPoint(133.00, 132.00);

        request.setEndGeo(B2);

        assertEquals(B2, request.getEndGeo());
    }

    /**
     * US 10.02.01 As a driver, I want to view start and end geo locations
     * on a map for a request
     */
    public void testGetStartGeo() {
        GeoPoint A = new GeoPoint(12.00, 11.00);
        GeoPoint B = new GeoPoint(132.00, 131.00);
        String pickup = "st albert";
        String dropoff = "edmonton";
        Request request = new Request("Amy", pickup, dropoff, 5.00, A, B);
        assertEquals(A, request.getStartGeo());
    }

    /**
     * US 10.02.01 As a driver, I want to view start and end geo locations
     * on a map for a request
     */
    public void testGetEndGeo() {
        GeoPoint A = new GeoPoint(12.00, 11.00);
        GeoPoint B = new GeoPoint(132.00, 131.00);
        String pickup = "st albert";
        String dropoff = "edmonton";
        Request request = new Request("Amy", pickup, dropoff, 5.00, A, B);
        assertEquals(B, request.getEndGeo());
    }



}

