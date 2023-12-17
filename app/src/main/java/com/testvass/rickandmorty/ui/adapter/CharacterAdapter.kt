package com.testvass.rickandmorty.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.testvass.repository.models.CharacterModel
import com.testvass.rickandmorty.R
import com.testvass.rickandmorty.databinding.ItemCharacterBinding
import com.testvass.rickandmorty.ui.utils.loadImage
import com.testvass.rickandmorty.ui.utils.setOnSingleClickListener

class CharacterAdapter(
    private val characters: List<CharacterModel>,
    private val clickCallback: (characterId: Int) -> Unit
): RecyclerView.Adapter<CharacterAdapter.ViewHolderCharacter>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderCharacter {
        val layout = LayoutInflater.from(parent.context)
        val itemBinding = ItemCharacterBinding.inflate(layout, parent, false)
        return ViewHolderCharacter(itemBinding, clickCallback)
    }

    override fun onBindViewHolder(holder: ViewHolderCharacter, position: Int) {
        holder.itemView.startAnimation(
            AnimationUtils.loadAnimation(
                holder.itemView.context,
                R.anim.fade_scale_animation
            )
        )
        holder.binding(characters[position])
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemCount(): Int {
        return characters.size
    }

    inner class ViewHolderCharacter(
        private val characterBinding: ItemCharacterBinding,
        private val clickCallback: (characterId: Int) -> Unit
    ) : RecyclerView.ViewHolder(characterBinding.root) {

        fun binding(character: CharacterModel) {
            setCharacterInformation(character)
            with(characterBinding) {
                root.setOnSingleClickListener {
                    clickCallback.invoke(character.id)
                }
            }
        }

        private fun setCharacterInformation(character: CharacterModel) {
            with(characterBinding) {
                imageViewPhoto.loadImage(character.image)
                textViewCharacterName.text = character.name
                textViewCharacterDate.text = character.created
                textViewOriginName.text = character.origin
            }
        }
    }
}