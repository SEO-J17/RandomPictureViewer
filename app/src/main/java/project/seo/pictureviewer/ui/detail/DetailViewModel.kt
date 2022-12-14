package project.seo.pictureviewer.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import project.seo.pictureviewer.data.PictureData
import project.seo.pictureviewer.network.RetrofitService
import retrofit2.HttpException

class DetailViewModel : ViewModel() {
    private val _pictureDetail = MutableLiveData<PictureData>()
    val pictureDetail: LiveData<PictureData> = _pictureDetail

    private val _previousPreview = MutableLiveData<String?>()
    val previousPreview: LiveData<String?> = _previousPreview

    private val _nextPreview = MutableLiveData<String?>()
    val nextPreview: LiveData<String?> = _nextPreview

    private val _error = MutableLiveData<Event<String>>()
    val error: LiveData<Event<String>> = _error

    private val _webPage = MutableLiveData<Event<Boolean>>()
    val webPage: LiveData<Event<Boolean>> = _webPage

    fun fetchDetail() {
        viewModelScope.launch(exceptionHandler()) {
            _pictureDetail.postValue(RetrofitService.getPictureData(PICTURE_ID))
            setPreviousPreview(PICTURE_ID - 1)
            setNextPreview(PICTURE_ID + 1)
        }
    }

    private suspend fun setPreviousPreview(pictureId: Int) {
        try {
            _previousPreview.postValue(RetrofitService.getPictureData(pictureId).downloadUrl)
        } catch (e: HttpException) {
            _previousPreview.value = null
        }
    }

    private suspend fun setNextPreview(pictureId: Int) {
        try {
            _nextPreview.postValue(RetrofitService.getPictureData(pictureId).downloadUrl)
        } catch (e: HttpException) {
            _nextPreview.value = null
        }
    }

    fun updateId(pictureId: Int) {
        if (pictureId in 0 until 100) {
            PICTURE_ID = pictureId
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
        updateId(PICTURE_ID - 1)
    }

    fun nextPage() {
        updateId(PICTURE_ID + 1)
    }

    private fun exceptionHandler(): CoroutineExceptionHandler {
        return CoroutineExceptionHandler { _, throwable ->
            throwable.printStackTrace()
            when (throwable) {
                is HttpException -> _error.value = Event("네트워크 오류. 다시 시도해주세요")
                else -> _error.value = Event("이미지를 불러올 수 없습니다. \n다시 시도해 주세요.")
            }
        }
    }

    companion object {
        private var PICTURE_ID = 0
    }
}