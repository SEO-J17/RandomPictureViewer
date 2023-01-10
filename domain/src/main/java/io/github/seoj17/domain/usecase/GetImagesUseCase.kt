package io.github.seoj17.domain.usecase

import androidx.paging.Pager
import dagger.Reusable
import io.github.seoj17.domain.model.DomainModel
import io.github.seoj17.domain.repository.PictureRepository
import javax.inject.Inject

@Reusable
class GetImagesUseCase @Inject constructor(
    private val repository: PictureRepository
) {
    operator fun invoke(): Pager<Int, DomainModel> {
        return repository.fetchPictures()
    }
}