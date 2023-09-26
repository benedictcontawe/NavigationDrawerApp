package com.example.navigationdrawerapp

public interface DrawerListener {

    fun onClick(model : DrawerModel, position : Int)

    fun onExpand(model : DrawerModel, position : Int)

    fun onCompress(model : DrawerModel, position : Int)
}