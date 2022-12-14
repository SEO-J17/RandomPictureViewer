package project.seo.pictureviewer.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _pictureDetail.postValue(RetrofitService.getPictureData(PICTURE_ID))
            }
            setPreviousPreview(PICTURE_ID - 1)
            setNextPreview(PICTURE_ID + 1)
        }
    }

    private suspend fun setPreviousPreview(pictureId: Int) {
        try {
            withContext(Dispatchers.IO) {
                _previousPreview.postValue(RetrofitService.getPictureData(pictureId).downloadUrl)
            }
        } catch (e: HttpException) {
            _previousPreview.value = null
        }
    }

    private suspend fun setNextPreview(pictureId: Int) {
        try {
            withContext(Dispatchers.IO) {
                _nextPreview.postValue(RetrofitService.getPictureData(pictureId).downloadUrl)
            }
        } catch (e: HttpException) {
            _nextPreview.value = null
        }
    }

    fun updateId(pictureId: Int) {
        with(RetrofitService) {
            if (pictureId >= startPage - 1 && pictureId < endPage) {
                PICTURE_ID = pictureId
                isValid(true)
            } else {
                isValid(false)
            }
        }
    }

    private fun isValid(flag: Boolean) {
        if (flag) {
            fetchDetail()
        } else {
            _error.value = Event("이미지가 더 이상 없습니다.")
        }
    }

    fun showWebPage() {
        _webPage.value = Event(true)
    }

    fun getId(): Int = PICTURE_ID

    companion object {
        private var PICTURE_ID = 0
    }
}