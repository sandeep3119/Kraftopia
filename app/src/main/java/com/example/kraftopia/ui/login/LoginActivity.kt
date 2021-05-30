package com.example.kraftopia.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.kraftopia.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    override fun onStop() {
        super.onStop()
        Log.d("yolo","onStop")
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d("yolo","destroyed")
    }

    override fun finish() {
        super.finish()
        onDestroy()
    }
}