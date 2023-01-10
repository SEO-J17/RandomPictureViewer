package io.github.seoj17.data.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import io.github.seoj17.data.data.database.PictureDao
import io.github.seoj17.data.data.mapper.entityToDomainModel
import io.github.seoj17.data.data.mapper.responseToDomainModel
import io.github.seoj17.data.data.mapper.responseToEntity
import io.github.seoj17.data.data.network.ResponseService
import io.github.seoj17.domain.model.DomainModel
import io.github.seoj17.domain.repository.PictureRepository
import javax.inject.Inject

class PictureRepositoryImpl @Inject constructor(
    private val remoteService: ResponseService,
    private val localService: PictureDao,
) : PictureRepository {
    override fun fetchPictures(): Pager<Int, DomainModel> {
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

    override suspend fun getDetail(id: Int): DomainModel? {
        return localService.getPicture(id)?.let {
            entityToDomainModel(it)
        } ?: run {
            remoteService.getPictures(id)?.let { response ->
                responseToDomainModel(response).also {
                    localService.insert(
                        responseToEntity(response)
                    )
                }
            }
        }
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 20
    }
}