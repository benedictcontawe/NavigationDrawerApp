package com.example.navigationdrawerapp

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
        binder.textView.setText(model.text)
        if (model.isExpand) {
            binder.imageView.setImageDrawable(ContextCompat.getDrawable(itemView.getContext(), R.drawable.ic_expand_less))
        } else {
            binder.imageView.setImageDrawable(ContextCompat.getDrawable(itemView.getContext(), R.drawable.ic_expand_more))
        }
        //endregion
        //region Set Listener
        binder.constraintLayout.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view : View) {
                if (model.isExpand)
                    getListener().onCompress(model, position)
                else
                    getListener().onExpand(model, position)
            }
        } )
        //endregion
    }
}