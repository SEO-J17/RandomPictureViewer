package project.seo.pictureviewer.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.liveData
import dagger.hilt.android.lifecycle.HiltViewModel
import project.seo.pictureviewer.data.Picture
import project.seo.pictureviewer.data.PictureRepository
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    repository: PictureRepository,
) : ViewModel() {
    val pictureList: LiveData<PagingData<Picture>> =
        repository
            .fetchPictures()
            .liveData
            .cachedIn(viewModelScope)
}