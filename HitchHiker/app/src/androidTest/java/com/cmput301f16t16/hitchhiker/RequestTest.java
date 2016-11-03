package com.cmput301f16t16.hitchhiker;


import junit.framework.TestCase;


/**
 * Created by Jae-yeon on 10/14/2016.
 * edited by Angus on 11/2/2016.
 */

public class RequestTest extends TestCase {


    public void testRequest () {
        Location pickUp = new Location();
        Location dropOff = new Location();
        User requestCreator = new User("HitchKiker1");
        Request request = new Request (requestCreator, pickUp, dropOff);
        assertTrue("Request Creator is not equal", requestCreator.equals(request.getRider()));
    }

    /**
     * US 01.01.01
     * As a rider I want to request rides between 2 locations
    */
    public void testLocationRequest() {
        User paying_Rider = new User("HitchHiker2");
        Location pickUp = new Location();
        Location dropOff = new Location();
        Request request = new Request(paying_Rider, pickUp, dropOff);
        assertTrue("Location A is not the start", pickUp.equals(request.getStartLocation()));
        assertTrue("Location A is not the end", dropOff.equals(request.getEndLocation()));
        RequestListController requestListController= new RequestListController();
        requestListController.addRequest(request);
        requestListController.removeRequest(request);





    }
<<<<<<< HEAD
    public void testAcceptDriver() {
        Request request = new Request(0);
        request.acceptDriver();
        assertEquals("Accepted",request.getRequestStatus());
    }
    public void testCompleteRequest() {
        Request request = new Request(0);
        request.completeRequest();
        assertEquals("Completed",request.getRequestStatus());
    }
    public void testDriverAcceptsRequest(){
        Request request = new Request(0);
        request.driverAcceptsRequest();
        assertEquals("Driver Accepted", request.getRequestStatus());
        }
    }
=======
}
//DONT NEED TO TEST GETTERS AND SETTERS
//
//    public void testGetRequestID() {
//        Integer RID = 0;
//        Request request = new Request(RID);
//        assertEquals(0,request.getRequestID());
//    }

//    public void testSetRequestID() {
//        Integer RID = 0;
//        Request request = new Request(RID);
//        Integer RID2 = 1;
//        request.setRequestID(RID2);
//        assertEquals(1,request.getRequestID());
//    }
//    public void testSetRequestStatus() {
//        Request request = new Request(0);
//        String status = "TestStatus";
//        request.setRequestStatus(status);
//        assertEquals("TestStatus",request.getRequestStatus());
//    }
//    public void testGetRequestStatus() {
//        Request request = new Request(0);
//        String status = "TestStatus";
//        request.setRequestStatus(status);
//        assertEquals("TestStatus",request.getRequestStatus());
//    }
//}
>>>>>>> request_testing
