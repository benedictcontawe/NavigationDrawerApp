package com.example.navigationdrawerapp;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class DashboardViewModel extends ViewModel {

    private static final String TAG = DashboardViewModel.class.getSimpleName();

    private final List<DrawerModel> list;

    public DashboardViewModel() {
        this.list = new ArrayList<DrawerModel>();
        setList();
    }

    private void setList() {
        list.add(new DrawerModel("A", true, true));
        list.add(new DrawerModel("B", false, true));
        list.add(new DrawerModel("C", false, true));
        list.add(new DrawerModel("D", true, true));
        list.add(new DrawerModel("E", false, true));
        list.add(new DrawerModel("F", false, true));
        list.add(new DrawerModel("G", false, true));
        list.add(new DrawerModel("H", false, true));
        list.add(new DrawerModel("I", true, true));
        list.add(new DrawerModel("J", false, true));
        list.add(new DrawerModel("K", true, true));
        list.add(new DrawerModel("L", false, true));
        list.add(new DrawerModel("M", false, true));
        list.add(new DrawerModel("N", false, true));
        list.add(new DrawerModel("O", true, true));
        list.add(new DrawerModel("P", false, true));
        list.add(new DrawerModel("Q", false, true));
        list.add(new DrawerModel("R", false, true));
        list.add(new DrawerModel("S", false, true));
        list.add(new DrawerModel("T", false, true));
        list.add(new DrawerModel("U", true, true));
        list.add(new DrawerModel("V", false, true));
        list.add(new DrawerModel("W", true, true));
        list.add(new DrawerModel("X", false, true));
        list.add(new DrawerModel("Y", true, true));
        list.add(new DrawerModel("Z", false, true));
    }

    public List<DrawerModel> getList() {
        return list;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}