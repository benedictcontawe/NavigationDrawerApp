package com.example.navigationdrawerapp;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DividerItemDecoration extends RecyclerView.ItemDecoration{

    private static final String TAG = DividerItemDecoration.class.getSimpleName();

    private final ColorDrawable divider;
    private final int height;

    public DividerItemDecoration(int resId, int height) {
        this.divider = new ColorDrawable(resId);
        this.height = height;
    }

    @Override
    public void onDraw(@NonNull Canvas canvas, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(canvas, parent, state);
        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();

        final int childCount = parent.getChildCount();
        for (int index = 0; index < childCount; index++) {
            final View child = parent.getChildAt(index);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int top = child.getBottom() + params.bottomMargin;
            final int bottom = top + divider.getIntrinsicHeight();
            //if (child.getVisibility() == View.VISIBLE && child.getId() != -1) {
                divider.setBounds(left, top, right, bottom);
                divider.draw(canvas);
            //}
        }
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.bottom = height;
    }
}