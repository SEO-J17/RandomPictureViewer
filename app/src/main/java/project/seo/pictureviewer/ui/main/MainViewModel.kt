package project.seo.pictureviewer.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import project.seo.pictureviewer.data.PictureInfo
import project.seo.pictureviewer.network.RetrofitService

class MainViewModel : ViewModel() {

    private val _isLoading = MutableLiveData(true)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _pictureList = MutableLiveData<PictureInfo>(emptyList())
    val pictureList: LiveData<PictureInfo> = _pictureList

    fun start() {
        _isLoading.value = true
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _pictureList.postValue(RetrofitService.getPicture())
            }
            _isLoading.value = false
        }
    }
}