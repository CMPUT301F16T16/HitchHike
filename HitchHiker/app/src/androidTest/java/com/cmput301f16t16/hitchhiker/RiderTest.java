package com.cmput301f16t16.hitchhiker;

import junit.framework.Test;
import junit.framework.TestCase;

/**
 * Created by Jae-yeon on 10/14/2016.
 */

public class RiderTest extends TestCase {
    public void testGetRiderID() {
        Rider rider = new Rider(0);
        Integer riderID = 0;
        rider.setRiderID(riderID);
        assertEquals(0, rider.getRiderID());
    }
    public void testSetRiderID() {
        Rider rider = new Rider(0);
        Integer riderID = 1;
        rider.setRiderID(riderID);
        assertEquals(1,rider.getRiderId());

    }
}
