package com.phonedev.phonedevblog.ui.auth

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.phonedev.phonedevblog.R
import com.phonedev.phonedevblog.core.Result
import com.phonedev.phonedevblog.data.remote.auth.AuthDataSource
import com.phonedev.phonedevblog.databinding.FragmentRegisterBinding
import com.phonedev.phonedevblog.domain.auth.AuthRepoImpl
import com.phonedev.phonedevblog.presentation.auth.AuthViewModel
import com.phonedev.phonedevblog.presentation.auth.AuthViewModelFactory

class RegisterFragment : Fragment(R.layout.fragment_register) {

    private lateinit var binding: FragmentRegisterBinding
    private val viewModel by viewModels<AuthViewModel> { AuthViewModelFactory(
        AuthRepoImpl(
            AuthDataSource()
        )
    ) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentRegisterBinding.bind(view)
        signUp()
    }

    private fun signUp() {
        binding.btnSiginUp.setOnClickListener {

            val userName = binding.editTextUserName.text.toString().trim()
            val email = binding.editTextEmail.text.toString().trim()
            val password = binding.editTextPassword.text.toString().trim()
            val passwordSure = binding.editTextPasswordSure.text.toString().trim()

            if (validateUserData(password, passwordSure, email, userName)) return@setOnClickListener

           createUser(email, password, userName)
        }
    }

    private fun createUser(email: String, password: String, userName: String){
        viewModel.singUp(email,password,userName).observe(viewLifecycleOwner, Observer { resutl ->
            when(resutl){
                is Result.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.btnSiginUp.isEnabled = false
                }
                is Result.Success -> {
                    binding.progressBar.visibility = View.GONE
                    findNavController().navigate(R.id.action_registerFragment_to_setupProfileFragment)
                }
                is Result.Failure -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.btnSiginUp.isEnabled = true
                    Toast.makeText(requireContext(), "Error: ${resutl.exception}", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun validateUserData(
        password: String,
        passwordSure: String,
        email: String,
        userName: String
    ): Boolean {
        if (password != passwordSure) {
            binding.editTextPasswordSure.error = "Password not match"
            binding.editTextPassword.error = "Password not match"

            return true
        }

        if (email.isEmpty()) {
            binding.editTextEmail.error = "Email is empty"
            return true
        }

        if (userName.isEmpty()) {
            binding.editTextUserName.error = "Username is empty"
            return true
        }

        if (password.isEmpty()) {
            binding.editTextPassword.error = "Password is empty"
            return true
        }

        if (passwordSure.isEmpty()) {
            binding.editTextPasswordSure.error = "Password is empty"
            return true
        }
        return false
    }
}