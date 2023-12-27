package com.example.navigationdrawerapp

import androidx.compose.runtime.Immutable

@Immutable
public data class DrawerModel (
    val text : String,
    val isHeader : Boolean,
    var isExpand : Boolean,
    val icon : Int,
) {

    companion object {
        private val TAG = DrawerModel::class.java.getSimpleName()
    }

    override fun toString() : String {
        return "$TAG text $text isHeader $isHeader isExpand $isExpand" ?: super.toString()
    }
}