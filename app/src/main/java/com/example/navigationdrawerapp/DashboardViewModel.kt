package com.example.navigationdrawerapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DashboardViewModel : ViewModel {

    companion object {
        private val TAG = DashboardViewModel::class.java.getSimpleName()
    }

    private val liveList : MutableLiveData<List<DrawerModel>>
    private val list : MutableList<DrawerModel>

    constructor() {
        liveList = MutableLiveData<List<DrawerModel>>(listOf())
        list = mutableListOf<DrawerModel>()
        setList()
    }

    private fun setList() {
        list.add(DrawerModel("A", isHeader = true, isExpand = true, icon = R.drawable.ic_blender))
        list.add(DrawerModel("B", isHeader = false, isExpand = true, icon = R.drawable.ic_umbrella))
        list.add(DrawerModel("C", isHeader = false, isExpand = true, icon = R.drawable.ic_wind_power))
        list.add(DrawerModel("D", isHeader = true, isExpand = true, icon = R.drawable.ic_slideshow))
        list.add(DrawerModel("E", isHeader = false, isExpand = true, icon = R.drawable.ic_gallery))
        list.add(DrawerModel("F", isHeader = false, isExpand = true, icon = R.drawable.ic_camera))
        list.add(DrawerModel("G", isHeader = false, isExpand = true, icon = R.drawable.ic_blender))
        list.add(DrawerModel("H", isHeader = false, isExpand = true, icon = R.drawable.ic_umbrella))
        list.add(DrawerModel("I", isHeader = true, isExpand = true, icon = R.drawable.ic_wind_power))
        list.add(DrawerModel("J", isHeader = false, isExpand = true, icon = R.drawable.ic_umbrella))
        list.add(DrawerModel("K", isHeader = true, isExpand = true, icon = R.drawable.ic_umbrella))
        list.add(DrawerModel("L", isHeader = false, isExpand = true, icon = R.drawable.ic_wind_power))
        list.add(DrawerModel("M", isHeader = false, isExpand = true, icon = R.drawable.ic_umbrella))
        list.add(DrawerModel("N", isHeader = false, isExpand = true, icon = R.drawable.ic_umbrella))
        list.add(DrawerModel("O", isHeader = true, isExpand = true, icon = R.drawable.ic_blender))
        list.add(DrawerModel("P", isHeader = false, isExpand = true, icon = R.drawable.ic_wind_power))
        list.add(DrawerModel("Q", isHeader = false, isExpand = true, icon = R.drawable.ic_umbrella))
        list.add(DrawerModel("R", isHeader = false, isExpand = true, icon = R.drawable.ic_blender))
        list.add(DrawerModel("S", isHeader = false, isExpand = true, icon = R.drawable.ic_umbrella))
        list.add(DrawerModel("T", isHeader = false, isExpand = true, icon = R.drawable.ic_blender))
        list.add(DrawerModel("U", isHeader = true, isExpand = true, icon = R.drawable.ic_wind_power))
        list.add(DrawerModel("V", isHeader = false, isExpand = true, icon = R.drawable.ic_umbrella))
        list.add(DrawerModel("W", isHeader = true, isExpand = true, icon = R.drawable.ic_umbrella))
        list.add(DrawerModel("X", isHeader = false, isExpand = true, icon = R.drawable.ic_blender))
        list.add(DrawerModel("Y", isHeader = true, isExpand = true, icon = R.drawable.ic_umbrella))
        list.add(DrawerModel("Z", isHeader = false, isExpand = true, icon = R.drawable.ic_wind_power))
        liveList.setValue(emptyList<DrawerModel>())
        liveList.setValue(list)
    }

    public fun getLiveList() : LiveData<List<DrawerModel>> {
        return liveList
    }

    private fun getItem(position : Int) : DrawerModel {
        return list.get(position)
    }

    public fun onHeaderCellClick(position : Int, model : DrawerModel) {
        if (model.isHeader && model.isExpand) {
            setCompress(model, position)
        } else if (model.isHeader && model.isExpand.not()) {
            setExpand(model, position)
        }
    }

    private fun setExpand(model : DrawerModel, position : Int) {
        list.set(position, model)
        for (index in position until list.size) {
            if (getItem(index).isHeader && index != position) {
                break
            } else if (!getItem(index).isHeader) {
                getItem(index).isExpand = true
            } else {
                getItem(index).isExpand = true
            }
        }
        liveList.setValue(emptyList<DrawerModel>())
        liveList.setValue(list)
    }

    private fun setCompress(model : DrawerModel, position : Int) {
        list.set(position, model)
        for (index in position until list.size) {
            if (getItem(index).isHeader && index != position) {
                break
            } else if (!getItem(index).isHeader) {
                getItem(index).isExpand = false
            } else {
                getItem(index).isExpand = false
            }
        }
        liveList.setValue(emptyList<DrawerModel>())
        liveList.setValue(list)
    }

    override fun onCleared() {
        super.onCleared()
    }
}