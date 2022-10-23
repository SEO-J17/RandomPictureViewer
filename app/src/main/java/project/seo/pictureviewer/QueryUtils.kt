package project.seo.pictureviewer

import project.seo.pictureviewer.data.PictureData
import project.seo.pictureviewer.data.PictureInfo

object QueryUtils {
    lateinit var dataSet: MutableList<PictureData>
    fun extractData(pictureData: PictureInfo?): MutableList<PictureData> {
        val pictureList = mutableListOf<PictureData>()
        pictureData?.let { picture ->
            picture.forEach { data ->
                pictureList.add(PictureData(data))
            }
        }
        dataSet = pictureList
        return pictureList
    }
}