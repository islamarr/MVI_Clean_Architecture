package com.islam.mvisample.presentation.view.main.adapter

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

class GridSpacingItemDecoration(private val spanCount: Int, private val spacing: Int) :
    ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        val column = position % spanCount
        outRect.left = spacing - column * spacing / spanCount
        outRect.right = (column + 1) * spacing / spanCount
        if (position < spanCount) {
            outRect.top = spacing
        }
        outRect.bottom = spacing
    }
}