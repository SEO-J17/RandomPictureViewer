package project.seo.pictureviewer.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import project.seo.pictureviewer.database.PictureDao
import project.seo.pictureviewer.network.PicturesAPI
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val remoteService: PicturesAPI,
    private val localService: PictureDao,
) : MainRepository {
    override fun fetchPictures(): Pager<Int, Picture> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                PicturePagingSource(localService, remoteService)
            }
        )
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 20
    }
}