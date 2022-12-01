package project.seo.pictureviewer

import project.seo.pictureviewer.data.PictureData

class DetailPresenter(
    private val view: DetailContract.View
) : DetailContract.Presenter {
    override fun getPictureInfo(
        dataSet: List<PictureData>,
        position: Int

    ){
        view.showPictureInfo(dataSet, position)
    }
}