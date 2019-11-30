package com.martianlab.recipes.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.martianlab.recipes.R
import com.martianlab.recipes.databinding.RecipeIngredientItemBinding
import com.martianlab.recipes.entities.RecipeIngredient

class RecipeIngredientAdapter(
) : RecyclerView.Adapter<RecipeIngredientAdapter.ViewHolder>(){

    var items = listOf<RecipeIngredient>()

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view : RecipeIngredientItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.recipe_ingredient_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ingredient = items[position]
        holder.bind(ingredient)
    }

    fun replaceData(list: List<RecipeIngredient>) {
        items = list
        println("RECIPES: ingr lsit=" + list )
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ViewDataBinding ) : RecyclerView.ViewHolder(binding.root){
        fun bind( ingredient: RecipeIngredient){
            (binding as RecipeIngredientItemBinding).ingredient = ingredient
            println("RECIPES: bind ingredient =" + ingredient )
            binding.executePendingBindings()
        }

    }
}

