package com.martianlab.recipes.tools.db.dao

import androidx.room.*
import com.martianlab.recipes.tools.db.entities.*

@Dao
interface RecipeDao {

    @Query("SELECT * FROM recipe JOIN recipe_tag ON recipe.id = recipe_id WHERE recipe_tag.id = :tag_id")
    suspend fun getRecipes(tag_id: Long): List<RecipeWithDependencies>

    @Query("SELECT * FROM recipe")
    suspend fun getRecipes(): List<RecipeWithDependencies>

    @Query("SELECT * FROM recipe WHERE id = :id")
    suspend fun getById(id: Long): RecipeWithDependencies

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(recipeList : List<RecipeEntity>) : List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert( recipe : RecipeEntity ) : Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertComments(recipeCommentList : List<RecipeCommentEntity>) : List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIngredients(recipeIngredientList : List<RecipeIngredientEntity>) : List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStages(recipeStageList : List<RecipeStageEntity>) : List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTags(recipeTagList : List<RecipeTagEntity>) : List<Long>
}