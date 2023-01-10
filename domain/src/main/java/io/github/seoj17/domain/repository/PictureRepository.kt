package io.github.seoj17.domain.repository

import androidx.paging.Pager
import io.github.seoj17.domain.model.DomainModel

interface PictureRepository {
    fun fetchPictures(): Pager<Int, DomainModel>
    suspend fun getDetail(id: Int): DomainModel?
}