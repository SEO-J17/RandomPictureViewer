package project.seo.pictureviewer.data

import androidx.paging.Pager
import kotlinx.coroutines.flow.Flow

interface PictureRepository {
    fun fetchPictures(): Pager<Int, Picture>
    fun getDetail(id: Int): Flow<Picture?>
}