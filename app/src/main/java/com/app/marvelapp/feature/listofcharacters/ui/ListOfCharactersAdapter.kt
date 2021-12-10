package com.app.marvelapp.feature.listofcharacters.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.marvelapp.databinding.ItemMarvelRowBinding
import com.app.marvelapp.feature.listofcharacters.domain.MarvelCharacter

class MarvelAdapter(
    private val onClick: ((MarvelCharacter) -> Unit),
    private val onFavClick: ((MarvelCharacter) -> Unit)
) : ListAdapter<MarvelCharacter, MarvelAdapter.ViewHolder>(CharacterDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent, onClick, onFavClick)
    }

    class ViewHolder private constructor(
        private val binding: ItemMarvelRowBinding,
        private val onFavClick: ((MarvelCharacter) -> Unit),
        private val onClick: ((MarvelCharacter) -> Unit)
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MarvelCharacter) {
            binding.row.setOnClickListener {
                onClick(item)
            }
            binding.favButton.setOnClickListener {
                onFavClick(item)
            }
            binding.item = item

            binding.executePendingBindings()
        }

        companion object {
            fun from(
                parent: ViewGroup,
                onClick: (MarvelCharacter) -> Unit,
                onFavClick: (MarvelCharacter) -> Unit
            ): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemMarvelRowBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding, onFavClick, onClick)
            }
        }
    }
}


class CharacterDiffCallback : DiffUtil.ItemCallback<MarvelCharacter>() {

    override fun areItemsTheSame(oldItem: MarvelCharacter, newItem: MarvelCharacter): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MarvelCharacter, newItem: MarvelCharacter): Boolean {
        return oldItem == newItem
    }
}
