package com.example.kraftopia.ui.login

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.kraftopia.R
import com.example.kraftopia.databinding.FragmentEnterOtpBinding
import com.example.kraftopia.ui.main.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class EnterOtpFragment : Fragment() {
    @Inject
    lateinit var mAuth:FirebaseAuth
    private val viewModel:LoginViewModel by viewModels()
    private lateinit var binding: FragmentEnterOtpBinding
    val args:EnterOtpFragmentArgs by navArgs()
//    override fun onStart() {
//        viewModel.startTimer()
//        super.onStart()
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel.startTimer()
        super.onCreate(savedInstanceState)
    }


    override fun onPause() {
        super.onPause()
        (activity as LoginActivity).requestedOrientation=ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR
    }
    override fun onResume() {
        super.onResume()
        val currOrientation= (activity as LoginActivity).resources.configuration.orientation
        (activity as LoginActivity).requestedOrientation=currOrientation
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_enter_otp,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.otpDescriptionText.text="Enter the 6-digit code sent to ${args.phoneNo}"
        viewModel.timerText.observe(viewLifecycleOwner,{
            binding.timer.visibility=View.VISIBLE
            binding.timer.text=it
        })
        viewModel.showResendText.observe(viewLifecycleOwner,{
            if(it==true){
                binding.timer.visibility=View.GONE
                binding.resend.visibility=View.VISIBLE
            }
        })
        binding.verifyOtpButton.setOnClickListener {
            Log.d("yolo",binding.pinView.text.toString())
            viewModel.timer.cancel()
            findNavController().navigate(R.id.action_enterOtpFragment_to_registerFragment)
            //verifyCode(binding.pinView.text.toString())
        }
        binding.resend.setOnClickListener {

        }
    }

    private fun verifyCode(codeByUser: String) {
        val credential:PhoneAuthCredential=PhoneAuthProvider.getCredential(args.verificationId,codeByUser)
        signInWithPhoneAuthCredential(credential)
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
                        val action=EnterOtpFragmentDirections.actionEnterOtpFragmentToRegisterFragment(user!!)
                        findNavController().navigate(action)
                    }else {
                        val intent= Intent(activity,MainActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                        startActivity(intent)
                        (activity as LoginActivity).finish()

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