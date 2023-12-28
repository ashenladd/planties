package com.example.planties.features.utils;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
    private final int spaceHeight;
    private final boolean disableLastSpace;
    private final boolean isVertical;
    private final boolean isGrid;
    private final boolean isWithStartSpace;
    private final int startSpaceHeight;

    public SpaceItemDecoration(int spaceHeight, boolean disableLastSpace, boolean isVertical, boolean isGrid, boolean isWithStartSpace, int startSpaceHeight) {
        this.spaceHeight = spaceHeight;
        this.disableLastSpace = disableLastSpace;
        this.isVertical = isVertical;
        this.isGrid = isGrid;
        this.isWithStartSpace = isWithStartSpace;
        this.startSpaceHeight = startSpaceHeight;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (!isGrid) {
            if (disableLastSpace &&
                    parent.getChildAdapterPosition(view) != parent.getAdapter().getItemCount() - 1) {
                if (isVertical) {
                    outRect.bottom = spaceHeight;
                } else {
                    if (isWithStartSpace && parent.getChildAdapterPosition(view) == 0) {
                        outRect.left = startSpaceHeight;
                    }
                    outRect.right = spaceHeight;
                }
            } else if (!disableLastSpace) {
                if (isVertical) {
                    outRect.bottom = spaceHeight;
                } else {
                    if (isWithStartSpace && parent.getChildAdapterPosition(view) == 0) {
                        outRect.left = startSpaceHeight;
                    }
                    outRect.right = spaceHeight;
                }
            }
        } else {
            outRect.left = spaceHeight;
            outRect.right = spaceHeight;
            outRect.bottom = spaceHeight;
            outRect.top = spaceHeight;
        }
    }
}
