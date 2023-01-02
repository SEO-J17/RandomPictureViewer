package project.seo.pictureviewer.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import project.seo.pictureviewer.data.database.PictureDao
import project.seo.pictureviewer.data.database.PictureEntity
import project.seo.pictureviewer.data.network.ResponseService
import javax.inject.Inject

class PictureRepositoryImpl @Inject constructor(
    private val remoteService: ResponseService,
    private val localService: PictureDao,
) : PictureRepository {
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

    override fun getDetail(id: Int): Flow<Picture?> {
        return flow {
            localService.getItem(id)?.let {
                runCatching {
                    it.collect { entity ->
                        emit(Picture(entity))
                    }
                }
            } ?: run {
                remoteService.getPictureData(id)?.let { data ->
                    emit(Picture(data))
                    localService.insertItem(
                        PictureEntity(
                            id = data.id,
                            author = data.author,
                            width = data.width,
                            height = data.height,
                            url = data.url,
                            downloadUrl = data.downloadUrl
                        )
                    )
                }
            }
        }
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 20
    }
}