package com.example.navigationdrawerapp;

public interface DrawerListener {
    public void onClick(DrawerModel model, int position);

    public void onExpand(DrawerModel model, int position);

    public void onCompress(DrawerModel model, int position);
}