package com.example.kraftopia.ui.login

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.kraftopia.R
import com.example.kraftopia.databinding.FragmentEnterPhoneNumberBinding
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import com.hbb20.CountryCodePicker
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@AndroidEntryPoint
class EnterPhoneNumberFragment : Fragment(),CountryCodePicker.OnCountryChangeListener {

    @Inject
    lateinit var mAuth:FirebaseAuth
    private lateinit var binding:FragmentEnterPhoneNumberBinding
    private var storedVerificationId:String? = ""
    private lateinit var resendToken:PhoneAuthProvider.ForceResendingToken
    private var countryCode:String="91"
    private lateinit var phoneNo:String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_enter_phone_number,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.sendCodeButton.setOnClickListener {
            phoneNo="+"+countryCode+binding.phoneNumber.text.toString()
            sendAuthCode(phoneNo)
           // val action=EnterPhoneNumberFragmentDirections.actionEnterPhoneNumberFragmentToEnterOtpFragment(phoneNo,"11" )
           // findNavController().navigate(action)
        }
        binding.countryCodePicker.setDefaultCountryUsingNameCode("IN")
        binding.countryCodePicker.setOnCountryChangeListener(this)
        binding.signinEmailText.setOnClickListener {
            findNavController().navigate(R.id.action_enterPhoneNumberFragment_to_enterEmailFragment)
        }
    }

    private fun sendAuthCode(phoneNumber:String) {
        binding.progressBar.visibility=View.VISIBLE
        binding.sendCodeButton.visibility=View.GONE
        binding.signinEmailText.visibility=View.GONE
        binding.errorText.visibility=View.GONE
        Log.d("yolo",phoneNumber)
        val options=PhoneAuthOptions.newBuilder(mAuth)
            .setPhoneNumber(phoneNumber)
            .setTimeout(60L,TimeUnit.SECONDS)
            .setActivity(activity as LoginActivity)
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
            binding.progressBar.visibility=View.GONE
            binding.errorText.visibility=View.VISIBLE
            binding.sendCodeButton.visibility=View.VISIBLE
            binding.signinEmailText.visibility=View.VISIBLE
        }

        override fun onCodeSent(verificationId: String, token: PhoneAuthProvider.ForceResendingToken) {
            Log.d("yolo", "onCodeSent:$verificationId")
            storedVerificationId= verificationId
            resendToken=token
            val action=EnterPhoneNumberFragmentDirections.actionEnterPhoneNumberFragmentToEnterOtpFragment(phoneNo,
                storedVerificationId!!
            )
            findNavController().navigate(action)
        }
    }

    override fun onCountrySelected() {
        countryCode=binding.countryCodePicker.selectedCountryCode
    }


}
