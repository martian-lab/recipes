package com.martianlab.recipes.data

import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.martianlab.recipes.domain.BackendApi
import com.martianlab.recipes.domain.DbApi
import com.martianlab.recipes.domain.RecipesRepository
import com.martianlab.recipes.entities.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
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

    override suspend fun insertRecipes(recipes: List<Recipe>) {
        dbApi.insert(recipes)
    }

    override suspend fun loadRecipes(): List<Recipe> {
        val recipes = dbApi.getRecipes()
        if( recipes.isEmpty() ){
            loadRecipesFromFile().also {
                insertRecipes(it)
                return@loadRecipes it
            }
        }
        return recipes
    }

    private fun loadRecipesFromFile() : List<Recipe>{
        val json = RecipesRepositoryImpl::class.java.classLoader?.getResource("recipes.json")?.readText()
        val recipeListTypeToken = object : TypeToken<List<Recipe>>() {}.type
        val recipeList: List<Recipe> = Gson().fromJson(json, recipeListTypeToken)
        return recipeList
    }


    override suspend fun getRecipe(id: Long): Recipe {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getRecipesByIngredient(ingredients: List<RecipeIngredient>): List<Recipe> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private suspend fun loadCategoryRecipesToDb(category : Category ){
        val count = 20
        var offset = 0
        //println("RECIPES: category=" + category)
        do {
            val result = backendApi.recipeSearch(category.id, 0L, count, offset )
            if( result is Result.Success ){
                result.data?.let {list ->
                    val recipeWithTagList = list.map { it.copy(tags = setOf(RecipeTag(id=category.id, recipeId = it.id, title = category.title ))) }
//                    println("RECIPES:: firts cat recipe=" + recipeWithTagList[0].ingredients )
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


    override suspend fun loadRecipesToDbFlow() : Flow<String> {
        return flow {
            val categoryList = getCategoriesFromBackend()
            emit("getting category list")
            loadCategoriesToDb(categoryList)
            emit("loading categories into db")
            categoryList.forEach {
                loadCategoryRecipesToDb(it)
                emit("loading category ${it.title}" )
            }
        }


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

    override suspend fun loadCategoriesFromDb(): List<Category> {
        return dbApi.loadCategories()
    }

    suspend fun getRecipesTotal() : Long{
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}


