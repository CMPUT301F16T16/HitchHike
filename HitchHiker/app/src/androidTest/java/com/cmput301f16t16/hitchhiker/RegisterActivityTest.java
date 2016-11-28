package com.cmput301f16t16.hitchhiker;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.widget.EditText;

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

    /**
     * US 03.01.01 As a user, I want a profile with a unique username and my contact information.
     */
    public void testRegister(){
        solo.assertCurrentActivity("Wrong Activity", RequestInfoActivity.class);
        User user = new User("","","","","",1,"");
        solo.enterText((EditText) solo.getView(R.id.firstNameEditText), "testFN");
        solo.enterText((EditText) solo.getView(R.id.lastNameEditText), "testLN");
        solo.enterText((EditText) solo.getView(R.id.emailAddressEditText), "test@test.com");
        solo.enterText((EditText) solo.getView(R.id.userNameEditText), "testUser");
        solo.enterText((EditText) solo.getView(R.id.phoneNumberEditText), "29837492");

        solo.clickOnView(solo.getView(R.id.riderCheckBox));

        solo.clickOnView(solo.getView(R.id.registerButton));

    }



}
