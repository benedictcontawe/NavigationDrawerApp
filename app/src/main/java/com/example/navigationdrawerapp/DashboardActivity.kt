package com.example.navigationdrawerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import com.example.navigationdrawerapp.databinding.DashboardBinder

class DashboardActivity : AppCompatActivity(), View.OnClickListener, DrawerListener {

    companion object {
        private val TAG = DashboardActivity::class.java.getSimpleName()
    }

    private var binder : DashboardBinder? = null
    private var adapter : DrawerAdapter? = null

    override fun onCreate(savedInstanceState : Bundle?) {
        binder = DataBindingUtil.setContentView(this@DashboardActivity, R.layout.activity_dashboard)
        binder?.setLifecycleOwner(this@DashboardActivity)
        super.onCreate(savedInstanceState)
        onSetNavigationDrawerEvents()
        setRecylerView()
        getOnBackPressedDispatcher().addCallback(this@DashboardActivity, getHandleOnBackPressed())
    }

    private fun onSetNavigationDrawerEvents() {
        binder?.layoutDashboard?.navigationBar?.setOnClickListener(this)
        binder?.layoutSideMenu?.headerDashboard?.imageViewLogout?.setOnClickListener(this@DashboardActivity)
        binder?.layoutSideMenu?.headerDashboard?.textViewLogout?.setOnClickListener(this@DashboardActivity)
    }

    private fun setRecylerView() {
        adapter = DrawerAdapter(this)
        binder?.layoutSideMenu?.recyclerView?.setLayoutManager(CustomLinearLayoutManager(this@DashboardActivity, LinearLayout.VERTICAL, false))
        binder?.layoutSideMenu?.recyclerView?.setAdapter(adapter)
        binder?.layoutSideMenu?.recyclerView?.setHasFixedSize(true)
        /*
        binder?.layoutSideMenu?.recyclerView?.setOnTouchListener(object : View.OnTouchListener{
            override fun onTouch(view : View?, event : MotionEvent) : Boolean {
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        Log.d("MainActivity","ACTION_DOWN")
                        return false
                    }
                    MotionEvent.ACTION_MOVE -> {
                        Log.d("MainActivity","ACTION_MOVE")
                        return false
                    }
                    MotionEvent.ACTION_UP -> {
                        Log.d("MainActivity","ACTION_UP")
                        return false
                    }
                }
                return false
            }
        })
        */
    }

    override fun onClick(view : View) {
        when (view.id) {
            R.id.navigation_bar -> binder?.drawerLayout?.openDrawer(binder?.navigationView!!, true)
            R.id.image_view_logout -> {
                showToast("iv_logout")
                binder?.drawerLayout?.closeDrawer(binder?.navigationView!!, true)
            }
            R.id.text_view_logout -> {
                showToast("tv_logout")
                binder?.drawerLayout?.closeDrawer(binder?.navigationView!!, true)
            }
            else -> {
                showToast("Default")
                binder?.drawerLayout?.closeDrawer(binder?.navigationView!!, true)
            }
        }
    }

    private fun showToast(message : String) {
        Toast.makeText(this@DashboardActivity, message, Toast.LENGTH_SHORT).show()
    }

    override fun onClick(model : DrawerModel, position : Int) {
        showToast("onClick $model $position")
    }

    override fun onExpand(model : DrawerModel, position : Int) {
        showToast("onExpand $model $position")
        adapter?.setExpand(model, position)
    }

    override fun onCompress(model : DrawerModel, position : Int) {
        showToast("onCompress $model $position")
        adapter?.setCompress(model, position)
    }

    private fun getHandleOnBackPressed() : OnBackPressedCallback {
        return object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (binder?.drawerLayout?.isDrawerOpen(binder?.navigationView!!) == true) {
                    binder?.drawerLayout?.closeDrawer(binder?.navigationView!!, true)
                } else {
                    finish()
                }
            }
        }
    }

    @Deprecated("Deprecated in Java", ReplaceWith("Use this method getHandleOnBackPressed"), DeprecationLevel.WARNING)
    override fun onBackPressed() {
        if (binder?.drawerLayout?.isDrawerOpen(binder?.navigationView!!) == true) {
            binder?.drawerLayout?.closeDrawer(binder?.navigationView!!, true)
        } else {
            super.onBackPressed()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}