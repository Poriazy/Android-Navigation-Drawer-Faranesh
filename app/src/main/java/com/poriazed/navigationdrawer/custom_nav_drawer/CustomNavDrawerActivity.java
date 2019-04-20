package com.poriazed.navigationdrawer.custom_nav_drawer;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.poriazed.navigationdrawer.R;
import com.poriazed.navigationdrawer.custom_nav_drawer.fragment.NavigationDrawerFragment;
import com.poriazed.navigationdrawer.custom_nav_drawer.ui_components.NavDrawerDivider;
import com.poriazed.navigationdrawer.custom_nav_drawer.ui_components.NavDrawerEntry;
import com.poriazed.navigationdrawer.custom_nav_drawer.ui_components.NavDrawerWithIcon;
import com.poriazed.navigationdrawer.custom_nav_drawer.ui_components.NavDrawerWithOutIcon;
import com.poriazed.navigationdrawer.custom_nav_drawer.ui_components.NavDrawerWithToggle;

import java.util.ArrayList;
import java.util.List;

public class CustomNavDrawerActivity extends AppCompatActivity {

    private static final String TAG = "CustomNavDrawerActivity";

    //components
    Toolbar toolbar;

    //vars
    private NavigationDrawerFragment navDrawerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customized_nav_drawer);

        //init toolbar
        initToolbarAndNavigation();

        //init navigation fragment
        initNavDrawerFragment();
    }

    private void initToolbarAndNavigation() {
        Log.d(TAG, "initToolbarAndNavigation: called.");
        toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu);
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        toolbar.setTitle(getString(R.string.custom_nav_drawer));
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!navDrawerFragment.isNavDrawerOpened()) {
                    navDrawerFragment.openNavDrawer();
                }
            }
        });
    }


    private void initNavDrawerFragment() {
        Log.d(TAG, "initNavDrawerFragment: called.");

        List<NavDrawerEntry> drawerEntries = new ArrayList<>();
        drawerEntries.add(new NavDrawerWithIcon(getString(R.string.home), R.drawable.ic_home));
        drawerEntries.add(new NavDrawerWithIcon(getString(R.string.fav_product), R.drawable.ic_favorite));
        drawerEntries.add(new NavDrawerWithIcon(getString(R.string.daily_offer), R.drawable.ic_offer));
        drawerEntries.add(new NavDrawerDivider());
        drawerEntries.add(new NavDrawerWithToggle(getString(R.string.dark_mode)));
        drawerEntries.add(new NavDrawerWithOutIcon(getString(R.string.about_us)));
        drawerEntries.add(new NavDrawerWithOutIcon(getString(R.string.exit)));

        //adjust
        navDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        navDrawerFragment.init((DrawerLayout) findViewById(R.id.drawer_layout), drawerEntries);
    }
}
