package com.martianlab.recipes.tools.db

import android.database.sqlite.SQLiteConstraintException
import androidx.paging.DataSource
import com.martianlab.recipes.data.db.DbApi
import com.martianlab.recipes.entities.Category
import com.martianlab.recipes.entities.Recipe
import com.martianlab.recipes.entities.RecipeIngredient
import com.martianlab.recipes.entities.RecipeTag
import com.martianlab.recipes.tools.db.dao.CategoryDao
import com.martianlab.recipes.tools.db.dao.RecipeDao
import com.martianlab.recipes.tools.db.mapper.*

class DbImpl(
    private val recipeDao: RecipeDao,
    private val categoryDao: CategoryDao
) : DbApi {


    override suspend fun getRecipes(tag: RecipeTag): List<Recipe> {
        return recipeDao.getRecipesByTagTitle(tag.title).map { it.toRecipe() }
    }

    override suspend fun getRecipes(): List<Recipe> {
        return recipeDao.getRecipes().map { it.toRecipe() }
    }

    override fun getRecipesPages(tags : List<RecipeTag>): DataSource.Factory<Int, Recipe> {
        return recipeDao.getRecipesPaged(tags[0].id).map{ it.toRecipe()}
    }

    override suspend fun getRecipeById(id: Long): Recipe {
        return recipeDao.getById(id).toRecipe()
    }

    override suspend fun insert(recipe: Recipe) : Long {
        try {
            return  recipeDao.insert(recipe = recipe.toEntityWithDependencies() )

        }catch ( e : SQLiteConstraintException ){
            println("RECIPES: exception = " + e )
            println("RECIPES: recipe = " + recipe )
        }

        return 0L
//        recipeDao.insertIngredients( recipe.ingredients.map { it.toEntity() } )
//        recipeDao.insertStages( recipe.stages.map { it.toEntity() } )
//        recipeDao.insertTags( recipe.tags.map { it.toEntity() } )
//        recipeDao.insertComments(recipe.comments.map { it.toEntity() })
//        return id
    }

    override suspend fun insert(recipeList: List<Recipe>) {
        recipeList.forEach { insert(it) }//.also { println("RECIPES:: listtop=" + recipeList[0]) }
    }

    override suspend fun loadCategories(): List<Category> {
        return categoryDao.getAll().map { it.toModel() }
    }

    override suspend fun insertCategories(categoryList: List<Category>): List<Long> {
        return categoryDao.insertAll(categoryList.map { it.toEntity() })
    }

    override suspend fun searchIngredients(contains: String): List<RecipeIngredient> =
         recipeDao
            .searchIngredients("%$contains%")
            .map { it.toRecipeIngredient() }


    override suspend fun searchRecipes(contains: String): List<Recipe> =
        recipeDao
            .searchRecipes("%$contains%")
            .map { it.toRecipe() }


    override suspend fun setFavorite(recipe: Recipe) {
        recipeDao.insertTags( listOf(RecipeTag(0, recipe.id, "favorite").toEntity()) )
    }

    override suspend fun removeFavorite(recipe: Recipe) {
        recipeDao.removeTag( recipe.id, "favorite" )
    }

    override suspend fun getFavorites(): List<Recipe> {
        return recipeDao
            .getRecipesByTagTitle("favorite")
            .map { it.toRecipe() }
    }

//    override suspend fun getRecipesByIngredients(ingredients: List<RecipeIngredient>): List<Recipe> =
//        recipeDao
//            .getByIngredient(ingredients[0].title)
//            .filter { it.ingredients
//                .map { it.toRecipeIngredient() }
//                .containsAll(ingredients) }
//            .map { it.toRecipe() }

    override suspend fun getRecipesByIngredient(ingredient: RecipeIngredient): List<Recipe> =
        recipeDao
            .getByIngredient(ingredient.title)
            .map { it.toRecipe() }

}