package com.example.navigationdrawerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import com.example.navigationdrawerapp.databinding.MainBinder

class DashboardActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        private val TAG = DashboardActivity::class.java.getSimpleName()
    }

    private var binder : MainBinder? = null

    override fun onCreate(savedInstanceState : Bundle?) {
        binder = DataBindingUtil.setContentView(this@DashboardActivity, R.layout.activity_dashboard)
        binder?.setLifecycleOwner(this@DashboardActivity)
        super.onCreate(savedInstanceState)
        onSetNavigationDrawerEvents()
        getOnBackPressedDispatcher().addCallback(this@DashboardActivity, getHandleOnBackPressed())
    }

    private fun onSetNavigationDrawerEvents() {
        //drawerLayout.openDrawer(GravityCompat.START)
        binder?.layoutDashboard?.navigationBar?.setOnClickListener(this)
        binder?.layoutSideMenu?.linearLayoutFirst?.setOnClickListener(this)
        binder?.layoutSideMenu?.linearLayoutSecond?.setOnClickListener(this)
        binder?.layoutSideMenu?.linearLayoutThird?.setOnClickListener(this)
        binder?.layoutSideMenu?.linearLayoutFourth?.setOnClickListener(this)
        binder?.layoutSideMenu?.linearLayoutFifth?.setOnClickListener(this)
        binder?.layoutSideMenu?.linearLayoutSixth?.setOnClickListener(this)
        binder?.layoutSideMenu?.linearLayoutSeventh?.setOnClickListener(this)
        binder?.layoutSideMenu?.headerDashboard?.imageViewLogout?.setOnClickListener(this)
        binder?.layoutSideMenu?.headerDashboard?.textViewLogout?.setOnClickListener(this)
    }

    override fun onClick(view : View) {
        when (view.id) {
            R.id.navigation_bar -> binder?.drawerLayout?.openDrawer(binder?.navigationView!!, true)
            R.id.linear_layout_First -> {
                showToast("linear_layout_First")
                binder?.drawerLayout?.closeDrawer(binder?.navigationView!!, true)
            }
            R.id.linear_layout_Second -> {
                showToast("linear_layout_Second")
                binder?.drawerLayout?.closeDrawer(binder?.navigationView!!, true)
            }
            R.id.linear_layout_Third -> {
                showToast("linear_layout_Third")
                binder?.drawerLayout?.closeDrawer(binder?.navigationView!!, true)
            }
            R.id.linear_layout_Fourth -> {
                showToast("linear_layout_Fourth")
                binder?.drawerLayout?.closeDrawer(binder?.navigationView!!, true)
            }
            R.id.linear_layout_Fifth -> {
                showToast("linear_layout_Fifth")
                binder?.drawerLayout?.closeDrawer(binder?.navigationView!!, true)
            }
            R.id.linear_layout_Sixth -> {
                showToast("linear_layout_Sixth")
                binder?.drawerLayout?.closeDrawer(binder?.navigationView!!, true)
            }
            R.id.linear_layout_Seventh -> {
                showToast("linear_layout_Seventh")
                binder?.drawerLayout?.closeDrawer(binder?.navigationView!!, true)
            }
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

    private fun showToast(message: String) {
        Toast.makeText(this@DashboardActivity, message, Toast.LENGTH_SHORT).show()
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