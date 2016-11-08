package com.cmput301f16t16.hitchhiker;

/**
 * Created by V1CTORIA2LEE on 2016-11-07.
 */

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import junit.framework.TestCase;


import android.util.Log;

import com.robotium.solo.Solo;

public class riderActivityTest extends ActivityInstrumentationTestCase2<Rider> {

    private Solo solo;

    public riderActivityTest() {
        super(com.cmput301f16t16.hitchhiker.Rider.class);
    }

    public void testStart() throws Exception {
        Activity activity = getActivity();
    }

    public void setUp() throws Exception{
        Log.d("TAG1", "setUp()");
        solo = new Solo(getInstrumentation(),getActivity());
    }

    public void testIntents(){
        solo.assertCurrentActivity("Wrong Activity", Rider.class);
        solo.clickOnButton("Home");

        solo.goBack();

        solo.assertCurrentActivity("Wrong Activity", Rider.class);
        solo.clickOnButton("New Request");

        solo.goBack();

        solo.assertCurrentActivity("Wrong Activity", Rider.class);
        solo.clickOnButton("Profile");



    }

}
