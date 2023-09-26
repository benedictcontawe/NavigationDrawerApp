package com.example.navigationdrawerapp

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder : RecyclerView.ViewHolder {

    companion object {
        private val TAG = BaseViewHolder::class.java.getSimpleName()
    }

    private val listener : DrawerListener

    constructor(itemView : View, listener : DrawerListener) : super(itemView) {
        this.listener = listener
    }

    protected fun getListener() : DrawerListener {
        return listener
    }

    abstract fun bindDataToViewHolder(model : DrawerModel, position : Int)
}