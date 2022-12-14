package project.seo.pictureviewer.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import project.seo.pictureviewer.data.PictureInfo
import project.seo.pictureviewer.network.RetrofitService
import project.seo.pictureviewer.ui.detail.Event
import retrofit2.HttpException

class MainViewModel : ViewModel() {

    private val _isLoading = MutableLiveData(true)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _pictureList = MutableLiveData<PictureInfo>()
    val pictureList: LiveData<PictureInfo> = _pictureList

    private val _error = MutableLiveData<Event<String>>()
    val error: LiveData<Event<String>> = _error

    fun start() {
        viewModelScope.launch(exceptionHandler()) {
            _isLoading.value = true
            _pictureList.postValue(RetrofitService.getPicture())
            _isLoading.value = false
        }
    }

    private fun exceptionHandler(): CoroutineExceptionHandler {
        return CoroutineExceptionHandler { _, throwable ->
            throwable.printStackTrace()
            when (throwable) {
                is HttpException -> _error.value = Event("서버 통신 오류. 다시 시도해주세요")
                else -> _error.value = Event("이미지를 불러올 수 없습니다. \n다시 시도해 주세요.")
            }
        }
    }
}