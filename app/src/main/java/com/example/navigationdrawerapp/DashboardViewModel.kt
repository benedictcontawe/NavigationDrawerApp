package com.example.navigationdrawerapp

import androidx.lifecycle.ViewModel

class DashboardViewModel : ViewModel {

    companion object {
        private val TAG = DashboardViewModel::class.java.getSimpleName()
    }

    private val list : MutableList<DrawerModel>

    constructor() {
        list = mutableListOf<DrawerModel>()
        setList()
    }
    /*
    constructor(application : Application) : super(application) {

    }
    */
    private fun setList() {
        list.add(DrawerModel("A", true, true))
        list.add(DrawerModel("B", false, true))
        list.add(DrawerModel("C", false, true))
        list.add(DrawerModel("D", true, true))
        list.add(DrawerModel("E", false, true))
        list.add(DrawerModel("F", false, true))
        list.add(DrawerModel("G", false, true))
        list.add(DrawerModel("H", false, true))
        list.add(DrawerModel("I", true, true))
        list.add(DrawerModel("J", false, true))
        list.add(DrawerModel("K", true, true))
        list.add(DrawerModel("L", false, true))
        list.add(DrawerModel("M", false, true))
        list.add(DrawerModel("N", false, true))
        list.add(DrawerModel("O", true, true))
        list.add(DrawerModel("P", false, true))
        list.add(DrawerModel("Q", false, true))
        list.add(DrawerModel("R", false, true))
        list.add(DrawerModel("S", false, true))
        list.add(DrawerModel("T", false, true))
        list.add(DrawerModel("U", true, true))
        list.add(DrawerModel("V", false, true))
        list.add(DrawerModel("W", true, true))
        list.add(DrawerModel("X", false, true))
        list.add(DrawerModel("Y", true, true))
        list.add(DrawerModel("Z", false, true))
    }

    public fun getList() : List<DrawerModel> {
        return list
    }

    override fun onCleared() {
        super.onCleared()
    }
}