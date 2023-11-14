package com.example.navigationdrawerapp;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import com.example.navigationdrawerapp.databinding.DashboardBinder;

public class DashboardActivity extends AppCompatActivity implements View.OnClickListener, DrawerListener {

    private final String TAG = DashboardActivity.class.getSimpleName();

    private DashboardBinder binder;
    private DashboardViewModel viewModel;
    private DrawerAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        binder = DataBindingUtil.setContentView(this, R.layout.activity_dashboard);
        binder.setViewModel(viewModel);
        binder.setLifecycleOwner(this);
        super.onCreate(savedInstanceState);
        onSetNavigationDrawerEvents();
        setRecylerView();
        getOnBackPressedDispatcher().addCallback(this, getHandleOnBackPressed());
    }

    private void onSetNavigationDrawerEvents() {
        binder.layoutDashboard.navigationBar.setOnClickListener(this);
        binder.layoutSideMenu.headerDashboard.imageViewLogout.setOnClickListener(this);
        binder.layoutSideMenu.headerDashboard.textViewLogout.setOnClickListener(this);
    }

    private void setRecylerView() {
        adapter = new DrawerAdapter(this);
        binder.layoutSideMenu.recyclerView.setLayoutManager(new CustomLinearLayoutManager(this, LinearLayout.VERTICAL, false));
        binder.layoutSideMenu.recyclerView.setAdapter(adapter);
        binder.layoutSideMenu.recyclerView.setHasFixedSize(true);
        /*
        binder.layoutSideMenu.recyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                    Log.d("MainActivity","ACTION_DOWN");
                    return false;
                    break;
                    case MotionEvent.ACTION_MOVE:
                    Log.d("MainActivity","ACTION_MOVE");
                    return false;
                    break;
                    case MotionEvent.ACTION_UP:
                    Log.d("MainActivity","ACTION_UP");
                    return false;
                }
                return false;
            }
        } );
        */
        binder.layoutSideMenu.recyclerView.addItemDecoration (
            new DividerItemDecoration (ContextCompat.getColor(getBaseContext(),R.color.purple_500), 1)
        );
        adapter.setItems(viewModel.getList());
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.navigation_bar) {
            binder.drawerLayout.openDrawer(binder.navigationView, true);
        } else if (view.getId() == R.id.image_view_logout) {
            showToast("iv_logout");
            binder.drawerLayout.closeDrawer(binder.navigationView, true);
        } else if (view.getId() == R.id.text_view_logout) {
            showToast("tv_logout");
            binder.drawerLayout.closeDrawer(binder.navigationView, true);
        } else {
            showToast("Default");
            binder.drawerLayout.closeDrawer(binder.navigationView, true);
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(DrawerModel model, int position) {
        showToast("onClick " + model.getText());
    }

    @Override
    public void onExpand(DrawerModel model, int position) {
        adapter.setExpand(model, position);
    }

    @Override
    public void onCompress(DrawerModel model, int position) {
        adapter.setCompress(model, position);
    }

    private OnBackPressedCallback getHandleOnBackPressed() {
        return new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (binder.drawerLayout.isDrawerOpen(binder.navigationView) == true) {
                    binder.drawerLayout.closeDrawer(binder.navigationView, true);
                } else {
                    finish();
                }
            }
        };
    }

    @Override
    public void onBackPressed() {
        if (binder.drawerLayout.isDrawerOpen(binder.navigationView) == true) {
            binder.drawerLayout.closeDrawer(binder.navigationView, true);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}