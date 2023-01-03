package io.github.seoj17.doamain.usecase

import dagger.Reusable
import io.github.seoj17.doamain.model.DomainPicture
import io.github.seoj17.doamain.repository.PictureRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@Reusable
class GetDetailUseCase @Inject constructor(
    private val repository: PictureRepository
) {
    operator fun invoke(id: Int): Flow<DomainPicture?> {
        return repository.getDetail(id)
    }
}