package com.example.kraftopia.auth

import android.util.Log
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.kraftopia.R
import com.example.kraftopia.ui.login.EnterPhoneNumberFragmentDirections
import com.example.kraftopia.ui.login.LoginActivity
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.qualifiers.ActivityContext
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class FirebaseAuthorization(val activity: LoginActivity) {
    private var  mAuth:FirebaseAuth = Firebase.auth
    private fun sendAuthCode(phoneNumber:String) {
        Log.d("yolo",phoneNumber)
        val options= PhoneAuthOptions.newBuilder(mAuth)
            .setPhoneNumber(phoneNumber)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(activity)
            .setCallbacks(callbacks)
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }
    private val callbacks=object :PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            Log.d("yolo","onVerificationComplete: $credential")
        }

        override fun onVerificationFailed(p0: FirebaseException) {
            Log.d("yolo","onVerificationFailed: $p0")

        }

        override fun onCodeSent(verificationId: String, token: PhoneAuthProvider.ForceResendingToken) {
            Log.d("yolo", "onCodeSent:$verificationId")
        }
    }
    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential){
        mAuth.signInWithCredential(credential)
            .addOnCompleteListener(activity as LoginActivity){ task->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("yolo", "signInWithCredential:success")
                    val user = task.result?.user
                    val creationTime=user?.metadata?.creationTimestamp
                    val lastSignInTime=user?.metadata?.lastSignInTimestamp
                    //New user
                    if(creationTime==lastSignInTime){
                    }else {
                        Log.d("yolo","User Sign in")
                    }


                } else {
                    // Sign in failed, display a message and update the UI
                    Log.w("yolo", "signInWithCredential:failure", task.exception)
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                    }
                    // Update UI
                }
            }
    }
}