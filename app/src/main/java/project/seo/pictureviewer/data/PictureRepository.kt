package project.seo.pictureviewer.data

import androidx.paging.Pager

interface PictureRepository {
    fun fetchPictures(): Pager<Int, Picture>
    suspend fun getDetail(id: Int): Picture?
}