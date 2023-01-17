package project.seo.pictureviewer.data.network

import retrofit2.await
import javax.inject.Inject

class ResponseService @Inject constructor(
    private val responseApi: PicturesAPI,
) {
    suspend fun getPictures(page: Int, limit: Int): List<PictureResponse> {
        return runCatching {
            responseApi.getPicture(page, limit).await()
        }.fold(
            onSuccess = { response ->
                response
            },
            onFailure = {
                emptyList()
            }
        )
    }

    suspend fun getPictures(id: Int): PictureResponse? {
        return runCatching {
            responseApi.getPicture(id).await()
        }.fold(
            onSuccess = { response ->
                response
            },
            onFailure = {
                null
            }
        )
    }
}