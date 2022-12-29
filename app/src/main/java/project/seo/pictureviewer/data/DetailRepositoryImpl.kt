package project.seo.pictureviewer.data

import project.seo.pictureviewer.database.PictureDao
import project.seo.pictureviewer.database.PictureEntity
import project.seo.pictureviewer.network.PicturesAPI
import retrofit2.awaitResponse
import javax.inject.Inject

class DetailRepositoryImpl @Inject constructor(
    private val remoteService: PicturesAPI,
    private val localService: PictureDao,
) : DetailRepository {
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
}