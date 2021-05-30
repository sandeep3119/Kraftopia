package com.example.kraftopia.ui.login

import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor():ViewModel() {
    lateinit var timer:CountDownTimer
    private var _showResendText=MutableLiveData(false)
    val showResendText:LiveData<Boolean>
        get() = _showResendText
    private var _timerText=MutableLiveData<String>()
    val timerText:LiveData<String>
    get() = _timerText

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