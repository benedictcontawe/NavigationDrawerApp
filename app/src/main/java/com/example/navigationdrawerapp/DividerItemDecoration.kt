package com.example.navigationdrawerapp

import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.ColorDrawable
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class DividerItemDecoration : RecyclerView.ItemDecoration {


    companion object {
        private val TAG = DividerItemDecoration::class.java.getSimpleName()
    }

    private val divider : ColorDrawable
    private val height : Int

    constructor(resId : Int, height : Int) {
        divider = ColorDrawable(resId)
        this.height = height
    }

    override fun onDraw(canvas : Canvas, parent : RecyclerView, state : RecyclerView.State) {
        super.onDraw(canvas, parent, state)

        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight

        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child : View = parent.getChildAt(i)
            val params = child.getLayoutParams() as RecyclerView.LayoutParams
            val top: Int = child.getBottom() + params.bottomMargin
            val bottom = top + divider!!.intrinsicHeight
            divider.setBounds(left, top, right, bottom)
            divider.draw(canvas)
        }

    }

    override fun getItemOffsets(outRect : Rect, view : View, parent : RecyclerView, state : RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.bottom = height
    }
}