package project.seo.pictureviewer.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import project.seo.pictureviewer.data.Picture
import project.seo.pictureviewer.data.PictureRepository
import project.seo.pictureviewer.navigator.AppNavigator
import project.seo.pictureviewer.utils.Event
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: PictureRepository,
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
        repository.getDetail(pictureId).onEach {
            _pictureDetail.value = it
            setPreviousPreview(pictureId - 1)
            setNextPreview(pictureId + 1)
        }.launchIn(viewModelScope)
    }

    private fun setPreviousPreview(pictureId: Int) {
        repository.getDetail(pictureId).onEach {
            _previousPreview.value = it?.downloadUrl
        }.launchIn(viewModelScope)
    }

    private fun setNextPreview(pictureId: Int) {
        repository.getDetail(pictureId).onEach {
            _nextPreview.value = it?.downloadUrl
        }.launchIn(viewModelScope)
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