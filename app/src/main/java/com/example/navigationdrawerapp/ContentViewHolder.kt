package com.example.navigationdrawerapp

import android.view.View
import com.example.navigationdrawerapp.databinding.ContentBinder

class ContentViewHolder : BaseViewHolder {

    companion object {
        private val TAG = ContentViewHolder::class.java.getSimpleName()
    }

    private val binder : ContentBinder
    //private val constraintLayout : ConstraintLayout
    //private val imageView : ImageView
    //private val textView : TextView

    constructor(binder : ContentBinder, listeners : DrawerListener) : super(binder.getRoot(), listeners) {
        this.binder = binder
        //constraintLayout = itemView.findViewById(R.id.constraint_layout)
        //imageView = itemView.findViewById(R.id.image_view)
        //textView = itemView.findViewById(R.id.text_view)
    }

    public override fun bindDataToViewHolder(model : DrawerModel, position : Int) {
        //region Input Data
        binder.setHolder(model)
        binder.setPosition(position)
        binder.textView.setText(model.text)
        //endregion
        //region Set Listener
        binder.constraintLayout.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view : View) {
                getListener().onClick(model, position)
            }
        } )
        //endregion
    }
}