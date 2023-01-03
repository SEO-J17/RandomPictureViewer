package io.github.seoj17.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import io.github.seoj17.data.database.PictureDao
import io.github.seoj17.data.mapper.entityToDomainModel
import io.github.seoj17.data.mapper.responseToDomainModel
import io.github.seoj17.data.mapper.responseToEntity
import io.github.seoj17.data.network.ResponseService
import io.github.seoj17.doamain.model.DomainPicture
import io.github.seoj17.doamain.repository.PictureRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PictureRepositoryImpl @Inject constructor(
    private val remoteService: ResponseService,
    private val localService: PictureDao,
) : PictureRepository {
    override fun fetchPictures(): Pager<Int, DomainPicture> {
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

    override fun getDetail(id: Int): Flow<DomainPicture?> {
        return flow {
            localService.getItem(id)?.let {
                runCatching {
                    it.collect { entity ->
                        emit(entityToDomainModel(entity))
                    }
                }
            } ?: run {
                remoteService.getPictureData(id)?.let { response ->
                    emit(responseToDomainModel(response))
                    localService.insertItem(
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