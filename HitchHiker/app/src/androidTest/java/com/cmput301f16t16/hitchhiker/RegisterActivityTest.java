package com.cmput301f16t16.hitchhiker;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;

import com.robotium.solo.Solo;

/**
 * Created by V1CTORIA2LEE on 2016-11-27.
 */
public class RegisterActivityTest extends ActivityInstrumentationTestCase2<RegisterActivity> {
    private Solo solo;

    public RegisterActivityTest() {
        super(com.cmput301f16t16.hitchhiker.RegisterActivity.class);
    }

    public void testStart() throws Exception {
        Activity activity = getActivity();
    }

    public void setUp() throws Exception{
        Log.d("TAG1", "setUp()");
        solo = new Solo(getInstrumentation(),getActivity());
    }

    public void testRegister(){
        solo.assertCurrentActivity("Wrong Activity", RequestInfoActivity.class);
        //solo.clickOnButton("Clear");

//        solo.enterText((EditText) solo.getView(R.id.body), "Test Tweet!");
//        solo.clickOnButton("Save");
    }



}
