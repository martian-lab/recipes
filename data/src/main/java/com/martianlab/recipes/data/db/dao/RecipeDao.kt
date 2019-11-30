package com.martianlab.recipes.tools.db.dao

import androidx.room.*
import com.martianlab.recipes.tools.db.entities.*
import androidx.paging.DataSource

@Dao
interface RecipeDao {

    @Transaction
    @Query("SELECT * FROM recipe JOIN recipe_tag ON recipe.id = recipe_id WHERE recipe_tag.title = :tag_title")
    suspend fun getRecipesByTagTitle(tag_title: String): List<RecipeWithDependencies>

    @Transaction
    @Query("SELECT * FROM recipe")
    suspend fun getRecipes(): List<RecipeWithDependencies>


    @Transaction
    @Query("SELECT * FROM recipe WHERE id = :id")
    suspend fun getById(id: Long): RecipeWithDependencies

    @Transaction
    @Query("SELECT * FROM recipe JOIN recipe_tag ON recipe.id = recipe_id WHERE recipe_tag.id = :tag_id ORDER BY recipe.rating DESC, recipe.id DESC")
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

    @Query("DELETE FROM recipe_tag WHERE recipe_id = :recipeId AND title  = :tagTitle")
    suspend fun removeTag( recipeId : Long, tagTitle : String )

    @Transaction
    @Query("SELECT * FROM recipe WHERE recipe.title LIKE :contains")
    suspend fun searchRecipes( contains : String ) : List<RecipeWithDependencies>

    @Query("SELECT * FROM recipe_ingredient WHERE recipe_ingredient.title LIKE :contains")
    suspend fun searchIngredients( contains : String ) : List<RecipeIngredientEntity>

    @Transaction
    @Query("SELECT * FROM recipe")
    suspend fun getFavorites(): List<RecipeWithDependencies>

    @Transaction
    @Query("SELECT * FROM recipe JOIN recipe_ingredient ON recipe.id = recipe_id WHERE recipe_ingredient.title = :title")
    suspend fun getByIngredient(title : String) : List<RecipeWithDependencies>

}