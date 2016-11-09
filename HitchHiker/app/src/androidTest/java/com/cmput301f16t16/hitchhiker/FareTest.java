package com.cmput301f16t16.hitchhiker;

import junit.framework.TestCase;

import static java.lang.Boolean.TRUE;

/**
 * Created by Jae-yeon on 10/24/2016.
 */

public class FareTest extends TestCase{
    public void testGetFare() {
        Fare fare = new Fare();
        fare.setFare(0);
        double testFare = 0;
        assertEquals(testFare, fare.getFare());

    }
    public void testSetFare() {
        Fare fare = new Fare();
        fare.setFare(10.00);
        double newFare = 10.00;
        fare.setFare(newFare);
        assertEquals(newFare,fare.getFare());
    }
}
