package com.phonedev.phonedevblog.ui.auth

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.phonedev.phonedevblog.R
import com.phonedev.phonedevblog.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment(R.layout.fragment_register) {

    private lateinit var binding: FragmentRegisterBinding

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

            Log.d("singUp", "data: $userName, $email, $password, $passwordSure")
        }
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