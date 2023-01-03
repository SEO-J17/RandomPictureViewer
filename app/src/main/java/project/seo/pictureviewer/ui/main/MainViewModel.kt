package project.seo.pictureviewer.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.liveData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.seoj17.doamain.model.DomainPicture
import io.github.seoj17.doamain.usecase.GetImagesUseCase
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    getImagesUseCase: GetImagesUseCase
) : ViewModel() {
    val pictureList: LiveData<PagingData<DomainPicture>> =
        getImagesUseCase().liveData.cachedIn(viewModelScope)
}