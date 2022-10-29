package project.seo.pictureviewer

import project.seo.pictureviewer.data.PictureData
import project.seo.pictureviewer.data.PictureInfo

object QueryUtils {
    private val dataSet = mutableListOf<PictureData>()
    fun extractData(pictureData: PictureInfo?): MutableList<PictureData> {
        return (pictureData?.let { pictureInfo ->
            pictureInfo.map { data ->
                PictureData(data)
            }
        } ?: listOf()).also {
            dataSet.clear()
            dataSet.addAll(it)
        } as MutableList<PictureData>
    }

    fun get(position: Int): PictureData? =
        dataSet.getOrNull(position)

    fun getSize(): Int = dataSet.size
}