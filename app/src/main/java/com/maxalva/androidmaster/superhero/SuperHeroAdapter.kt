package com.maxalva.androidmaster.superhero

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.maxalva.androidmaster.R

class SuperHeroAdapter(
    private var superHeroes: List<SuperHero> = emptyList(),
    private val onItemSelected: (String) -> Unit
) :
    RecyclerView.Adapter<SuperHeroViewHolder>() {

    fun updateSuperHeroes(superHeroes: List<SuperHero>) {
        this.superHeroes = superHeroes
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_super_hero, parent, false)

        return SuperHeroViewHolder(view)
    }

    override fun getItemCount(): Int {
        return superHeroes.size
    }

    override fun onBindViewHolder(holder: SuperHeroViewHolder, position: Int) {
        holder.bind(superHeroes[position], onItemSelected)
    }
}