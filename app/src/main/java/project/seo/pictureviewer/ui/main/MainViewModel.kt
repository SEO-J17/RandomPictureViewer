package project.seo.pictureviewer.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.liveData
import project.seo.pictureviewer.data.Picture
import project.seo.pictureviewer.data.PictureRepository

class MainViewModel : ViewModel() {
    val pictureList: LiveData<PagingData<Picture>> =
        PictureRepository()
            .fetchPictures()
            .liveData
            .cachedIn(viewModelScope)
}