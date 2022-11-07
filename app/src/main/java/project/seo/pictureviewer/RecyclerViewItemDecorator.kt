package project.seo.pictureviewer

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewItemDecorator : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State,
    ) {
        val position = parent.getChildAdapterPosition(view)
        val itemCount = state.itemCount

        with(outRect) {
            when (position) {
                0 -> {
                    bottom = 10
                    right = 5
                }
                1 -> {
                    bottom = 10
                    left = 5
                }
                itemCount - 1 -> {
                    left = 5
                }
                itemCount - 2 -> {
                    right = 5
                }
                else -> {
                    if (position % 2 == 0) {
                        bottom = 10
                        right = 5
                    } else {
                        bottom = 10
                        left = 5
                    }
                }
            }
        }
    }
}