package project.seo.pictureviewer.domain.usecase

import androidx.paging.Pager
import dagger.Reusable
import project.seo.pictureviewer.data.model.DataPicture
import project.seo.pictureviewer.data.repository.PictureRepository
import javax.inject.Inject

@Reusable
class GetImagesUseCase @Inject constructor(
    private val repository: PictureRepository
) {
    operator fun invoke(): Pager<Int, DataPicture> {
        return repository.fetchPictures()
    }
}