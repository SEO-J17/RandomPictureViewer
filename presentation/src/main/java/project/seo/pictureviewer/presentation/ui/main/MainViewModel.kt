package project.seo.pictureviewer.presentation.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import project.seo.pictureviewer.domain.model.DomainPicture
import project.seo.pictureviewer.domain.usecase.GetImagesUseCase
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    getImagesUseCase: GetImagesUseCase
) : ViewModel() {
    val pictureList: LiveData<PagingData<DomainPicture>> =
        getImagesUseCase()
            .asLiveData()
            .cachedIn(viewModelScope)
}