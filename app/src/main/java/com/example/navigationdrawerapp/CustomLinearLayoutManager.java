package com.example.navigationdrawerapp;

import android.content.Context;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

public class CustomLinearLayoutManager extends LinearLayoutManager {

    private final String TAG = CustomLinearLayoutManager.class.getSimpleName();

    public CustomLinearLayoutManager(Context context) {
        super(context);
    }

    public CustomLinearLayoutManager(Context context, int orientation, Boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public CustomLinearLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int position) {
        super.smoothScrollToPosition(recyclerView, state, position);
        Log.d(TAG, "smoothScrollToPosition()");
        final LinearSmoothScroller linearSmoothScroller = new LinearSmoothScroller(recyclerView.getContext()) {
            private final float MILLISECONDS_PER_INCH = 500f, DISTANCE_IN_PIXELS = 500f, DURATION = 500f;

            @Override
            protected int getHorizontalSnapPreference() {
                return super.getHorizontalSnapPreference();
            }

            @Nullable
            @Override
            public PointF computeScrollVectorForPosition(int targetPosition) {
                return super.computeScrollVectorForPosition(targetPosition);
            }

            @Override
            protected int calculateTimeForScrolling(int dx) {
                //return super.calculateTimeForScrolling(dx);
                final float proportion = (float) dx / DISTANCE_IN_PIXELS;
                return (int) (DURATION * proportion);
            }

            @Override
            protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
                //return MILLISECONDS_PER_INCH / displayMetrics.densityDpi;
                return super.calculateSpeedPerPixel(displayMetrics);
            }
        };
        linearSmoothScroller.computeScrollVectorForPosition(position);
        linearSmoothScroller.setTargetPosition(position);
        startSmoothScroll(linearSmoothScroller);
    }
}