package project.seo.pictureviewer.domain

import androidx.paging.Pager
import androidx.paging.PagingConfig
import project.seo.pictureviewer.data.source.PicturePagingSource
import project.seo.pictureviewer.data.database.PictureDao
import project.seo.pictureviewer.data.database.PictureEntity
import project.seo.pictureviewer.data.model.DataPicture
import project.seo.pictureviewer.data.network.ResponseService
import project.seo.pictureviewer.data.repository.PictureRepository
import javax.inject.Inject

class PictureRepositoryImpl @Inject constructor(
    private val remoteService: ResponseService,
    private val localService: PictureDao,
) : PictureRepository {
    override fun fetchPictures(): Pager<Int, DataPicture> {
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

    override suspend fun getDetail(id: Int): DataPicture? {
        return localService.getPicture(id)?.let {
            DataPicture(it)
        } ?: run {
            remoteService.getPictures(id)?.let { response ->
                DataPicture(response).also { data ->
                    localService.insert(
                        PictureEntity(data)
                    )
                }
            }
        }
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 20
    }
}