package project.seo.pictureviewer.domain.usecase

import androidx.paging.PagingData
import dagger.Reusable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import project.seo.pictureviewer.data.repository.PictureRepository
import project.seo.pictureviewer.domain.model.DomainPicture
import javax.inject.Inject

@Reusable
class GetImagesUseCase @Inject constructor(
    private val repository: PictureRepository
) {
    operator fun invoke(): Flow<PagingData<DomainPicture>> {
        return repository
            .fetchPictures()
            .flow
            .map {
                DomainPicture(it)
            }
    }
}