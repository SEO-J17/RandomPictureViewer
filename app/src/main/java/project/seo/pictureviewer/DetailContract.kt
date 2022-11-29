package project.seo.pictureviewer

import android.widget.ImageView
import project.seo.pictureviewer.data.PictureData

interface DetailContract {
    interface View {
        fun showErrorToast(message: String)

        fun showPreview(previewPicture: ImageView, dataSet: List<PictureData>, position: Int)

        fun showPictureWebSite(url: String)

        fun showPictureInfo(dataSet: List<PictureData>, position: Int)

    }

    interface Presenter {
        fun getPictureInfo(dataSet: List<PictureData>, position: Int)
    }
}