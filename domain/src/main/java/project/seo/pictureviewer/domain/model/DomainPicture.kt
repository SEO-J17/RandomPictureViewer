package project.seo.pictureviewer.domain.model

import androidx.paging.PagingData
import androidx.paging.map
import project.seo.pictureviewer.data.model.DataPicture

data class DomainPicture(
    val id: Int,
    val author: String,
    val width: String,
    val height: String,
    val url: String,
    val downloadUrl: String,
) {
    companion object {
        operator fun invoke(data: DataPicture): DomainPicture {
            return DomainPicture(
                id = data.id,
                author = data.author,
                width = data.width,
                height = data.height,
                url = data.url,
                downloadUrl = data.downloadUrl,
            )
        }

        operator fun invoke(paging: PagingData<DataPicture>): PagingData<DomainPicture> {
            return paging.map { data ->
                DomainPicture(
                    id = data.id,
                    author = data.author,
                    width = data.width,
                    height = data.height,
                    url = data.url,
                    downloadUrl = data.downloadUrl,
                )
            }
        }
    }
}