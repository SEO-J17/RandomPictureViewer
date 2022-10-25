package project.seo.pictureviewer

import project.seo.pictureviewer.data.PictureData
import project.seo.pictureviewer.data.PictureInfo

object QueryUtils {
    //detail 액티비티,메인 액티비티에서 data셋을 사용할 수 있도록 object에서 프로퍼티로 선언했다.
    lateinit var dataSet: MutableList<PictureData>
    //json으로 받아온 데이터를 for문으로 돌려서 데이터를 list에 저장하고 dataSet에 저장한뒤, 반환했다.
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