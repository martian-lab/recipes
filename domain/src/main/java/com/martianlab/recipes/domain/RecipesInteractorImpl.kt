package com.martianlab.recipes.domain

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.martianlab.recipes.entities.Category
import com.martianlab.recipes.entities.Recipe
import com.martianlab.recipes.entities.RecipeIngredient
import com.martianlab.recipes.entities.RecipeTag
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecipesInteractorImpl @Inject constructor(
    val recipesRepository: RecipesRepository
) : RecipesInteractor{

    override suspend fun loadToDb() {
        recipesRepository.loadRecipesToDb()
    }

    override suspend fun getCategories(): List<Category> {
        return recipesRepository.loadCategoriesFromDb()
    }

    override suspend fun loadToDbFlow(): Flow<String> {
        return recipesRepository.loadRecipesToDbFlow()
    }

    override suspend fun getRecipes(): List<Recipe> {
        return recipesRepository.loadRecipes()
    }

    override fun getRecipesPaged(category: Category): LiveData<PagedList<Recipe>> {
        val tags = listOf(RecipeTag(category.id, 0L, category.title))
        return recipesRepository.loadRecipesPaged(tags)
    }


    override suspend fun getRecipe(id: Long): Recipe? {
        return recipesRepository.getRecipe(id)
    }

    override suspend fun searchIngredients(contains: String): List<RecipeIngredient> = recipesRepository.searchIngredients(contains)
    override suspend fun searchRecipes(contains: String): List<Recipe> = recipesRepository.searchRecipes(contains)
    override suspend fun setFavorite(recipe: Recipe) = recipesRepository.setFavorite(recipe)
    override suspend fun removeFavorite(recipe: Recipe) = recipesRepository.removeFavorite(recipe)
    override suspend fun getFavorites(): List<Recipe> = recipesRepository.getFavorites()
}