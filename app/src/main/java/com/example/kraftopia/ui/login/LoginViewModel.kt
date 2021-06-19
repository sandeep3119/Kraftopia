package com.example.kraftopia.ui.login

import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kraftopia.data.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val mainRepository: MainRepository):ViewModel() {
    private val _token=MutableLiveData<String>()
    val token:LiveData<String>
    get() = _token
    lateinit var timer:CountDownTimer
    private var _showResendText=MutableLiveData(false)
    val showResendText:LiveData<Boolean>
        get() = _showResendText
    private var _timerText=MutableLiveData<String>()
    val timerText:LiveData<String>
    get() = _timerText

    fun fetchToken(email:String,password:String) {
        _token.value = ""
        viewModelScope.launch {
            _token.postValue(mainRepository.getUserToken(email,password))
        }
    }
    fun startTimer(){
        _showResendText.postValue(false)
        timer=object :CountDownTimer(60000,1000){
            override fun onTick(millisUntilFinished: Long) {
                Log.d("yolo",millisUntilFinished.toString())
                _timerText.postValue(String.format("%02d:%02d",
                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                    TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished)-
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))
                )
                )
            }

            override fun onFinish() {
                _showResendText.postValue(true)
            }

        }.start()
    }

}