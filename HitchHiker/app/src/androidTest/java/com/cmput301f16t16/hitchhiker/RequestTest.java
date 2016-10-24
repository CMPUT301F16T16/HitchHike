package com.cmput301f16t16.hitchhiker;

import junit.framework.TestCase;

/**
 * Created by Jae-yeon on 10/14/2016.
 */

public class RequestTest extends TestCase {
    public void testGetRequestID() {
        Integer RID = 0;
        Request request = new Request(RID);
        assertEquals(0,request.getRequestID());
    }

    public void testSetRequestID() {
        Integer RID = 0;
        Request request = new Request(RID);
        Integer RID2 = 1;
        request.setRequestID(RID2);
        assertEquals(1,request.getRequestID());
    }
    public void testSetRequestStatus() {
        Request request = new Request(0);
        String status = "TestStatus";
        request.setRequestStatus(status);
        assertEquals("TestStatus",request.getRequestStatus());
    }
    public void testGetRequestStatus() {
        Request request = new Request(0);
        String status = "TestStatus";
        request.setRequestStatus(status);
        assertEquals("TestStatus",request.getRequestStatus());
    }
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
