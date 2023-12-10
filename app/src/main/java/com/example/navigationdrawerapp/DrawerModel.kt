package com.example.navigationdrawerapp

public data class DrawerModel (
    val text : String,
    val isHeader : Boolean,
    var isExpand : Boolean,
    ) {

    override fun toString() : String {
        return "DrawerModel text $text isHeader $isHeader isExpand $isExpand" ?: super.toString()
    }
}