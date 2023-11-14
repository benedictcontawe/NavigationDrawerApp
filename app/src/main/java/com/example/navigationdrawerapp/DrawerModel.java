package com.example.navigationdrawerapp;

import androidx.annotation.NonNull;

public class DrawerModel {
    private final String text;
    private final Boolean isHeader;
    private Boolean isExpand;
    public DrawerModel(String text, Boolean isHeader, Boolean isExpand) {
        this.text = text;
        this.isHeader = isHeader;
        this.isExpand = isExpand;
    }

    public String getText() {
        return text;
    }

    public Boolean getHeader() {
        return isHeader;
    }

    public Boolean getExpand() {
        return isExpand;
    }

    public void setExpand(Boolean expand) {
        isExpand = expand;
    }

    @NonNull
    @Override
    public String toString() {
        return "DrawerModel text " + text + " isHeader " + isHeader + " isExpand " + isExpand;
    }
}