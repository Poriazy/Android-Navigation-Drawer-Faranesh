package com.poriazed.navigationdrawer.custom_nav_drawer.ui_components;

public class NavDrawerWithToggle extends NavDrawerEntry {

    private String title;
    private boolean checked;

    public NavDrawerWithToggle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
