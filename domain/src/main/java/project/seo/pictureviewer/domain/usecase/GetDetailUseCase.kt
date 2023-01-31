package project.seo.pictureviewer.domain.usecase

import dagger.Reusable
import project.seo.pictureviewer.data.repository.PictureRepository
import project.seo.pictureviewer.domain.model.DomainPicture
import javax.inject.Inject

@Reusable
class GetDetailUseCase @Inject constructor(
    private val repository: PictureRepository
) {
    suspend operator fun invoke(id: Int): DomainPicture? {
        return repository
            .getDetail(id)
            ?.let {
                DomainPicture(it)
            }
    }
}