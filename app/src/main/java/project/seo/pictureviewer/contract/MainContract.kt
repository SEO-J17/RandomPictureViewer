package project.seo.pictureviewer.contract

interface MainContract {
    interface View {
        fun showPictureFragment()

        fun showDetailFragment(pictureId: Int)

    }

    interface Presenter {
        fun start()
    }
}