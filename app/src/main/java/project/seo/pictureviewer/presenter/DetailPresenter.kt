package project.seo.pictureviewer.presenter

import android.net.Uri
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import project.seo.pictureviewer.R
import project.seo.pictureviewer.contract.DetailContract
import project.seo.pictureviewer.network.RetrofitService
import retrofit2.HttpException

class DetailPresenter(
    private val view: DetailContract.View,
    private val pictureId: Int
) : DetailContract.Presenter {
    override fun start() {
        view.lifecycleScope.launch {
            val pictureDetail = withContext(Dispatchers.IO) {
                RetrofitService.getPictureData(pictureId)
            }
            with(pictureDetail) {
                val nextId = id + 1
                val previousId = id - 1
                view.showPicture(downloadUrl)
                view.showId(id)
                view.showAuthor(author)
                view.showWidth(width)
                view.showHeight(height)
                view.showUrl(url)
                view.showDownloadUrl(downloadUrl)
                view.showCurrentPreview(downloadUrl)
                view.showPictureWebSite(Uri.parse(url))
                setPreviousPreview(previousId)
                setNextPreview(nextId)
                try {
                    view.showPreviousPage(previousId)
                    view.showNextPage(nextId)
                } catch (e: HttpException) {
                    view.showErrorToast("더 이상 이동 할 수 없습니다.")
                }
            }
        }
    }

    override suspend fun setPreviousPreview(previousId: Int) {
        try {
            val preview = withContext(Dispatchers.IO) {
                RetrofitService.getPictureData(previousId)
            }
            view.showPreviousPreview(preview.downloadUrl, previousId)
        } catch (e: HttpException) {
            view.showPreviousNoImage(R.drawable.no_image)
        }
    }

    override suspend fun setNextPreview(nextId: Int) {
        try {
            val preview = withContext(Dispatchers.IO) {
                RetrofitService.getPictureData(nextId)
            }
            view.showNextPreview(preview.downloadUrl, nextId)
        } catch (e: HttpException) {
            view.showNextNoImage(R.drawable.no_image)
        }
    }
}