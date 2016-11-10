package com.cmput301f16t16.hitchhiker;

import junit.framework.TestCase;

/**
 * Created by willyliao on 2016-11-09.
 */

public class RequestTest extends TestCase {

    public void testCreateRequest(){
        String pickUp = "Earth";
        String dropOff = "Moon";
        Request newRequest = new Request(pickUp, dropOff);


//        Rider newRider = new Rider(username, firstName, lastName, phoneNumber);
//
//        //Test to see if values passed are created and stored. It tests for equality.
//        assertEquals("Username is not the same", username, newRider.getUsername());
//        assertEquals("First name is not the same", firstName, newRider.getFirstName());
//        assertEquals("Last name is not the same", lastName, newRider.getLastName());
//        assertEquals("Phone number is not the same", phoneNumber, newRider.getPhoneNumber());
    }
}
