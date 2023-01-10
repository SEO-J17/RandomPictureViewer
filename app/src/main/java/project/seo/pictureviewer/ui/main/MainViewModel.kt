package project.seo.pictureviewer.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.liveData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.seoj17.domain.model.DomainModel
import io.github.seoj17.domain.usecase.GetImagesUseCase
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getImagesUseCase: GetImagesUseCase
) : ViewModel() {
    val pictureList: LiveData<PagingData<DomainModel>> =
        getImagesUseCase().liveData.cachedIn(viewModelScope)
}