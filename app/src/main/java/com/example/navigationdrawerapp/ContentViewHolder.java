package com.example.navigationdrawerapp;

import android.view.View;

import com.example.navigationdrawerapp.databinding.ContentBinder;

public class ContentViewHolder extends BaseViewHolder{

    private final String TAG = ContentViewHolder.class.getSimpleName();

    private final ContentBinder binder;
    //private final ConstraintLayout constraintLayout;
    //private final ImageView imageView;
    //private final TextView textView;

    public ContentViewHolder(ContentBinder binder, DrawerListener listener) {
        super(binder.getRoot(), listener);
        this.binder = binder;
        //constraintLayout = itemView.findViewById(R.id.constraint_layout);
        //imageView = itemView.findViewById(R.id.image_view);
        //textView = itemView.findViewById(R.id.text_view);
    }

    @Override
    void bindDataToViewHolder(DrawerModel model, int position) {
        //region Input Data
        binder.setHolder(model);
        binder.setPosition(position);
        binder.executePendingBindings();
        binder.textView.setText(model.getText());
        binder.textView.setContentDescription(model.getText());
        //endregion
        //region Set Listener
        binder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getListener().onClick(model, position);
            }
        } );
        //endregion
    }
}