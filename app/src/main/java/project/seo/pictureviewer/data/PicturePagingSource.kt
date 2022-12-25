package project.seo.pictureviewer.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import project.seo.pictureviewer.database.LocalService
import project.seo.pictureviewer.database.PictureEntity
import project.seo.pictureviewer.network.RemoteService

class PicturePagingSource : PagingSource<Int, Picture>() {
    override fun getRefreshKey(state: PagingState<Int, Picture>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Picture> {
        val page = params.key ?: STARTING_PAGE
        return try {
            val pictures = RemoteService.getPicture(page, params.loadSize)
            pictures.fold(
                onSuccess = { response ->
                    LocalService.getDao().insert(
                        response.map { data ->
                            PictureEntity(
                                id = data.id,
                                author = data.author,
                                width = data.width,
                                height = data.height,
                                url = data.url,
                                downloadUrl = data.downloadUrl
                            )
                        }
                    )
                    return@fold LoadResult.Page(
                        data = Picture(response),
                        prevKey = if (page == STARTING_PAGE) null else page - 1,
                        nextKey = if (response.isEmpty() || response.size < params.loadSize) null else page + 1
                    )
                }, onFailure = {
                    LoadResult.Error(it)
                }
            )
        } catch (e: Exception) {
            val offset = (page - 1) * params.loadSize
            val local = LocalService.getDao().getAll(offset, params.loadSize)
            return LoadResult.Page(
                data = Picture(local),
                prevKey = if (page == STARTING_PAGE) null else page - 1,
                nextKey = if (local.isEmpty() || local.size < params.loadSize) null else page + 1
            )
        }
    }

    companion object {
        private const val STARTING_PAGE = 1
    }
}