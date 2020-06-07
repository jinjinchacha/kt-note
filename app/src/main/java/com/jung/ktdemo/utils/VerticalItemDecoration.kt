package com.jung.ktdemo.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class VerticalItemDecoration(private val paddingStart: Int, paddingMiddle: Int, private val paddingEnd: Int) : RecyclerView.ItemDecoration() {
    private val paddingMiddle: Int = paddingMiddle / 2

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position = parent.getChildAdapterPosition(view)
        val layoutManager = parent.layoutManager ?: return

        val totalSize = layoutManager.itemCount

        when (position) {
            0 -> {
                outRect.top = paddingStart
                outRect.bottom = paddingMiddle
            }
            totalSize - 1 -> {
                outRect.bottom = paddingEnd
                outRect.top = paddingMiddle
            }
            else -> {
                outRect.top = paddingMiddle
                outRect.bottom = paddingMiddle
            }
        }
    }
}