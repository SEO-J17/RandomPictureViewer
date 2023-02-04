package project.seo.pictureviewer.data.repository

import androidx.paging.Pager
import project.seo.pictureviewer.data.model.DataPicture

interface PictureRepository {
    fun fetchPictures(): Pager<Int, DataPicture>
    suspend fun getDetail(id: Int): DataPicture?
}