package project.seo.pictureviewer.data

interface DetailRepository {
    suspend fun getDetail(id: Int): Picture?
}