package project.seo.pictureviewer.data.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import project.seo.pictureviewer.data.database.PictureDao
import project.seo.pictureviewer.data.database.PictureEntity
import project.seo.pictureviewer.data.model.DataPicture
import project.seo.pictureviewer.data.network.ResponseService

class PicturePagingSource(
    private val localService: PictureDao,
    private val remoteService: ResponseService,
) : PagingSource<Int, DataPicture>() {
    override fun getRefreshKey(state: PagingState<Int, DataPicture>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DataPicture> {
        val page = params.key ?: STARTING_PAGE
        val pictures = remoteService.getPictures(page, params.loadSize)
        localService.insert(PictureEntity(pictures))

        val offset = (page - 1) * params.loadSize
        val local = localService.getPicture(offset, params.loadSize)
        return LoadResult.Page(
            data = DataPicture(local),
            prevKey = if (page == STARTING_PAGE) null else page - 1,
            nextKey = if (local.isEmpty() || local.size < params.loadSize) null else page + 1
        )
    }

    companion object {
        private const val STARTING_PAGE = 1
    }
}