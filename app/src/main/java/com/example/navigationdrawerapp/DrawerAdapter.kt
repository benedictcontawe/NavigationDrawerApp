package com.example.navigationdrawerapp

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.navigationdrawerapp.databinding.CollapseBinder
import com.example.navigationdrawerapp.databinding.ContentBinder
import com.example.navigationdrawerapp.databinding.HeaderBinder

class DrawerAdapter : RecyclerView.Adapter<BaseViewHolder> {

    companion object {
        private val TAG = DrawerAdapter::class.java.getSimpleName()
        private const val HeaderView = 0
        private const val ContentView = 1
        private const val CollapseView = 3
    }

    private val listener : DrawerListener
    private val list : MutableList<DrawerModel> = mutableListOf<DrawerModel>()

    constructor(listener : DrawerListener) : super() {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent : ViewGroup, viewType : Int) : BaseViewHolder {
        val layoutInflater : LayoutInflater = LayoutInflater.from(parent.getContext())
        return when(viewType) {
            HeaderView -> {
                val binder : HeaderBinder = DataBindingUtil.inflate(layoutInflater, R.layout.cell_header, parent, false)
                HeaderViewHolder(binder, listener)
            }
            ContentView -> {
                val binder : ContentBinder = DataBindingUtil.inflate(layoutInflater, R.layout.cell_content, parent, false)
                ContentViewHolder(binder, listener)
            }
            CollapseView -> {
                val binder : CollapseBinder = DataBindingUtil.inflate(layoutInflater, R.layout.cell_collapse, parent, false)
                CollapseViewHolder(binder, listener)
            }
            else -> {
                val binder : CollapseBinder = DataBindingUtil.inflate(layoutInflater, R.layout.cell_collapse, parent, false)
                CollapseViewHolder(binder, listener)
            }
        }
    }

    override fun onBindViewHolder(holder : BaseViewHolder, position : Int) {
        holder.bindDataToViewHolder(list.get(position), position)
    }

    override fun getItemCount() : Int {
        return list.size
    }

    override fun getItemViewType(position : Int) : Int {
        return if (list[position].isHeader) {
            HeaderView
        } else if (!list[position].isHeader && list[position].isExpand) {
            ContentView
        } else if (!list[position].isHeader && !list[position].isExpand) {
            CollapseView
        } else {
            super.getItemViewType(position)
        }
    }

    public fun setItems(items : List<DrawerModel>) {
        list.clear()
        list.addAll(items)
        notifyDataSetChanged()
    }

    public fun getItem(position : Int) : DrawerModel {
        return list.get(position)
    }

    override fun getItemId(position : Int) : Long {
        return super.getItemId(position)
    }

    public fun setExpand(model : DrawerModel, position : Int) {
        for (index in position ..< getItemCount()) {
            if (getItem(index).isHeader && index != position) {
                break
            } else {
                Log.d(TAG,"setExpand ${getItem(index)}")
                getItem(index).isExpand = true
                notifyItemChanged(index)
            }
        }
        //notifyItemRangeChanged(position, getItemCount())
    }

    public fun setCompress(model : DrawerModel, position : Int) {
        for (index in position ..< getItemCount()) {
            if (getItem(index).isHeader && index != position) {
                break
            } else {
                Log.d(TAG,"setCompress ${getItem(index)}")
                getItem(index).isExpand = false
                notifyItemChanged(index)
            }
        }
        //notifyItemRangeChanged(position, getItemCount())
    }
}