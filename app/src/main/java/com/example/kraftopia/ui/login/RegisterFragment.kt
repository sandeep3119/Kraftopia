package com.example.kraftopia.ui.login


import android.app.DatePickerDialog

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.kraftopia.R
import com.example.kraftopia.data.model.RegisterRequest
import com.example.kraftopia.databinding.FragmentRegisterBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class RegisterFragment : Fragment(), DatePickerDialog.OnDateSetListener {

    val args: RegisterFragmentArgs by navArgs()
    private lateinit var binding: FragmentRegisterBinding
    private val viewModel: LoginViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.dobEditText.setOnClickListener {
            val calendar: Calendar = Calendar.getInstance(TimeZone.getDefault())
            val dialog = DatePickerDialog(
                (activity as LoginActivity), this,
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
            dialog.show()
        }
        binding.nextButton.setOnClickListener {
            validateData()
            binding.nextButton.visibility=View.GONE
            binding.progressBar3.visibility=View.VISIBLE
        }

    }
    private fun validateData() {

        val registerRequest = RegisterRequest(
            binding.userEmail.text.toString(),
            binding.userPassword.text.toString(),
            binding.userName.text.toString(),
            args.phoneNumber,
            binding.dobEditText.text.toString(),
            args.firebaseId
        )
        viewModel.registerUser(registerRequest, binding.userPassword.text.toString())

        viewModel.token.observe(viewLifecycleOwner,{
            if(it!=null){
                findNavController().navigate(R.id.action_registerFragment_to_mainActivity)
            }
        })
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        Log.d("yolo", "$year-$month")
        binding.dobEditText.setText("$year-${month + 1}-$dayOfMonth")
    }

}