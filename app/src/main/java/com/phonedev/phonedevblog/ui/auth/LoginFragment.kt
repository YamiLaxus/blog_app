package com.phonedev.phonedevblog.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.phonedev.phonedevblog.R
import com.phonedev.phonedevblog.data.remote.auth.LoginDataSource
import com.phonedev.phonedevblog.databinding.FragmentLoginBinding
import com.phonedev.phonedevblog.domain.auth.LoginRepoImpl
import com.phonedev.phonedevblog.presentation.auth.LoginScreenViewModel
import com.phonedev.phonedevblog.presentation.auth.LoginScreenViewModelFactory
import com.phonedev.phonedevblog.core.Result

class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var binding: FragmentLoginBinding
    private val firebaseAuth by lazy {FirebaseAuth.getInstance()}
    private val viewModel by viewModels<LoginScreenViewModel> { LoginScreenViewModelFactory(LoginRepoImpl(LoginDataSource())) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)
        isUserLoggedIn()
        doLogin()
        goToSignUpPage()
    }

    private fun isUserLoggedIn(){
        firebaseAuth.currentUser?.let {
            findNavController().navigate(R.id.action_loginFragment_to_homeScreenFragment)
        }
    }

    private fun doLogin(){
        binding.btnSignin.setOnClickListener {
            val email = binding.editTextEmail.text.toString().trim()
            val password = binding.editTextPassword.text.toString().trim()
            validateCredential(email, password)
            signIn(email, password)
        }
    }


    private fun goToSignUpPage(){
        binding.txtSingnup.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

    private fun validateCredential(email: String, password: String){
        if (email.isEmpty()){
            binding.editTextEmail.error = "E-mail is empty"
            return
        }

        if (password.isEmpty()){
            binding.editTextPassword.error = "Password is empty"
            return
        }
    }

    private fun signIn(email: String, password: String){

        viewModel.signIn(email, password).observe(viewLifecycleOwner, { result ->
            when(result){
                is com.phonedev.phonedevblog.core.Result.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.btnSignin.isEnabled = false
                }
                is com.phonedev.phonedevblog.core.Result.Success -> {
                    binding.progressBar.visibility = View.GONE
                    findNavController().navigate(R.id.action_loginFragment_to_homeScreenFragment)
                }

            }
        })

    }

}