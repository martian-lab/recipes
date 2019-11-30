package com.martianlab.recipes.data

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.google.gson.reflect.TypeToken
import com.martianlab.recipes.data.db.DbApi
import com.martianlab.recipes.domain.BackendApi
import com.martianlab.recipes.domain.RecipesRepository
import com.martianlab.recipes.entities.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton
import com.google.gson.GsonBuilder

import com.google.gson.Gson





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

    override fun loadRecipesPaged(tags : List<RecipeTag>): LiveData<PagedList<Recipe>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(10)
            .build()
        return dbApi.getRecipesPages(tags).toLiveData(config)
    }

    override suspend fun loadRecipes(): List<Recipe> {
        val recipes = dbApi.getRecipes()
        //println("RECIPES: from db size=" + recipes.size )
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
        val gson: Gson = GsonBuilder().setDateFormat("MMM dd, yyyy HH:mm:ss").create()
        val recipeList: List<Recipe> = gson.fromJson(json, recipeListTypeToken)
        return recipeList
    }

    private fun loadCategoriesFromFile() : List<Category>{
        val json = RecipesRepositoryImpl::class.java.classLoader?.getResource("categories.json")?.readText()
        val categoryListTypeToken = object : TypeToken<List<Category>>() {}.type
        val categoryList: List<Category> = Gson().fromJson(json, categoryListTypeToken)
        return categoryList
    }

    override suspend fun getRecipe(id: Long): Recipe? {
        return dbApi.getRecipeById(id)
    }

    override suspend fun getRecipesByIngredient(ingredients: List<RecipeIngredient>): List<Recipe> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private suspend fun loadCategoryRecipesToDb(category : Category ){
        val count = 20
        var offset = 0
        println("RECIPES: category=" + category)
        do {
            val result = backendApi.recipeSearch(category.id, 0L, count, offset )
            if( result is Result.Success ){
                result.data?.let {list ->
                    val recipeWithTagList = list.map { it.copy(tags = listOf(RecipeTag(id=category.id, recipeId = it.id, title = category.title ))) }
                    println("RECIPES:: firts cat recipe=" + recipeWithTagList[0].tags )
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
//        loadCategoriesToDb(getCategoriesFromBackend())
        val categories = dbApi.loadCategories()
        //println("RECIPES: categories from db size=" + categories.size )
        if( categories.isEmpty() ){
            loadCategoriesFromFile().also {
                dbApi.insertCategories(it)
                return it
            }
        }
        return categories
        //return dbApi.loadCategories()
    }

    suspend fun getRecipesTotal() : Long{
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}


