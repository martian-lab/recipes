package com.martianlab.recipes.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.martianlab.recipes.domain.RecipesRepository
import javax.inject.Inject

class RecipesViewModel @Inject constructor(private val repository: com.martianlab.recipes.domain.RecipesRepository) : ViewModel() {



}