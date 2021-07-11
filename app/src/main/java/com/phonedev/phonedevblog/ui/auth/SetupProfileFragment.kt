package com.phonedev.phonedevblog.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.phonedev.phonedevblog.R
import com.phonedev.phonedevblog.data.remote.auth.AuthDataSource
import com.phonedev.phonedevblog.databinding.FragmentSetupProfileBinding
import com.phonedev.phonedevblog.domain.auth.AuthRepoImpl
import com.phonedev.phonedevblog.presentation.auth.AuthViewModel
import com.phonedev.phonedevblog.presentation.auth.AuthViewModelFactory

class SetupProfileFragment : Fragment(R.layout.fragment_setup_profile) {

    private lateinit var binding: FragmentSetupProfileBinding
    private val viewModel by viewModels<AuthViewModel> {
        AuthViewModelFactory(
            AuthRepoImpl(
                AuthDataSource()
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSetupProfileBinding.bind(view)

    }

}