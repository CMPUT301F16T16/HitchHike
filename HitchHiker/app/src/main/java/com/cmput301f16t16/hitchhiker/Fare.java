package com.cmput301f16t16.hitchhiker;

/**
 * Created by Jae-yeon on 10/24/2016.
 */
public class Fare {

    /**
     * The constant fare.
     */
    protected static double fare;
    /**
     * The constant estimate.
     */
    protected static double estimate;
    /**
     * The A.
     */
    protected Location A;
    /**
     * The B.
     */
    protected Location B;

    /**
     * Instantiates a new Fare.
     *
     * @param A the a
     * @param B the b
     */
    public Fare(Location A, Location B) {
        this.A = A;
        this.B = B;
        this.fare = getFare();
    }

    /**
     * Instantiates a new Fare.
     */
    public Fare() {
    }

    /**
     * Gets fare.
     *
     * @return the fare
     */
    public double getFare() {
        return this.fare;
    }

    /**
     * Sets fare.
     *
     * @param fare the fare
     */
    public void setFare(double fare) {
        this.fare = fare;
    }
}
