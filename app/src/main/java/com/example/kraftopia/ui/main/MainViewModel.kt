package com.example.kraftopia.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kraftopia.data.model.AddressResponse
import com.example.kraftopia.data.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(val repository: MainRepository) :ViewModel(){

    private var _addressList=MutableLiveData<List<AddressResponse>>()
    val addressList:LiveData<List<AddressResponse>>
    get() = _addressList

    fun fetchAddress(){
        viewModelScope.launch {
          _addressList.postValue(repository.getUserAddress())
        }
        addressList.value?.joinToString("")?.let { Log.d("yolo", it) }
    }
}