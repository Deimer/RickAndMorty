package com.testvass.rickandmorty.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnPreDraw
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.testvass.repository.models.CharacterModel
import com.testvass.rickandmorty.databinding.FragmentHomeBinding
import com.testvass.rickandmorty.ui.adapter.CharacterAdapter
import com.testvass.rickandmorty.ui.utils.showSnackBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment: Fragment() {

    private val viewModel by activityViewModels<HomeViewModel>()
    private val binding: FragmentHomeBinding by lazy {
        FragmentHomeBinding.inflate(layoutInflater)
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
        initSubscriptionCharacters()
        initSubscriptionErrors()
    }

    private fun initSubscriptionCharacters() {
        viewModel.fetchCharacters()
        viewModel.charactersLiveData().observe(viewLifecycleOwner) { characters ->
            setupRecyclerCharacters(characters)
        }
    }

    private fun initSubscriptionErrors() {
        viewModel.errorLiveData().observe(viewLifecycleOwner) {
            binding.recyclerviewCharacters.showSnackBar(it)
        }
    }

    private fun setupRecyclerCharacters(characters: List<CharacterModel>) {
        with(binding) {
            recyclerviewCharacters.apply {
                layoutManager = LinearLayoutManager(context, VERTICAL, false)
                adapter = CharacterAdapter(characters, ::openCharacterDetails)
            }
            postponeEnterTransition()
            recyclerviewCharacters.doOnPreDraw {
                startPostponedEnterTransition()
            }
            recyclerviewCharacters.scrollState
        }
    }

    private fun openCharacterDetails(characterId: Int, viewOne: View) {
        val extras = FragmentNavigatorExtras(
            viewOne to characterId.toString()
        )
        val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(characterId)
        findNavController().navigate(action, extras)
    }
}