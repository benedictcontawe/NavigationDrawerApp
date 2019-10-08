package com.example.navigationdrawerapp;

import android.app.Activity;
import android.os.Bundle;
import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class DashboardActivity extends Activity implements View.OnClickListener {

    DrawerLayout drawerLayout;
    ImageView navigationBar,iv_logout;
    LinearLayout ll_First,ll_Second,ll_Third,ll_Fourth,ll_Fifth,ll_Sixth,ll_Seventh;
    NavigationView navigationView;
    TextView tv_logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        onSetNavigationDrawerEvents();
    }

    private void onSetNavigationDrawerEvents() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        navigationView = (NavigationView) findViewById(R.id.navigationView);

        navigationBar = (ImageView) findViewById(R.id.navigationBar);
        ll_First = (LinearLayout) findViewById(R.id.ll_First);
        ll_Second = (LinearLayout) findViewById(R.id.ll_Second);
        ll_Third = (LinearLayout) findViewById(R.id.ll_Third);
        ll_Fourth = (LinearLayout) findViewById(R.id.ll_Fourth);
        ll_Fifth = (LinearLayout) findViewById(R.id.ll_Fifth);
        ll_Sixth = (LinearLayout) findViewById(R.id.ll_Sixth);
        ll_Seventh = (LinearLayout) findViewById(R.id.ll_Seventh);

        iv_logout = (ImageView) findViewById(R.id.iv_logout);
        tv_logout = (TextView) findViewById(R.id.tv_logout);

        navigationBar.setOnClickListener(this);
        ll_First.setOnClickListener(this);
        ll_Second.setOnClickListener(this);
        ll_Third.setOnClickListener(this);
        ll_Fourth.setOnClickListener(this);
        ll_Fifth.setOnClickListener(this);
        ll_Sixth.setOnClickListener(this);
        ll_Seventh.setOnClickListener(this);
        iv_logout.setOnClickListener(this);
        tv_logout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.navigationBar:
                drawerLayout.openDrawer(navigationView, true);
                break;
            case R.id.ll_First:
                showToast("ll_First");
                drawerLayout.closeDrawer(navigationView, true);
                break;
            case R.id.ll_Second:
                showToast("ll_Second");
                drawerLayout.closeDrawer(navigationView, true);
                break;
            case R.id.ll_Third:
                showToast("ll_Third");
                drawerLayout.closeDrawer(navigationView, true);
                break;
            case R.id.ll_Fourth:
                showToast("ll_Fourth");
                drawerLayout.closeDrawer(navigationView, true);
                break;
            case R.id.ll_Fifth:
                showToast("ll_Fifth");
                drawerLayout.closeDrawer(navigationView, true);
                break;
            case R.id.ll_Sixth:
                showToast("ll_Sixth");
                drawerLayout.closeDrawer(navigationView, true);
                break;
            case R.id.ll_Seventh:
                showToast("ll_Seventh");
                drawerLayout.closeDrawer(navigationView, true);
                break;
            case R.id.iv_logout:
                showToast("iv_logout");
                drawerLayout.closeDrawer(navigationView, true);
                break;
            case R.id.tv_logout:
                showToast("tv_logout");
                drawerLayout.closeDrawer(navigationView, true);
                break;
            default:
                showToast("Default");
                drawerLayout.closeDrawer(navigationView, true);
                break;

        }
    }

    private void showToast(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(navigationView)) {
            drawerLayout.closeDrawer(navigationView, true);
        }
        else{
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}