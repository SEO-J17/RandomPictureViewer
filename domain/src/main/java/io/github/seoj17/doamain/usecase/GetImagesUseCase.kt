package io.github.seoj17.doamain.usecase

import androidx.paging.Pager
import dagger.Reusable
import io.github.seoj17.doamain.model.DomainPicture
import io.github.seoj17.doamain.repository.PictureRepository
import javax.inject.Inject

@Reusable
class GetImagesUseCase @Inject constructor(
    private val repository: PictureRepository
) {
    operator fun invoke(): Pager<Int, DomainPicture> {
        return repository.fetchPictures()
    }
}