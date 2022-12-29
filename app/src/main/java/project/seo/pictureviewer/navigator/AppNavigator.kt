package project.seo.pictureviewer.navigator

interface AppNavigator {
    fun navigateTo(id: Int?, screen: Screens)

    companion object {
        const val PICTURE_KEY = "pictureId"
    }
}