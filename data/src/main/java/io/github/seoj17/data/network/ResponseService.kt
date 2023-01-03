package io.github.seoj17.data.network

import retrofit2.awaitResponse
import javax.inject.Inject

class ResponseService @Inject constructor(
    private val responseApi: PicturesAPI,
) {
    suspend fun getPictures(page: Int, limit: Int): List<PictureResponse>? {
        return runCatching {
            responseApi.getPicture(page, limit).awaitResponse()
        }.fold(
            onSuccess = {
                it.body()
            },
            onFailure = {
                null
            },
        )
    }

    suspend fun getPictureData(id: Int): PictureResponse? {
        return runCatching {
            responseApi.getPictureData(id).awaitResponse()
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