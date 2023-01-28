package project.seo.pictureviewer.presentation.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import project.seo.pictureviewer.domain.usecase.GetDetailUseCase
import project.seo.pictureviewer.presentation.model.Picture
import project.seo.pictureviewer.presentation.utils.Event
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getDetailUseCase: GetDetailUseCase,
) : ViewModel() {

    private var pictureId = DetailFragmentArgs.fromSavedStateHandle(
        savedStateHandle
    ).pictureId

    private val _pictureDetail = MutableLiveData<Picture>()
    val pictureDetail: LiveData<Picture> = _pictureDetail

    private val _previousPreview = MutableLiveData<String?>()
    val previousPreview: LiveData<String?> = _previousPreview

    private val _nextPreview = MutableLiveData<String?>()
    val nextPreview: LiveData<String?> = _nextPreview

    private val _error = MutableLiveData<Event<String>>()
    val error: LiveData<Event<String>> = _error

    private val _webPage = MutableLiveData<Event<Boolean>>()
    val webPage: LiveData<Event<Boolean>> = _webPage

    fun fetchDetail() {
        viewModelScope.launch {
            getDetailUseCase(pictureId)?.let { data ->
                _pictureDetail.postValue(Picture(data))
                setPreviousPreview(pictureId - 1)
                setNextPreview(pictureId + 1)
            }
        }
    }

    private suspend fun setPreviousPreview(pictureId: Int) {
        _previousPreview.postValue(getDetailUseCase(pictureId)?.downloadUrl)
    }

    private suspend fun setNextPreview(pictureId: Int) {
        _nextPreview.postValue(getDetailUseCase(pictureId)?.downloadUrl)
    }

    private fun updateId(id: Int) {
        if (id >= 0) {
            pictureId = id
            isValid(true)
        } else {
            isValid(false)
        }
    }

    private fun isValid(flag: Boolean) {
        if (flag) {
            fetchDetail()
        } else {
            _error.value = Event("이미지가 더 이상 없습니다.")
        }
    }

    fun clickWebPage() {
        _webPage.value = Event(true)
    }

    fun previousPage() {
        updateId(pictureId - 1)
    }

    fun nextPage() {
        updateId(pictureId + 1)
    }
}