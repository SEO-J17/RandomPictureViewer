package io.github.seoj17.data.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import io.github.seoj17.data.data.database.PictureDao
import io.github.seoj17.data.data.mapper.listEntityToDomainModel
import io.github.seoj17.data.data.mapper.responseToEntity
import io.github.seoj17.data.data.network.ResponseService
import io.github.seoj17.domain.model.DomainModel

class PicturePagingSource(
    private val localService: PictureDao,
    private val remoteService: ResponseService,
) : PagingSource<Int, DomainModel>() {
    override fun getRefreshKey(state: PagingState<Int, DomainModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DomainModel> {
        val page = params.key ?: STARTING_PAGE
        val pictures = remoteService.getPictures(page, params.loadSize)
        localService.insert(
            pictures.map { response ->
                responseToEntity(response)
            }
        )
        val offset = (page - 1) * params.loadSize
        val local = localService.getPicture(offset, params.loadSize)
        return LoadResult.Page(
            data = listEntityToDomainModel(local),
            prevKey = if (page == STARTING_PAGE) null else page - 1,
            nextKey = if (local.isEmpty() || local.size < params.loadSize) null else page + 1
        )
    }

    companion object {
        private const val STARTING_PAGE = 1
    }
}