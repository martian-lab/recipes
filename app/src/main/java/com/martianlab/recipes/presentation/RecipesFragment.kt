package com.martianlab.recipes.presentation


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.work.ListenableWorker
import com.martianlab.recipes.R
import com.martianlab.recipes.RecipesApp
import com.martianlab.recipes.databinding.FragmentRecipesBinding
import com.martianlab.recipes.entities.Recipe
import com.martianlab.recipes.presentation.adapters.CategoryListAdapter
import com.martianlab.recipes.presentation.viewmodel.RecipesViewModel
import com.martianlab.recipes.presentation.viewmodel.Resource
import kotlinx.android.synthetic.main.fragment_recipes.*
import javax.inject.Inject


class RecipesFragment : Fragment() {

    @Inject
    lateinit var viewModel: RecipesViewModel

    lateinit var binding : FragmentRecipesBinding

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        (activity?.application as RecipesApp).component.inject(this)
        super.onActivityCreated(savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner
        //binding.recyclerView.layoutManager = LinearLayoutManager(activity?.applicationContext)

        val recipeClickAction : (Long)  -> Unit =  { id ->
            RecipesFragmentDirections.actionRecipesFragmentToRecipeDetailsFragment(id)
                .also { findNavController().navigate(it) }
        }
        val adapter = CategoryListAdapter({viewModel.getRecipes(it)}, recipeClickAction , viewLifecycleOwner)


        recyclerView.adapter = adapter
        viewModel.categories.observe(viewLifecycleOwner, Observer {
            //println("RECIPES: paged list loadedCount=" + it.loadedCount + " offset=" + it.positionOffset )
            //progressBar.visibility = View.GONE
            if( it is Resource.Success)
                adapter.replaceData(it.data!!)
        })

        viewModel.loadRecipes()

        viewModel.recipes.observe(viewLifecycleOwner, Observer {
            if( it is Resource.Success) {
                recyclerView.visibility = View.VISIBLE
                progressBar.visibility = View.GONE
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_recipes, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}
