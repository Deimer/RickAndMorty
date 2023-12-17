package com.testvass.rickandmorty.ui.details

import android.os.Bundle
import android.transition.TransitionInflater
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL
import com.testvass.repository.models.CharacterModel
import com.testvass.rickandmorty.R
import com.testvass.rickandmorty.databinding.FragmentDetailsBinding
import com.testvass.rickandmorty.ui.adapter.EpisodeAdapter
import com.testvass.rickandmorty.ui.utils.loadImage
import com.testvass.rickandmorty.ui.utils.setOnSingleClickListener
import com.testvass.rickandmorty.ui.utils.showSnackBar
import com.testvass.rickandmorty.ui.utils.toHumanDate
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class DetailsFragment: Fragment() {

    private val viewModel by activityViewModels<DetailsViewModel>()
    private val args by navArgs<DetailsFragmentArgs>()
    private val binding: FragmentDetailsBinding by lazy {
        FragmentDetailsBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setupAnimation()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView() {
        initClickListeners()
        initSubscriptionCharacter()
        initSubscriptionErrors()
    }

    private fun setupAnimation() {
        val animation = TransitionInflater.from(requireContext())
            .inflateTransition(android.R.transition.move)
        sharedElementEnterTransition = animation
        sharedElementReturnTransition = animation
        postponeEnterTransition(DURATION_ANIMATION, TimeUnit.MILLISECONDS)
        binding.imageViewCharacterPhoto.transitionName = args.characterId.toString()
    }
    private fun initClickListeners() {
        binding.fabBack.setOnSingleClickListener {
            findNavController().popBackStack()
        }
    }

    private fun initSubscriptionCharacter() {
        viewModel.fetchCharacterById(args.characterId)
        viewModel.characterLiveData().observe(viewLifecycleOwner) { character ->
            setupInfoCharacter(character)
        }
    }

    private fun initSubscriptionErrors() {
        viewModel.errorLiveData().observe(viewLifecycleOwner) {
            binding.recyclerViewEpisodes.showSnackBar(it)
        }
    }

    private fun setupInfoCharacter(character: CharacterModel) {
        with(binding) {
            imageViewCharacterPhoto.loadImage(character.image)
            textViewCharacterName.text = character.name
            textViewCharacterDate.text = getString(R.string.date_created, character.created.toHumanDate())
            textViewOriginName.text = getString(R.string.origin, character.origin)
            textViewGender.text = getString(R.string.gender, character.gender)
            textViewSpecie.text = getString(R.string.specie, character.species)
            textViewStatus.text = getString(R.string.status, character.status)
            textViewTotalEpisodes.text = getString(R.string.total_episodes, character.episodes.count().toString())
            setupRecyclerEpisodes(character.episodesNumber)
        }
    }

    private fun setupRecyclerEpisodes(episodes: List<String>) {
        with(binding) {
            recyclerViewEpisodes.apply {
                layoutManager = LinearLayoutManager(context, HORIZONTAL, false)
                adapter = EpisodeAdapter(episodes)
            }
        }
    }

    companion object {
        const val DURATION_ANIMATION = 250L
    }
}