package project.seo.pictureviewer.data

import androidx.paging.Pager

interface MainRepository {
    fun fetchPictures(): Pager<Int, Picture>
}