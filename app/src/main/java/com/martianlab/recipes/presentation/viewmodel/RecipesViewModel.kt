package com.martianlab.recipes.presentation.viewmodel

import androidx.lifecycle.*
import androidx.paging.PagedList
import com.martianlab.recipes.domain.RecipesInteractor
import com.martianlab.recipes.entities.Category
import com.martianlab.recipes.entities.Recipe
import javax.inject.Inject
import kotlinx.coroutines.*

class RecipesViewModel @Inject constructor(
    private val interactor: RecipesInteractor
) : ViewModel(), CoroutineScope by CoroutineScope(Dispatchers.IO) {

    val categories = MutableLiveData<Resource<List<Category>>>()
    val recipes = MutableLiveData<Resource<List<Recipe>>>()

    val loadingState = MutableLiveData<Resource<String>>()

    val params = MutableLiveData<Category>()//.also { it.value = Category() }

    val recipesPaged = Transformations.switchMap(params){
        println("RECIPES: params=" + it )
        interactor.getRecipesPaged(it)
    }

    fun getRecipe(id: Long ) : LiveData<Recipe?>{
        return liveData{
            val recipe = interactor.getRecipe(id)
            emit(recipe)
        }
    }
    //val recipesPaged = interactor.getRecipesPaged()

    fun getRecipes(category: Category) = interactor.getRecipesPaged(category)

    fun loadRecipes(){
        val job = launch {
//            categories.postValue(Resource.Loading)
//            loadingState.postValue(Resource.Loading)
//            println("RECIPES: loading started")
//            interactor.loadToDbFlow().collect{
//                loadingState.postValue(Resource.Success(it))
//            }
//            loadingState.postValue(Resource.Success("loading finished"))

            val categoryList = interactor.getCategories()
//            println("RECIPES: categories: " + categoryList)
            categories.postValue(Resource.Success(categoryList))
//            loadingState.postValue(Resource.Success("${categoryList.map { it.total }.sum()} рецептов загружено"))
///            println("RECIPES:, loaded size=" + interactor.getRecipes().size)
//            println("RECIPES:, loaded 5=" + interactor.getRecipes().subList(3,5))
            recipes.postValue(Resource.Success(interactor.getRecipes()))
//            println("RECIPES: loading ended")
//            params.postValue(categoryList[0])
//            loadingState.postValue(Resource.Success("loading finished"))
        }
        //params.value?.let { params.value = it }
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