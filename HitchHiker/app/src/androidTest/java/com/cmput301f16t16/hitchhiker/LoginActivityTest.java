//package com.cmput301f16t16.hitchhiker;
//
//import android.app.Activity;
//import android.test.ActivityInstrumentationTestCase2;
//import android.util.Log;
//import android.widget.EditText;
//
//import com.robotium.solo.Solo;
//
///**
// * Created by Leo Yoon on 07/11/2016.
// */
//
//public class LoginActivityTest extends ActivityInstrumentationTestCase2<LoginActivity> {
//    private Solo solo;
//
//    public LoginActivityTest() {
//        super(com.cmput301f16t16.hitchhiker.LoginActivity.class);
//    }
//
//    public void testStart() throws Exception {
//        Activity activity = getActivity();
//    }
//
//    public void setUp() throws Exception {
//        Log.d("TAG1", "setUp()");
//        solo = new Solo(getInstrumentation(),getActivity());
//    }
//
//    public void testLogin() {
//        solo.assertCurrentActivity("Wrong Activity", LoginActivity.class);
//
//        solo.enterText((EditText) solo.getView(R.id.userNameText), "test@email.com");
//    }
//}
