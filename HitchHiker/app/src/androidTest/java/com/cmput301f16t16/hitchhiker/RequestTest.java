package com.cmput301f16t16.hitchhiker;


import junit.framework.TestCase;

import org.osmdroid.util.GeoPoint;


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
        Fare fare = new Fare();
        fare.setFare(100.25);
        Request request = new Request(paying_Rider.getUserName(), pickUpA, dropOffB, 5.00, A, B, driver.getUserName());
        assertTrue("Location A is not the start", pickUpA.equals(request.getStartLocation()));
        assertTrue("Location A is not the end", dropOffB.equals(request.getEndLocation()));
        RequestListController requestListController = new RequestListController();
        requestListController.addRequest(request);
        assertEquals("There is a request", 1, requestListController.getRequestLoad(paying_Rider).size());
        requestListController.removeRequest(request.getId());
        assertEquals("There is no request", requestListController.getRequestLoad(paying_Rider).size(), 0);

    }

    public void testSizeRequestList() {
        User rider = new User("HitchHiker4", "Kevin", "Abels", "kabels@ualberta.ca", "2065714788", 1, "");
        User driver = new User("Hitcher1", "Vic", "Lee", "vlee@ualberta.ca", "7803334444", 2, "Civic");
        GeoPoint A = new GeoPoint(12.00, 11.00);
        GeoPoint B = new GeoPoint(132.00, 131.00);
        String pickUpC = "1123 CandyLane Rd Edmonton";
        String dropOffD = "1222 DeerBourne Ln Edmonton";
        Fare fare = new Fare();
        fare.setFare(1.25);
        Request request = new Request(rider.getUserName(), pickUpC, dropOffD, 5.00, A, B, driver.getUserName());
        RequestListController rc = new RequestListController();
        rc.addRequest(request);
        assertEquals("There are no requests in the List", rc.getRequestLoad(rider).size(), 1);
    }

    public void testUserType() {
        User rider = new User("HitchHiker4", "Kevin", "Abels", "kabels@ualberta.ca", "2065714788", 1, "");
        User driver = new User("Hitcher1", "Vic", "Lee", "vlee@ualberta.ca", "7803334444", 2, "Civic");
        rider.setUserType(2);
        driver.setUserType(2);
        assertTrue("The User's type is the same", rider.getUserType().equals(driver.getUserType()));

    }
}

