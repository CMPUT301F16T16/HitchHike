package com.cmput301f16t16.hitchhiker;

/**
 * Created by Jae-yeon on 10/24/2016.
 */
public class Fare extends Location{

    protected static double fare;
    protected static double estimate;
    protected Location A;
    protected Location B;

    public Fare(Location A, Location B) {
        this.A = A;
        this.B = B;
        this.fare = getFare();

    }

    public Fare() {
    }


    public double getFare() {

        return this.fare;
    }

    public void setFare(double fare) {

        this.fare = fare;
    }
}
