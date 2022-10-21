package project.seo.pictureviewer

import project.seo.pictureviewer.data.MainPicture
import project.seo.pictureviewer.data.PictureInfo

object QueryUtils {
    fun extractData(pictureData: PictureInfo?): MutableList<MainPicture> {
        val pictureList = mutableListOf<MainPicture>()
        pictureData?.let { picture ->
            picture.forEach { data ->
                pictureList.add(MainPicture(data))
            }
        }
        return pictureList
    }
}