package com.cmput301f16t16.hitchhiker;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.robotium.solo.Solo;

import static com.cmput301f16t16.hitchhiker.R.id.saveProfile;

/**
 * Created by Leo Yoon on 27/11/2016.
 */

public class UserProfileEditActivityTest extends ActivityInstrumentationTestCase2<UserProfileEditActivity> {
    private Solo solo;

    public UserProfileEditActivityTest() { super(com.cmput301f16t16.hitchhiker.UserProfileEditActivity.class); }

    public void testStart() throws Exception {
        Activity activity = getActivity();
    }

    public void setUp() throws Exception {
        Log.d("TAG1", "setUp()");
        solo = new Solo(getInstrumentation(), getActivity());
    }

    /**
     * US 03.02.01 As a user, I want to edit the contact information in my profile
     */
    public void testEditUserContactInformation() {
        solo.assertCurrentActivity("Wrong Activity", UserProfileEditActivity.class);
        User user = new User("","","","","",65,"");
        solo.enterText((EditText) solo.getView(R.id.email_edittext), "test@email.com");
        solo.enterText((EditText) solo.getView(R.id.phoneNumber_edittext), "7082871737");

        solo.clickOnView(solo.getView(R.id.saveProfile));


    }

}
