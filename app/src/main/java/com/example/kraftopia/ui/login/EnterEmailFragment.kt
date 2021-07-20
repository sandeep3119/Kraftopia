package com.example.kraftopia.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.kraftopia.R
import com.example.kraftopia.databinding.FragmentEnterEmailBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class EnterEmailFragment : Fragment() {

    private val viewModel:LoginViewModel by viewModels()
    lateinit var binding: FragmentEnterEmailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_enter_email, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.login.setOnClickListener {
            val email=binding.email.text.toString()
            val password=binding.password.text.toString()
            viewModel.fetchToken(email,password)
        }
        viewModel.token.observe(viewLifecycleOwner,{
            if(it.isNotBlank())
                findNavController().navigate(R.id.action_enterEmailFragment_to_mainActivity)
        })
    }

}