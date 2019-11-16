package com.martianlab.recipes.data

import android.content.SharedPreferences
import com.martianlab.recipes.domain.*
import com.martianlab.recipes.entities.*
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class RecipesRepositoryImpl @Inject constructor(
    private val dbApi: DbApi,
    private val backendApi: BackendApi,
    private val preferences: SharedPreferences
) : RecipesRepository {


    override suspend fun getRecipesFromBackend(count: Int, offset: Int): Result<List<Recipe>> {
        return backendApi.recipeSearch(0L, 0L, count, offset)
    }

    override suspend fun insertRecipes(recipes: List<Recipe>): List<Long> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun loadRecipes(): List<Recipe> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override suspend fun getRecipe(id: Long): Recipe {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getRecipesByIngredient(ingredients: List<RecipeIngredient>): List<Recipe> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private suspend fun loadCategoryRecipesToDb(category : Category ){
        val count = 50
        var offset = 0
        println("RECIPES: category=" + category)
        do {
            val result = backendApi.recipeSearch(category.id, 0L, count, offset )
            if( result is Result.Success ){
                result.data?.let {list ->
                    val recipeWithTagList = list.map { it.copy(tags = setOf(RecipeTag(id=category.id, recipeId = it.id, title = category.title ))) }
                    dbApi.insert(recipeWithTagList)
//                    println("RECIPES: list=" + recipeWithTagList )
//                    list.forEach {
//                        val tag = RecipeTag(id=category.id, recipeId = it.id, title = category.title )
//                        val recipe = it.copy(tags = setOf(tag))
//                        dbApi.insert(recipe)
//                    }
                }
            }
            offset += count
        }while( offset < category.total )
    }

    private suspend fun loadCategoriesToDb(categoryList: List<Category>) : List<Long>{
        return dbApi.insertCategories(categoryList)
    }

    override suspend fun loadRecipesToDb(){
        val categoryList = getCategoriesFromBackend()
        loadCategoriesToDb(categoryList).also { println("RECIPES: catIds=" + it) }
        categoryList.forEach { loadCategoryRecipesToDb(it) }
    }

    private suspend fun getCategoriesFromBackend() : List<Category>{
        val result = backendApi.getCategories()

        val categoryList = if( result is Result.Success ){
            result.data?.map {
                val res = backendApi.getCategory(it.id)
                if( res is Result.Success ) res.data!! else Category(-1L, "", "", 0, 0)
            }?.filter { it.id >= 0  }.orEmpty()
        }else listOf()

        return categoryList
    }

    suspend fun getRecipesTotal() : Long{
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}


