package project.seo.pictureviewer.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import project.seo.pictureviewer.data.database.PictureDao
import project.seo.pictureviewer.data.database.PictureEntity
import project.seo.pictureviewer.data.network.PicturesAPI
import retrofit2.awaitResponse
import javax.inject.Inject

class PictureRepositoryImpl @Inject constructor(
    private val remoteService: PicturesAPI,
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

    override suspend fun getDetail(id: Int): Picture? {
        return localService.getItem(id)?.let {
            Picture(it)
        } ?: run {
            runCatching {
                remoteService.getPictureData(id).awaitResponse()
            }.onSuccess { response ->
                response.body()?.let { picture ->
                    Picture(picture).also { data ->
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
            null
        }
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 20
    }
}