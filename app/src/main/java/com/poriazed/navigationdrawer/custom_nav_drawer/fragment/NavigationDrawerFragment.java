package com.poriazed.navigationdrawer.custom_nav_drawer.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.poriazed.navigationdrawer.R;
import com.poriazed.navigationdrawer.custom_nav_drawer.NavContoller;
import com.poriazed.navigationdrawer.custom_nav_drawer.OnNavViewClickListener;
import com.poriazed.navigationdrawer.custom_nav_drawer.adapters.NavDrawerRecyclerAdapter;
import com.poriazed.navigationdrawer.custom_nav_drawer.ui_components.NavDrawerEntry;

import java.util.List;

public class NavigationDrawerFragment extends Fragment implements OnNavViewClickListener, NavContoller {

    //component
    private View root;
    private DrawerLayout drawerLayout;
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_nav_drawer, container, false);
        return root;
    }


    public void init(DrawerLayout drawerLayout, List<NavDrawerEntry> drawerEntries) {
        this.drawerLayout = drawerLayout;

        //init recycler
        recyclerView = root.findViewById(R.id.rv_nav_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);

        // add recycler view adapter
        NavDrawerRecyclerAdapter adapter = new NavDrawerRecyclerAdapter(drawerEntries, getActivity());
        recyclerView.setAdapter(adapter);
        adapter.setClickListener(this);
    }

    @Override
    public void onItemClick(String title) {
        Toast.makeText(getActivity(), title, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSwitchStateChange(boolean status) {
        Toast.makeText(getActivity(), status + "", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void openNavDrawer() {
        drawerLayout.openDrawer(Gravity.START);
    }

    @Override
    public void closeNavDrawer() {
        drawerLayout.closeDrawer(Gravity.START);
    }

    @Override
    public boolean isNavDrawerOpened() {
        if (drawerLayout.isDrawerOpen(Gravity.START))
            return true;
        else if (!drawerLayout.isDrawerOpen(Gravity.START))
            return false;

        return false;
    }
}
