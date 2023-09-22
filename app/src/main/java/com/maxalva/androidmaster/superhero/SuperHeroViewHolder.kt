package com.maxalva.androidmaster.superhero

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.maxalva.androidmaster.databinding.ItemSuperHeroBinding

class SuperHeroViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemSuperHeroBinding.bind(view)

    fun bind(superHero: SuperHero) {
        binding.tvSuperHeroName.text = superHero.name
    }
}