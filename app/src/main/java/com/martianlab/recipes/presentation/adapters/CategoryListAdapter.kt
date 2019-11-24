package com.martianlab.recipes.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.martianlab.recipes.R
import com.martianlab.recipes.databinding.ListItemRecipeBinding
import com.martianlab.recipes.databinding.RecipeCategoryItemBinding
import com.martianlab.recipes.entities.Category
import com.martianlab.recipes.entities.Recipe
import com.martianlab.recipes.presentation.viewmodel.RecipesViewModel

class CategoryListAdapter(
    private val getRecipes : (Category) -> LiveData<PagedList<Recipe>>,
    private val recipeClickAction : (Long)  -> Unit,
    private val lifecycleOwner: LifecycleOwner
) : RecyclerView.Adapter<CategoryListAdapter.ViewHolder>(){

    lateinit var context : Context
    var items = listOf<Category>()

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view : RecipeCategoryItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.recipe_category_item, parent, false)
        context = parent.context
        return ViewHolder(view, getRecipes, lifecycleOwner, recipeClickAction)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = items[position]
        holder.bind(category)
    }

    fun replaceData(list: List<Category>) {
        items = list
        notifyDataSetChanged()
    }

    class ViewHolder(
        private val binding: ViewDataBinding,
        private val getRecipes : (Category) -> LiveData<PagedList<Recipe>>,
        private val lifecycleOwner: LifecycleOwner,
        private val recipeClickAction : (Long)  -> Unit
    ) : RecyclerView.ViewHolder(binding.root){
        fun bind( category: Category ){
            println("RECIPES: bind category =" + category )
            (binding as RecipeCategoryItemBinding).category = category
            val adapter = RecipesListPageAdapter(recipeClickAction)
            getRecipes(category).observe(lifecycleOwner, Observer {
                adapter.submitList(it)
            })
            binding.receiptList.adapter = adapter
            binding.executePendingBindings()
        }

    }
}

