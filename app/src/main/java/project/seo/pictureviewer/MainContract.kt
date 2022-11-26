package project.seo.pictureviewer

import kotlinx.coroutines.CoroutineScope
import project.seo.pictureviewer.data.PictureData

interface MainContract {
    interface View {
        val lifecycleScope: CoroutineScope

        fun showLoadingBar()

        fun hideLoadingBar()

        fun showList(pictureInfo: MutableList<PictureData>)

        fun showDetail(position: Int)

    }

    interface Presenter {
        fun start()

        fun onItemClick(position: Int)
    }
}