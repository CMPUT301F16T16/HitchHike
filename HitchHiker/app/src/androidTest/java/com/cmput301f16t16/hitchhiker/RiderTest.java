package com.cmput301f16t16.hitchhiker;

import junit.framework.Test;
import junit.framework.TestCase;

/**
 * Created by Jae-yeon on 10/14/2016.
 */

public class RiderTest extends TestCase {

    public void testCreateRider(){
        String username = "Stitch";
        String firstName = "Willy";
        String lastName = "Liao";
        String phoneNumber = "1234567890";

        Rider newRider = new Rider(userName, userFirstName, userLastName, userPhoneNumber);

        //Test to see if values passed are creqted and stored. It tests for equality.
        assertEquals("Username is not the same", username, newRider.getUserName());
        assertEquals("First name is not the same", firstName, newRider.getUserFirstName());
        assertEquals("Last name is not the same", lastName, newRider.getUserLastName());
        assertEquals("Phone number is not the same", phoneNumber, newRider.getUserPhoneNumber());
    }


}
