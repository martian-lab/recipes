package com.martianlab.recipes.tools.db.entities

import androidx.room.*


@Entity(tableName = "recipe")
data class RecipeEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id : Long = 0,
    @ColumnInfo(name = "title")
    val title : String,
    @ColumnInfo(name = "text")
    val text : String,
    @ColumnInfo(name = "complexity")
    val complexity : Int,
    @ColumnInfo(name = "person_count")
    val personCount : Int,
    @ColumnInfo(name = "image_url")
    val imageURL : String,
    @ColumnInfo(name = "rating")
    val rating : Double?,
    @ColumnInfo(name = "votes")
    val ratingVotes : Int?
//    @ColumnInfo(name = "tags")
//    val tags: Set<CategoryEntity>,
//    @Embedded
//    @Relation(parentColumn = "id", entityColumn = "recipe_id")
//    val stages : List<RecipeStageEntity>
//    @Embedded
//    @ColumnInfo(name = "ingredients")
//    val ingredients : List<RecipeIngredient>,
//    @Embedded
//    @ColumnInfo(name = "comments")
//    val comments: List<RecipeComment>
)