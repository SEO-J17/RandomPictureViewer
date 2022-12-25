package project.seo.pictureviewer.data

import androidx.paging.Pager
import androidx.paging.PagingConfig

class PictureRepository {
    fun fetchPictures(): Pager<Int, Picture> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false),
            pagingSourceFactory = {
                PicturePagingSource()
            }
        )
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 20
    }
}