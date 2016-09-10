package com.leo.example.wdight;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.leo.example.util.LocationUtils;

/**
 * Created by leo on 16/7/22.
 */
public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
    private int space;
    private int rowCount;

    public SpacesItemDecoration(int space, int lineCount) {
        this.space = space;
        this.rowCount = lineCount;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        // Add top margin only for the first item to avoid double space between items
        int position = parent.getChildLayoutPosition(view);
        int size = parent.getAdapter().getItemCount();
        if (LocationUtils.isLeft(position, rowCount)) {
            outRect.left = space * 2;
        } else {
            outRect.left = space;
        }
        if (LocationUtils.isRight(position, rowCount)) {
            outRect.right = space * 2;
        } else {
            outRect.right = space;
        }
        if (LocationUtils.isFirstLine(position, rowCount)) {
            outRect.top = space * 2;
        } else {
            outRect.top = space;
        }
        if (LocationUtils.isLastLine(position, size, rowCount)) {
            outRect.bottom = space * 2;
        } else {
            outRect.bottom = space;
        }
    }
}

