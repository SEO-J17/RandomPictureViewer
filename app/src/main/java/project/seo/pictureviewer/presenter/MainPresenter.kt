package project.seo.pictureviewer.presenter

import project.seo.pictureviewer.contract.MainContract

class MainPresenter(
    private val view: MainContract.View
) : MainContract.Presenter {
    override fun start() {
        view.showPictureFragment()
    }
}
