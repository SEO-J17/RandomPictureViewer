package project.seo.pictureviewer

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import project.seo.pictureviewer.data.PictureData
import project.seo.pictureviewer.network.RetrofitService

class MainPresenter(
    private val view: MainContract.View
) : MainContract.Presenter {
    private val dataset = mutableListOf<PictureData>()

    override fun onItemClick(position: Int) {
        view.showDetail(position)
    }

    override fun start() {
        view.lifecycleScope.launch {
            view.showLoadingBar()
            dataset.clear()
            val pictureInfo = withContext(Dispatchers.IO) {
                RetrofitService.getPicture()
            }
            dataset.addAll(
                pictureInfo.map {
                    PictureData(it)
                }
            )
            view.hideLoadingBar()
            view.showList(dataset)
        }
    }

    override fun getDataSet() = dataset

}
