package com.martianlab.recipes.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.work.ListenableWorker
import com.martianlab.recipes.domain.RecipesInteractor
import com.martianlab.recipes.domain.RecipesRepository
import com.martianlab.recipes.entities.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class RecipesViewModel @Inject constructor(
    private val interactor: RecipesInteractor
) : ViewModel(), CoroutineScope by CoroutineScope(Dispatchers.IO) {


    fun loadRecipes(){
        launch {
//            val recipes = repository.getRecipesFromBackend(100,0)
//            //println("RECIPES: list= " + if(recipes is Result.Success){recipes.data}else{ listOf()})
//            println("RECIPES: list length= " + if(recipes is Result.Success){recipes.data?.size}else{0} )
            println("RECIPES: loading started")
            //interactor.loadToDb()
            println("RECIPES: loading ended")
            println("RECIPES: categories: " + interactor.getCategories())
        }
        println("RECIPES: hello!")
    }
}