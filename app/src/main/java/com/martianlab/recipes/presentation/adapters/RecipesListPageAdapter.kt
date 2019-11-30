package com.martianlab.recipes.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.martianlab.recipes.R
import com.martianlab.recipes.databinding.RecipeItemBinding
import com.martianlab.recipes.entities.Recipe

class RecipesListPageAdapter(
     private val recipeClickAction : (Long)  -> Unit
) : PagedListAdapter<Recipe, RecipeViewHolder>(DIFF_CALLBACK) {

    lateinit var context : Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {

        val view : RecipeItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.recipe_item, parent, false)
        context = parent.context
        return RecipeViewHolder(view, recipeClickAction)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = getItem(position)
        recipe?.let { holder.bind(recipe, context)  }
    }

    companion object {
        private val DIFF_CALLBACK = object :
            DiffUtil.ItemCallback<Recipe>() {
                override fun areItemsTheSame(old: Recipe, new: Recipe) = old.id == new.id
                override fun areContentsTheSame(old: Recipe, new: Recipe) = old.title == new.title
            }
    }
}

class RecipeViewHolder(private val binding: ViewDataBinding, private val recipeClickAction : (Long)  -> Unit) : RecyclerView.ViewHolder(binding.root){
    fun bind( recipe: Recipe, context: Context ){
        (binding as RecipeItemBinding).recipe = recipe
        binding.clickListener = View.OnClickListener { recipeClickAction(recipe.id) }
        Glide
            .with(context)
            .load(recipe.imageURL)
            .into(binding.imageView2)

        binding.executePendingBindings()
    }

}