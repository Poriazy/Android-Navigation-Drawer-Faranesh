package com.poriazed.navigationdrawer.custom_nav_drawer.ui_components;

public class NavDrawerWithIcon extends NavDrawerEntry{

    private String title;
    private int icon;

    public NavDrawerWithIcon(String title, int icon) {
        this.title = title;
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
