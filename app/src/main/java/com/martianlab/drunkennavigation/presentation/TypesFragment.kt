package com.martianlab.drunkennavigation.presentation


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.martianlab.drunkennavigation.R
import com.martianlab.drunkennavigation.RecipesApp
import com.martianlab.drunkennavigation.presentation.viewmodel.RecipesViewModel
import javax.inject.Inject


class TypesFragment : Fragment() {

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

}
