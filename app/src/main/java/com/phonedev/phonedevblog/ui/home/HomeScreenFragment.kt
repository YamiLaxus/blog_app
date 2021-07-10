package com.phonedev.phonedevblog.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.phonedev.phonedevblog.R
import com.phonedev.phonedevblog.data.remote.home.HomeScreenDataSource
import com.phonedev.phonedevblog.databinding.FragmentHomeScreenBinding
import com.phonedev.phonedevblog.domain.home.HomeScreenRepoImpl
import com.phonedev.phonedevblog.presentation.HomeScreenViewModel
import com.phonedev.phonedevblog.presentation.HomeScreenViewModelFactory
import com.phonedev.phonedevblog.ui.home.adapter.HomeScreenAdapter

class HomeScreenFragment : Fragment(R.layout.fragment_home_screen) {

    private lateinit var binding: FragmentHomeScreenBinding
    private val viewModel by viewModels<HomeScreenViewModel> { HomeScreenViewModelFactory(HomeScreenRepoImpl(
        HomeScreenDataSource()
    )) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeScreenBinding.bind(view)

        viewModel.fetchLatestPosts().observe(viewLifecycleOwner, Observer { result ->
            when(result){
                is com.phonedev.phonedevblog.core.Resource.Result.Loading ->{
                    binding.progressBar.visibility = View.VISIBLE
                }

                is com.phonedev.phonedevblog.core.Resource.Result.Success ->{
                    binding.progressBar.visibility = View.GONE
                    binding.rvHome.adapter = HomeScreenAdapter(result.data)
                }

                is com.phonedev.phonedevblog.core.Resource.Result.Failure<*> ->{
                    Toast.makeText(
                        requireContext(),
                        "Ocurrio un error: ${result.exception}",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }
        })

    }

}