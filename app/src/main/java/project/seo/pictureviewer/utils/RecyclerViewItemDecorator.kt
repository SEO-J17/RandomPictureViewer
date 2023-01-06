package project.seo.pictureviewer.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewItemDecorator(
    private val heightSpace: Int = 20,
    private val widthSpace: Int = 10
) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State,
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        val position = parent.getChildAdapterPosition(view)
        val itemCount = state.itemCount
        val layoutManager = parent.layoutManager as? GridLayoutManager ?: return
        val layoutParams = view.layoutParams as? GridLayoutManager.LayoutParams ?: return
        val spanCount = layoutManager.spanCount
        val spanIndex = layoutParams.spanIndex

        with(outRect) {
            top = heightSpace
            if (spanIndex == 0) {
                left = widthSpace * 2
                right = widthSpace
            } else {
                right = widthSpace * 2
                left = widthSpace
            }
        }
    }
}