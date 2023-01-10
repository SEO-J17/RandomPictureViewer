package io.github.seoj17.domain.usecase

import dagger.Reusable
import io.github.seoj17.domain.model.DomainModel
import io.github.seoj17.domain.repository.PictureRepository
import javax.inject.Inject

@Reusable
class GetDetailUseCase @Inject constructor(
    private val repository: PictureRepository
) {
    suspend operator fun invoke(id: Int): DomainModel? {
        return repository.getDetail(id)
    }
}