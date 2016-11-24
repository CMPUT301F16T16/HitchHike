package com.cmput301f16t16.hitchhiker;

import android.location.Address;
import android.location.Geocoder;

import org.osmdroid.util.GeoPoint;

/**
 * Created by Angus on 11/3/2016.
 */
public class Location {

    /**
     * The Drop off.
     */
    protected String addressName;
    protected double latitude;
    protected double longitude;

    public Location(String addressName, double latitude, double longitude) {
        this.addressName = addressName;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    /**
     * The Pick up lat.
     */







//    public Address getSourceAddress() {
//        return sourceAddress;
//    }
//
//    /**
//     * Sets source address.
//     *
//     * @param sourceAddress the source address
//     */
//    public void setSourceAddress(Address sourceAddress) {
//        this.sourceAddress = sourceAddress;

}
