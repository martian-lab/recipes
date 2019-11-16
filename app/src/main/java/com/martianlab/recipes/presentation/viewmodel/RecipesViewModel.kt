package com.martianlab.recipes.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.martianlab.recipes.domain.RecipesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class RecipesViewModel @Inject constructor(
    private val repository: RecipesRepository
) : ViewModel(), CoroutineScope by CoroutineScope(Dispatchers.IO) {


    fun loadRecipes(){
        launch {
            val recipes = repository.loadRecipes(50,0)
            println("RECIPES: list= " + recipes)
        }
    }
}