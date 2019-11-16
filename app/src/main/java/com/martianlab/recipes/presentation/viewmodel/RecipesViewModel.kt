package com.martianlab.recipes.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.work.ListenableWorker
import com.martianlab.recipes.domain.RecipesInteractor
import com.martianlab.recipes.domain.RecipesRepository
import com.martianlab.recipes.entities.Category
import kotlinx.coroutines.flow.collect
import okhttp3.Dispatcher
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext
import com.martianlab.recipes.entities.Result
import kotlinx.coroutines.*

class RecipesViewModel @Inject constructor(
    private val interactor: RecipesInteractor
) : ViewModel(), CoroutineScope by CoroutineScope(Dispatchers.IO) {

    val categories = MutableLiveData<Resource<List<Category>>>()

    val loadingState = MutableLiveData<Resource<String>>()

    fun loadRecipes(){
        launch {
            categories.postValue(Resource.Loading)
            loadingState.postValue(Resource.Loading)
            println("RECIPES: loading started")
            interactor.loadToDbFlow().collect{
                loadingState.postValue(Resource.Success(it))
            }
            loadingState.postValue(Resource.Success("loading finished"))
            println("RECIPES: loading ended")
            val categoryList = interactor.getCategories()
            println("RECIPES: categories: " + categoryList)
            categories.postValue(Resource.Success(categoryList))
            loadingState.postValue(Resource.Success("${categoryList.map { it.total }.sum()} рецептов загружено"))
        }
    }

    override fun onCleared() {
        cancel()
        super.onCleared()
    }
}

sealed class Resource<out T> {
    data class Success<T>(val data: T?) : Resource<T>()
    data class Failure(val statusCode: Int?) : Resource<Nothing>()
    object NetworkError : Resource<Nothing>()
    object Loading : Resource<Nothing>()
}