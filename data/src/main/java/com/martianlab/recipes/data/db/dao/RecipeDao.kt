package com.martianlab.recipes.tools.db.dao

import androidx.room.*
import com.martianlab.recipes.tools.db.entities.*
import androidx.paging.DataSource

@Dao
interface RecipeDao {

    @Transaction
    @Query("SELECT * FROM recipe JOIN recipe_tag ON recipe.id = recipe_id WHERE recipe_tag.id = :tag_id")
    suspend fun getRecipes(tag_id: Long): List<RecipeWithDependencies>

    @Transaction
    @Query("SELECT * FROM recipe")
    suspend fun getRecipes(): List<RecipeWithDependencies>

    @Transaction
    @Query("SELECT * FROM recipe WHERE id = :id")
    suspend fun getById(id: Long): RecipeWithDependencies

    @Transaction
    @Query("SELECT * FROM recipe JOIN recipe_tag ON recipe.id = recipe_id WHERE recipe_tag.id = :tag_id")
    fun getRecipesPaged(tag_id : Long): DataSource.Factory<Int, RecipeWithDependencies>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(recipeList : List<RecipeEntity>) : List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert( recipe : RecipeEntity ) : Long



    @Transaction
    suspend fun insert( recipe : RecipeWithDependencies ) : Long {
        val id = insert(recipe.recipeEntity)
        insertComments(recipe.comments)
        insertIngredients(recipe.ingredients)
        insertTags(recipe.tags.toList())
        insertStages(recipe.stages)
        return id
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertComments(recipeCommentList : List<RecipeCommentEntity>) : List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIngredients(recipeIngredientList : List<RecipeIngredientEntity>) : List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStages(recipeStageList : List<RecipeStageEntity>) : List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTags(recipeTagList : List<RecipeTagEntity>) : List<Long>
}