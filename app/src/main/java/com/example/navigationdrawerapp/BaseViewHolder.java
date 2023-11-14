package com.example.navigationdrawerapp;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public abstract class BaseViewHolder extends RecyclerView.ViewHolder {

    private static String TAG = BaseViewHolder.class.getSimpleName();
    private final DrawerListener listener;

    public BaseViewHolder (View itemView, DrawerListener listener) {
        super(itemView);
        this.listener = listener;
    }

    protected DrawerListener getListener() {
        return listener;
    }

    abstract void bindDataToViewHolder(DrawerModel model, int position);
}