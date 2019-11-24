package com.martianlab.recipes.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.martianlab.recipes.R
import com.martianlab.recipes.databinding.RecipeCategoryItemBinding
import com.martianlab.recipes.databinding.RecipeStageItemBinding
import com.martianlab.recipes.entities.Category
import com.martianlab.recipes.entities.RecipeStage
import com.martianlab.recipes.presentation.viewmodel.RecipesViewModel

class RecipeStageAdapter(
) : RecyclerView.Adapter<RecipeStageAdapter.ViewHolder>(){

    var items = listOf<RecipeStage>()

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view : RecipeStageItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.recipe_stage_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val stage = items[position]
        holder.bind(stage, position)
    }

    fun replaceData(list: List<RecipeStage>) {
        items = list.sortedBy { it.step }
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ViewDataBinding ) : RecyclerView.ViewHolder(binding.root){
        fun bind( stage: RecipeStage, position: Int){
            (binding as RecipeStageItemBinding).stage = stage.copy(step = position+1)
            binding.executePendingBindings()
        }

    }
}

