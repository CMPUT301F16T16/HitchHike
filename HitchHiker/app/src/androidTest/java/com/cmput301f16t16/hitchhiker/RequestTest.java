package com.cmput301f16t16.hitchhiker;


import junit.framework.TestCase;


/**
 * Created by Jae-yeon on 10/14/2016.
 * edited by Angus on 11/2/2016.
*/

public class RequestTest extends TestCase {

    public void testRequest() {
        Location pickUp = new Location();
        Fare fare = new Fare();
        fare.setFare(10.25);
        Location dropOff = new Location();
        User requestCreator = new User("HitchKiker1", "", "", "", 0,0 );
        String name = requestCreator.getUserName();
        Request request = new Request(name, "alabama", "arkansas", 10.00);
        assertTrue("Request Creator is not equal", requestCreator.equals(request.getRiderName()));
    }

    /**
     * US 01.01.01
     * As a rider I want to request rides between 2 locations
     */
    public void testLocationRequest() {
        User paying_Rider = new User("HitchHiker2", "", "", "", 6,5);
        Location pickUp = new Location();
        Location dropOff = new Location();
        paying_Rider.setUserType(1);
        Fare fare = new Fare();
        fare.setFare(100.25);
        Request request = new Request(paying_Rider.getUserName(), "alabama", "arkansas", 10.00);
        assertTrue("Location A is not the start", pickUp.equals(request.getStartLocation()));
        assertTrue("Location A is not the end", dropOff.equals(request.getEndLocation()));
        RequestListController requestListController = new RequestListController();
        requestListController.addRequest(request);
        assertEquals("There is a request", 1, requestListController.getRequestLoad(paying_Rider).size());
        requestListController.removeRequest(request);
        assertEquals("There is no request", requestListController.getRequestLoad(paying_Rider).size(),0);

    }

    public void testSizeRequestList() {
        User rider = new User("BOB", "", "", "", 3, 4);
        Location pickUp = new Location();
        Location dropOff = new Location();
        Fare fare = new Fare();
        fare.setFare(1.25);
        Request request = new Request(rider.getUserName(), "pickUp", "dropOff", 10.00);
        RequestListController rc = new RequestListController();
        assertEquals("There are no requests in the List", rc.getRequestLoad(rider).size(), 0);
//        rc.addRequest(request);
//        assertEquals("There is a request!",rc.getRequestLoad(rider).size(), 1);
//        rc.removeRequest(request);
//        assertEquals("There are no requests in the List", rc.getRequestLoad(rider).size(), 0);
    }

    public void testUserType () {
        User rider = new User("Billy", "", "", "", 4,5);
        rider.setUserType(2);
        User driver = new User("Dora", "", "", "", 5, 3);
        driver.setUserType(2);
        assertTrue("The User's type is the same", rider.getUserType().equals(driver.getUserType()));
//        assertTrue("The User's type is the same", driver.getUserType().equals("Driver"));
//        assertNotSame(rider, driver);




    }


}
//<<<<<<< HEAD
//    public void testAcceptDriver() {
//        Request request = new Request(0);
//        request.acceptDriver();
//        assertEquals("Accepted",request.getRequestStatus());
//    }
//    public void testCompleteRequest() {
//        Request request = new Request(0);
//        request.completeRequest();
//        assertEquals("Completed",request.getRequestStatus());
//    }
//    public void testDriverAcceptsRequest(){
//        Request request = new Request(0);
//        request.driverAcceptsRequest();
//        assertEquals("Driver Accepted", request.getRequestStatus());
//        }
//    }
//=======
//}
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
//>>>>>>> request_testing
//    public void testCreateRequest(){
//        String pickUp = "Earth";
//        String dropOff = "Moon";
//        Request newRequest = new Request(pickUp, dropOff);


//        Rider newRider = new Rider(username, firstName, lastName, phoneNumber);
//
//        //Test to see if values passed are created and stored. It tests for equality.
//        assertEquals("Username is not the same", username, newRider.getUsername());
//        assertEquals("First name is not the same", firstName, newRider.getFirstName());
//        assertEquals("Last name is not the same", lastName, newRider.getLastName());
//        assertEquals("Phone number is not the same", phoneNumber, newRider.getPhoneNumber());
//    }
//}

