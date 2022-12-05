package project.seo.pictureviewer.contract

import android.net.Uri
import kotlinx.coroutines.CoroutineScope

interface DetailContract {
    interface View {
        val lifecycleScope: CoroutineScope

        fun showPicture(url: String)

        fun showId(id: Int)

        fun showAuthor(author: String)

        fun showWidth(width: String)

        fun showHeight(height: String)

        fun showUrl(url: String)

        fun showDownloadUrl(downloadUrl: String)

        fun showErrorToast(message: String)

        fun showPreviousPreview(url: String, pictureId: Int)

        fun showCurrentPreview(url: String)

        fun showNextPreview(url: String, pictureId: Int)

        fun showPictureWebSite(url: Uri)

        fun showNextPage(id: Int)

        fun showPreviousPage(id: Int)

        fun showPreviousNoImage(id: Int)

        fun showNextNoImage(id: Int)

    }

    interface Presenter {
        fun start(pictureId: Int)

        suspend fun setNextPreview(nextId: Int)

        suspend fun setPreviousPreview(previousId: Int)
    }
}