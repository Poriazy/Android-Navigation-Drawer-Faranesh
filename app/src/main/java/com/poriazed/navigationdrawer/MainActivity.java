package com.poriazed.navigationdrawer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.poriazed.navigationdrawer.custom_nav_drawer.CustomNavDrawerActivity;
import com.poriazed.navigationdrawer.google_nav_drawer.GoogleNavDrawerActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // onClick methods was set to the buttons in xml file
    }

    /**
     * open google navigation drawer
     * @param view  button
     */
    public void openGoogleNavAct(View view) {
        Log.d(TAG, "openGoogleNavAct: clicked!");
        startActivity(new Intent(MainActivity.this, GoogleNavDrawerActivity.class));
    }


    /**
     * open custom navigation drawer
     * @param view  button
     */
    public void openCustomNavAct(View view) {
        Log.d(TAG, "openCustomNavAct: clicked!");
        startActivity(new Intent(MainActivity.this, CustomNavDrawerActivity.class));
    }
}
