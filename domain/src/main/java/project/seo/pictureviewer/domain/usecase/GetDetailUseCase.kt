package project.seo.pictureviewer.domain.usecase

import dagger.Reusable
import project.seo.pictureviewer.data.model.DataPicture
import project.seo.pictureviewer.data.repository.PictureRepository
import javax.inject.Inject

@Reusable
class GetDetailUseCase @Inject constructor(
    private val repository: PictureRepository
) {
    suspend operator fun invoke(id: Int): DataPicture? {
        return repository.getDetail(id)
    }
}