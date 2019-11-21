package com.martianlab.recipes.tools.db

import com.martianlab.recipes.domain.DbApi
import com.martianlab.recipes.entities.Category
import com.martianlab.recipes.entities.Recipe
import com.martianlab.recipes.entities.RecipeTag
import com.martianlab.recipes.tools.db.dao.CategoryDao
import com.martianlab.recipes.tools.db.dao.RecipeDao
import com.martianlab.recipes.tools.db.mapper.toEntity
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

    override suspend fun getRecipeById(id: Long): Recipe {
        return recipeDao.getById(id).toRecipe()
    }

    override suspend fun insert(recipe: Recipe) : Long {
        val id = recipeDao.insert(recipe = recipe.toEntity() )
        recipeDao.insertIngredients( recipe.ingredients.map { it.toEntity() } )
        recipeDao.insertStages( recipe.stages.map { it.toEntity() } )
        recipeDao.insertTags( recipe.tags.map { it.toEntity() } )
        recipeDao.insertComments(recipe.comments.map { it.toEntity() })
        return id
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