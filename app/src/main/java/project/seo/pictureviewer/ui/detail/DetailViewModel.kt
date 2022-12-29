package project.seo.pictureviewer.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import project.seo.pictureviewer.data.DetailRepository
import project.seo.pictureviewer.data.Picture
import project.seo.pictureviewer.navigator.AppNavigator
import project.seo.pictureviewer.utils.Event
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: DetailRepository,
) : ViewModel() {

    private var pictureId = savedStateHandle.get<Int>(AppNavigator.PICTURE_KEY) ?: 0

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
            repository.getDetail(pictureId)?.let {
                _pictureDetail.postValue(it)
                setPreviousPreview(pictureId - 1)
                setNextPreview(pictureId + 1)
            } ?: run {
                _error.value = Event("존재 하지 않는 이미지 입니다.")
            }
        }
    }

    private suspend fun setPreviousPreview(pictureId: Int) {
        _previousPreview.postValue(repository.getDetail(pictureId)?.downloadUrl)
    }

    private suspend fun setNextPreview(pictureId: Int) {
        _nextPreview.postValue(repository.getDetail(pictureId)?.downloadUrl)
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