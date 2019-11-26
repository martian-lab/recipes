package com.martianlab.recipes.presentation


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs

import com.martianlab.recipes.R
import com.martianlab.recipes.RecipesApp
import com.martianlab.recipes.databinding.FragmentRecipeDetailsBinding
import com.martianlab.recipes.databinding.FragmentRecipesBinding
import com.martianlab.recipes.presentation.adapters.CategoryListAdapter
import com.martianlab.recipes.presentation.adapters.RecipeIngredientAdapter
import com.martianlab.recipes.presentation.adapters.RecipeStageAdapter
import com.martianlab.recipes.presentation.viewmodel.RecipesViewModel
import com.martianlab.recipes.presentation.viewmodel.Resource
import kotlinx.android.synthetic.main.fragment_recipe_details.*
import kotlinx.android.synthetic.main.fragment_recipes.*
import javax.inject.Inject


class RecipeDetailsFragment : Fragment() {

    @Inject
    lateinit var viewModel: RecipesViewModel

    lateinit var binding : FragmentRecipeDetailsBinding

    val args: RecipeDetailsFragmentArgs by navArgs()

    lateinit var ingredientAdapter : RecipeIngredientAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_recipe_details, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        (activity?.application as RecipesApp).component.inject(this)
        super.onActivityCreated(savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner

        ingredientAdapter = RecipeIngredientAdapter()
        ingredientsListView.adapter = ingredientAdapter


        val stageAdapter = RecipeStageAdapter()
        stagesListView.adapter = stageAdapter

        viewModel.getRecipe(args.recipeId).observe(viewLifecycleOwner, Observer {
            it?.let {
                binding.recipe = it
                toolbar.title = it.title
                ingredientAdapter.replaceData(it.ingredients)
                stageAdapter.replaceData(it.stages)
            }
        })

        buttonIngrediends.setOnClickListener { showIngredients() }
        buttonSteps.setOnClickListener { showSteps() }

        ingredientsListView.visibility = View.VISIBLE
        stagesListView.visibility = View.GONE
        buttonIngrediends.isSelected = true

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar.setNavigationOnClickListener { activity?.onBackPressed() }
    }

    private fun showIngredients() {
        ingredientsListView.visibility = View.VISIBLE
        stagesListView.visibility = View.GONE
        appbar.setExpanded(false, true)
        buttonIngrediends.isSelected = true
        buttonSteps.isSelected = false
    }

    private fun showSteps() {
        ingredientsListView.visibility = View.GONE
        stagesListView.visibility = View.VISIBLE
        appbar.setExpanded(false, true)
        buttonIngrediends.isSelected = false
        buttonSteps.isSelected = true
    }

}


enum class RecipeComplexity(val title: String, val num : Int ){
    LOW("низкая",1 ), MEDIUM("средняя",2 ), HIGH("высокая", 3);

    companion object{
        @JvmStatic
        fun getByNum(num : Int ) = values().find { it.num == num  }?.title ?: "хз"
    }
}