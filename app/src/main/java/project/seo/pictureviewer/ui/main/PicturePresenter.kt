package project.seo.pictureviewer.ui.main

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import project.seo.pictureviewer.network.RetrofitService

class PicturePresenter(
    private val view: PictureContract.View
) : PictureContract.Presenter {

    override fun onItemClick(pictureId: Int) {
        view.showDetail(pictureId)
    }

    override fun start() {
        view.lifecycleScope.launch {
            view.showLoadingBar()
            val pictureInfo = withContext(Dispatchers.IO) {
                RetrofitService.getPicture()
            }
            view.hideLoadingBar()
            view.showPictures(pictureInfo)
        }
    }
}