package com.example.kraftopia

import android.app.Application
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class KraftopiaApplication:Application() {

     val mAuth by lazy {
        Firebase.auth
    }
}