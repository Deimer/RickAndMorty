package com.testvass.rickandmorty.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.testvass.rickandmorty.databinding.FragmentSplashBinding
import com.testvass.rickandmorty.ui.utils.setOnSingleClickListener
import dagger.hilt.android.AndroidEntryPoint
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

@AndroidEntryPoint
class SplashFragment: Fragment() {

    private val binding: FragmentSplashBinding by lazy {
        FragmentSplashBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView() {
        initClickListener()
    }

    private fun initClickListener() {
        with(binding) {
            buttonStart.setOnSingleClickListener {
                val action = SplashFragmentDirections.actionSplashFragmentToHomeFragment()
                findNavController().navigate(action)
            }
        }
    }
}