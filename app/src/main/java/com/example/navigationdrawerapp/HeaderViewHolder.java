package com.example.navigationdrawerapp;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.BlendMode;
import android.graphics.BlendModeColorFilter;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.example.navigationdrawerapp.databinding.HeaderBinder;

public class HeaderViewHolder extends BaseViewHolder {

    private final String TAG = BaseViewHolder.class.getSimpleName();

    private final HeaderBinder binder;
    //private final ConstraintLayout constraintLayout;
    //private final ImageView imageView;
    //private final TextView textView;

    public HeaderViewHolder(HeaderBinder binder, DrawerListener listener) {
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
        binder.textView.setContentDescription(model.getText() + " Header " + (model.getExpand() ? "Expanded" : "Collapsed"));
        initializeImage();
        //endregion
        //region Set Listener
        binder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (model.getExpand()) {
                    animateUp();
                    getListener().onCompress(model, position);
                } else {
                    animateDown();
                    getListener().onExpand(model, position);
                }
            }
        } );
        //endregion
    }

    private void initializeImage() {
        final Drawable drawable;
        if (binder.getHolder().getExpand() == true && Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            drawable = ContextCompat.getDrawable(itemView.getContext(), R.drawable.ic_collapse);
            drawable.setColorFilter (new BlendModeColorFilter(binder.textView.getCurrentTextColor(), BlendMode.SRC_ATOP));
        } else if (binder.getHolder().getExpand() == true) {
            drawable = ContextCompat.getDrawable(itemView.getContext(), R.drawable.ic_collapse);
            drawable.setColorFilter(binder.textView.getCurrentTextColor(), PorterDuff.Mode.SRC_ATOP);
        } else if (binder.getHolder().getExpand() == false && Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            drawable = ContextCompat.getDrawable(itemView.getContext(), R.drawable.ic_expand);
            drawable.setColorFilter (new BlendModeColorFilter(binder.textView.getCurrentTextColor(), BlendMode.SRC_ATOP));
        } else {
            drawable = ContextCompat.getDrawable(itemView.getContext(), R.drawable.ic_expand);
            drawable.setColorFilter(binder.textView.getCurrentTextColor(), PorterDuff.Mode.SRC_ATOP);
        }
        binder.imageView.setImageDrawable(drawable);
    }

    private void animateDown() {
        final ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(binder.imageView, View.ROTATION, -180f, 0f);
        objectAnimator.setDuration(250);
        objectAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(@NonNull Animator animation, boolean isReverse) {
                super.onAnimationStart(animation, isReverse);
                Log.d(TAG, "animateDown onAnimationStart");
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                Log.d(TAG, "animateDown onAnimationEnd");
            }
        } );

        objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(@NonNull ValueAnimator valueAnimator) {
                Log.d(TAG, "animateDown onAnimationUpdate");
            }
        });

        objectAnimator.start();
    }

    private void animateUp() {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(binder.imageView, View.ROTATION, 0f, -180f);
        objectAnimator.setDuration(250);
        objectAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(@NonNull Animator animation, boolean isReverse) {
                super.onAnimationStart(animation, isReverse);
                Log.d(TAG, "animateUp onAnimationStart");
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                Log.d(TAG, "animateUp onAnimationEnd");
            }
        });

        objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(@NonNull ValueAnimator valueAnimator) {
                Log.d(TAG, "animateUp onAnimationUpdate");
            }
        });
        objectAnimator.start();
    }
}