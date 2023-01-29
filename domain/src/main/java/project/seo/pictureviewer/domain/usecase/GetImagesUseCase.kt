package project.seo.pictureviewer.domain.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.paging.PagingData
import androidx.paging.liveData
import dagger.Reusable
import project.seo.pictureviewer.data.repository.PictureRepository
import project.seo.pictureviewer.domain.model.DomainPicture
import javax.inject.Inject

@Reusable
class GetImagesUseCase @Inject constructor(
    private val repository: PictureRepository
) {
    operator fun invoke(): LiveData<PagingData<DomainPicture>> {
        return repository.fetchPictures().liveData.map {
            DomainPicture(it)
        }
    }
}