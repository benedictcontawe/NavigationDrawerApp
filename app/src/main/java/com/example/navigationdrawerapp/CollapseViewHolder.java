package com.example.navigationdrawerapp;

import android.view.View;

import com.example.navigationdrawerapp.databinding.CollapseBinder;

public class CollapseViewHolder extends BaseViewHolder {

    private final String TAG = CollapseViewHolder.class.getSimpleName();

    private final CollapseBinder binder;

    public CollapseViewHolder(CollapseBinder binder, DrawerListener listener) {
        super(binder.getRoot(), listener);
        this.binder = binder;
        binder.getRoot().setId(-1);
        binder.getRoot().setVisibility(View.GONE);
    }

    @Override
    void bindDataToViewHolder(DrawerModel model, int position) {
        //region Input Data
        binder.setHolder(model);
        binder.setPosition(position);
        binder.executePendingBindings();
        //binder.textView.setText(model.getText());
        //endregion
    }
}
