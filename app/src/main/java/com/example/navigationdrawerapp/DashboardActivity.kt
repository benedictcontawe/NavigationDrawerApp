package com.example.navigationdrawerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.navigationdrawerapp.databinding.DashboardBinder

class DashboardActivity : AppCompatActivity(), View.OnClickListener, DrawerListener {

    companion object {
        private val TAG = DashboardActivity::class.java.getSimpleName()
    }

    private var binder : DashboardBinder? = null
    private val viewModel : DashboardViewModel by lazy { ViewModelProvider(this@DashboardActivity).get(DashboardViewModel::class.java) }
    private var adapter : DrawerAdapter? = null

    override fun onCreate(savedInstanceState : Bundle?) {
        binder = DataBindingUtil.setContentView(this@DashboardActivity, R.layout.activity_dashboard)
        binder?.setViewModel(viewModel)
        binder?.setLifecycleOwner(this@DashboardActivity)
        super.onCreate(savedInstanceState)
        onSetNavigationDrawerEvents()
        setRecyclerView()
        getOnBackPressedDispatcher().addCallback(this@DashboardActivity, getHandleOnBackPressed())
    }

    private fun onSetNavigationDrawerEvents() {
        binder?.layoutDashboard?.navigationBar?.setOnClickListener(this)
        binder?.layoutSideMenu?.headerDashboard?.imageViewLogout?.setOnClickListener(this@DashboardActivity)
        binder?.layoutSideMenu?.headerDashboard?.textViewLogout?.setOnClickListener(this@DashboardActivity)
    }

    private fun setRecyclerView() {
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
        binder?.layoutSideMenu?.recyclerView?.addItemDecoration (
            DividerItemDecoration (
                ContextCompat.getColor(getBaseContext(),R.color.purple_500),
                1
            )
        )
        adapter?.setItems(viewModel.getList())
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
        showToast("onClick ${model.text}")
    }

    override fun onExpand(model : DrawerModel, position : Int) {
        adapter?.setExpand(model, position)
        if (adapter?.isLastHeader(position) == true) {
            binder?.layoutSideMenu?.recyclerView?.smoothScrollToPosition(adapter?.getLastPosition() ?: 0)
        }
    }

    override fun onCompress(model : DrawerModel, position : Int) {
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