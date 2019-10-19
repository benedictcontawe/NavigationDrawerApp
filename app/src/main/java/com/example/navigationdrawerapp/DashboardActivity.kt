package com.example.navigationdrawerapp

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_dashboard.*
import kotlinx.android.synthetic.main.header_menu.*
import kotlinx.android.synthetic.main.layout_dashboard.*
import kotlinx.android.synthetic.main.layout_side_menu.*

class DashboardActivity : Activity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        onSetNavigationDrawerEvents()
    }

    private fun onSetNavigationDrawerEvents() {
        //drawerLayout.openDrawer(GravityCompat.START)
        navigationBar.setOnClickListener(this)
        ll_First.setOnClickListener(this)
        ll_Second.setOnClickListener(this)
        ll_Third.setOnClickListener(this)
        ll_Fourth.setOnClickListener(this)
        ll_Fifth.setOnClickListener(this)
        ll_Sixth.setOnClickListener(this)
        ll_Seventh.setOnClickListener(this)
        iv_logout.setOnClickListener(this)
        tv_logout.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.navigationBar -> drawerLayout.openDrawer(navigationView, true)
            R.id.ll_First -> {
                showToast("ll_First")
                drawerLayout.closeDrawer(navigationView, true)
            }
            R.id.ll_Second -> {
                showToast("ll_Second")
                drawerLayout.closeDrawer(navigationView, true)
            }
            R.id.ll_Third -> {
                showToast("ll_Third")
                drawerLayout.closeDrawer(navigationView, true)
            }
            R.id.ll_Fourth -> {
                showToast("ll_Fourth")
                drawerLayout.closeDrawer(navigationView, true)
            }
            R.id.ll_Fifth -> {
                showToast("ll_Fifth")
                drawerLayout.closeDrawer(navigationView, true)
            }
            R.id.ll_Sixth -> {
                showToast("ll_Sixth")
                drawerLayout.closeDrawer(navigationView, true)
            }
            R.id.ll_Seventh -> {
                showToast("ll_Seventh")
                drawerLayout.closeDrawer(navigationView, true)
            }
            R.id.iv_logout -> {
                showToast("iv_logout")
                drawerLayout.closeDrawer(navigationView, true)
            }
            R.id.tv_logout -> {
                showToast("tv_logout")
                drawerLayout.closeDrawer(navigationView, true)
            }
            else -> {
                showToast("Default")
                drawerLayout.closeDrawer(navigationView, true)
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this@DashboardActivity, message, Toast.LENGTH_SHORT).show()
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(navigationView)) {
            drawerLayout.closeDrawer(navigationView, true)
        } else {
            super.onBackPressed()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}