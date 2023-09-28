package com.example.navigationdrawerapp

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import com.example.navigationdrawerapp.databinding.HeaderBinder

class HeaderViewHolder : BaseViewHolder {

    companion object {
        private val TAG = HeaderViewHolder::class.java.getSimpleName()
    }

    private val binder : HeaderBinder
    //private val constraintLayout : ConstraintLayout
    //private val imageView : ImageView
    //private val textView : TextView

    constructor(binder : HeaderBinder, listeners : DrawerListener) : super(binder.getRoot(), listeners) {
        this.binder = binder
        //constraintLayout = itemView.findViewById(R.id.constraint_layout)
        //imageView = itemView.findViewById(R.id.image_view)
        //textView = itemView.findViewById(R.id.text_view)
    }

    public override fun bindDataToViewHolder(model : DrawerModel, position : Int) {
        //region Input Data
        binder.setHolder(model)
        binder.setPosition(position)
        binder.executePendingBindings()
        binder.textView.setText(model.text)
        if (model.isExpand) {
            binder.imageView.setImageDrawable(ContextCompat.getDrawable(itemView.getContext(), R.drawable.ic_collapse))
        } else {
            binder.imageView.setImageDrawable(ContextCompat.getDrawable(itemView.getContext(), R.drawable.ic_expand))
        }
        //endregion
        //region Set Listener
        binder.constraintLayout.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view : View) {
                if (model.isExpand) {
                    animateUp()
                    getListener().onCompress(model, position)
                } else {
                    animateDown()
                    getListener().onExpand(model, position)
                }
            }
        } )
        //endregion
    }

    private fun animateDown() {
        val objectAnimator : ObjectAnimator = ObjectAnimator.ofFloat(binder.imageView, View.ROTATION, -180f, 0f)
        objectAnimator.setDuration(250)
        objectAnimator.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationStart(animation : Animator) {
                super.onAnimationStart(animation)
                Log.d(TAG, "animateDown onAnimationStart")
            }

            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
                Log.d(TAG, "animateDown onAnimationEnd")
            }
        } )
        objectAnimator.addUpdateListener(object : ValueAnimator.AnimatorUpdateListener {
            override fun onAnimationUpdate(valueAnimator : ValueAnimator) {
                Log.d(TAG, "animateDown onAnimationUpdate")
            }
        } )
        objectAnimator.start()
    }

    private fun animateUp() {
        val objectAnimator : ObjectAnimator = ObjectAnimator.ofFloat(binder.imageView, View.ROTATION, 0f, -180f)
        objectAnimator.setDuration(250)
        objectAnimator.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationStart(animation : Animator) {
                super.onAnimationStart(animation)
                Log.d(TAG, "animateUp onAnimationStart")
            }

            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
                Log.d(TAG, "animateUp onAnimationEnd")
            }
        } )
        objectAnimator.addUpdateListener( ValueAnimator.AnimatorUpdateListener() { valueAnimator ->
            Log.d(TAG, "animateUp onAnimationUpdate")
        } )
        objectAnimator.start()
    }
}