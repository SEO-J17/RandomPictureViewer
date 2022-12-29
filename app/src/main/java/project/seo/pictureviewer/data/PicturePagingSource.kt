package project.seo.pictureviewer.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import project.seo.pictureviewer.database.PictureDao
import project.seo.pictureviewer.database.PictureEntity
import project.seo.pictureviewer.network.PicturesAPI
import retrofit2.awaitResponse

class PicturePagingSource(
    private val localService: PictureDao,
    private val remoteService: PicturesAPI,
) : PagingSource<Int, Picture>() {
    override fun getRefreshKey(state: PagingState<Int, Picture>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Picture> {
        val page = params.key ?: STARTING_PAGE
        runCatching {
            remoteService.getPicture(page, params.loadSize).awaitResponse()
        }.onSuccess { response ->
            response.body()?.let { pictures ->
                localService.insert(
                    pictures.map { data ->
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
                return LoadResult.Page(
                    data = Picture(pictures),
                    prevKey = if (page == STARTING_PAGE) null else page - 1,
                    nextKey = if (pictures.isEmpty() || pictures.size < params.loadSize) null else page + 1
                )
            }
        }
        val offset = (page - 1) * params.loadSize
        val local = localService.getAll(offset, params.loadSize)
        return LoadResult.Page(
            data = Picture(local),
            prevKey = if (page == STARTING_PAGE) null else page - 1,
            nextKey = if (local.isEmpty() || local.size < params.loadSize) null else page + 1
        )
    }

    companion object {
        private const val STARTING_PAGE = 1
    }
}