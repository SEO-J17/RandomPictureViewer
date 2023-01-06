package project.seo.pictureviewer.data.network

import retrofit2.awaitResponse
import javax.inject.Inject

class ResponseService @Inject constructor(
    private val responseApi: PicturesAPI,
) {
    suspend fun getPictures(page: Int, limit: Int): List<PictureResponse> {
        return runCatching {
            responseApi.getPicture(page, limit).awaitResponse()
        }.fold(
            onSuccess = {
                it.body().orEmpty()
            },
            onFailure = {
                emptyList()
            }
        )
    }

    suspend fun getPictures(id: Int): PictureResponse? {
        return runCatching {
            responseApi.getPicture(id).awaitResponse()
        }.fold(
            onSuccess = {
                it.body()
            },
            onFailure = {
                null
            }
        )
    }
}