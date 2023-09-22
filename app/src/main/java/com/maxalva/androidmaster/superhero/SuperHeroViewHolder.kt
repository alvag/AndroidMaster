package com.maxalva.androidmaster.superhero

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.maxalva.androidmaster.databinding.ItemSuperHeroBinding
import com.squareup.picasso.Picasso

class SuperHeroViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemSuperHeroBinding.bind(view)

    fun bind(superHero: SuperHero, onItemSelected: (String) -> Unit) {
        binding.tvSuperHeroName.text = superHero.name

        Picasso.get()
            .load(superHero.image.url)
            .into(binding.ivSuperHeroImage)

        binding.root.setOnClickListener { onItemSelected(superHero.id) }
    }
}