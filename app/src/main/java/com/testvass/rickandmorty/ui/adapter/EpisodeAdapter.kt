package com.testvass.rickandmorty.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.testvass.rickandmorty.databinding.ItemEpisodeBinding

class EpisodeAdapter(
    private val episodes: List<String>
): RecyclerView.Adapter<EpisodeAdapter.ViewHolderEpisode>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeAdapter.ViewHolderEpisode {
        val layout = LayoutInflater.from(parent.context)
        val itemBinding = ItemEpisodeBinding.inflate(layout, parent, false)
        return ViewHolderEpisode(itemBinding)
    }

    override fun onBindViewHolder(holder: EpisodeAdapter.ViewHolderEpisode, position: Int) {
        holder.binding(episodes[position])
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemCount(): Int {
        return episodes.size
    }

    inner class ViewHolderEpisode(
        private val episodeBinding: ItemEpisodeBinding
    ) : RecyclerView.ViewHolder(episodeBinding.root) {

        fun binding(episode: String) {
            with(episodeBinding) {
                textViewEpisodeNumber.text = episode
            }
        }
    }
}