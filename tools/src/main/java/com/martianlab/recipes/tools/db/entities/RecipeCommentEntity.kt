package com.martianlab.recipes.tools.db.entities

import androidx.room.*
import com.martianlab.recipes.tools.db.converter.DateTimeConverter
import com.martianlab.recipes.tools.db.converter.StringListConverter
import java.util.*

@Entity(tableName = "recipe_comment", foreignKeys = [ForeignKey(entity = RecipeEntity::class, parentColumns = ["id"], childColumns = ["recipe_id"], onDelete = ForeignKey.CASCADE)])
@TypeConverters(DateTimeConverter::class, StringListConverter::class)
data class RecipeCommentEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id : Long,
    @ColumnInfo(name = "recipe_id")
    val recipeId : Long,
    val authorId : Long,
    val authorName : String,
    val date: Date,
    val text: String,
    val parentCommentId : Long?,
    val photoURLs : List<String>?
)