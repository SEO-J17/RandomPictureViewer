package project.seo.pictureviewer

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewItemDecorator : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State,
    ) {
        val itemCount = state.itemCount
        with(parent.getChildAdapterPosition(view)) {
            if (this == 0) {
                outRect.bottom = 10
                outRect.right = 5
            } else if (this == 1) {
                outRect.bottom = 10
                outRect.left = 5
            } else if (this == itemCount - 1) { //마지막 줄 오른쪽 아이템
                outRect.left = 5
            } else if (this == itemCount - 2) { //마지막 줄 왼쪽 아이템
                outRect.right = 5
            } else if (this % 2 == 0) { //왼쪽 아이템
                outRect.bottom = 10
                outRect.right = 5
            } else if (this % 2 != 0) { //오른쪽 아이템
                outRect.bottom = 10
                outRect.left = 5
            }
        }
    }
}