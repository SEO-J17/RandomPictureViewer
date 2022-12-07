package project.seo.pictureviewer.ui.main

import kotlinx.coroutines.CoroutineScope
import project.seo.pictureviewer.data.PictureInfo

interface PictureContract {
    interface View {
        val lifecycleScope: CoroutineScope

        fun showLoadingBar()

        fun hideLoadingBar()

        fun showPictures(pictureInfo: PictureInfo)

        fun showDetail(pictureId: Int)

    }

    interface Presenter {
        fun start()

        fun onItemClick(pictureId: Int)

    }
}