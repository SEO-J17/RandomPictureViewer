package io.github.seoj17.doamain.repository

import androidx.paging.Pager
import io.github.seoj17.doamain.model.DomainPicture
import kotlinx.coroutines.flow.Flow

interface PictureRepository {
    fun fetchPictures(): Pager<Int, DomainPicture>
    fun getDetail(id: Int): Flow<DomainPicture?>
}