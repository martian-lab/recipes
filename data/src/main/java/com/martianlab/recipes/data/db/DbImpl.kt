package com.martianlab.recipes.tools.db

import android.database.sqlite.SQLiteConstraintException
import androidx.paging.DataSource
import com.martianlab.recipes.data.db.DbApi
import com.martianlab.recipes.entities.Category
import com.martianlab.recipes.entities.Recipe
import com.martianlab.recipes.entities.RecipeTag
import com.martianlab.recipes.tools.db.dao.CategoryDao
import com.martianlab.recipes.tools.db.dao.RecipeDao
import com.martianlab.recipes.tools.db.mapper.toEntity
import com.martianlab.recipes.tools.db.mapper.toEntityWithDependencies
import com.martianlab.recipes.tools.db.mapper.toModel
import com.martianlab.recipes.tools.db.mapper.toRecipe

class DbImpl(
    private val recipeDao: RecipeDao,
    private val categoryDao: CategoryDao
) : DbApi {


    override suspend fun getRecipes(tag: RecipeTag): List<Recipe> {
        return recipeDao.getRecipes(tag.id).map { it.toRecipe() }
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
}