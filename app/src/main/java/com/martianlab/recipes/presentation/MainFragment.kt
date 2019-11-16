package com.martianlab.recipes.presentation


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.martianlab.recipes.R
import com.martianlab.recipes.RecipesApp
import com.martianlab.recipes.presentation.viewmodel.RecipesViewModel
import javax.inject.Inject


class MainFragment : Fragment() {

    @Inject
    lateinit var viewModel: RecipesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_types, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        (activity?.application as RecipesApp).component.inject(this)
        super.onActivityCreated(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        viewModel.loadRecipes()
    }
}
