package com.example.navigationdrawerapp

import android.view.View
import com.example.navigationdrawerapp.databinding.CollapseBinder

class CollapseViewHolder : BaseViewHolder {

    companion object {
        private val TAG = CollapseViewHolder::class.java.getSimpleName()
    }

    private val binder : CollapseBinder

    constructor(binder : CollapseBinder, listeners : DrawerListener) : super(binder.getRoot(), listeners) {
        this.binder = binder
        binder.getRoot().setId(-1)
        binder.getRoot().setVisibility(View.GONE)
    }

    public override fun bindDataToViewHolder(model : DrawerModel, position : Int) {
        //region Input Data
        binder.setHolder(model)
        binder.setPosition(position)
        binder.executePendingBindings()
        binder.textView.setText(model.text)
        //endregion
    }
}