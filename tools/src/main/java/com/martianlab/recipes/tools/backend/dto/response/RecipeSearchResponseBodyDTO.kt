package com.martianlab.recipes.tools.backend.dto.response

import com.google.gson.annotations.SerializedName
import com.martianlab.recipes.tools.backend.dto.CategoryDTO
import com.martianlab.recipes.tools.backend.dto.RecipeCookingDTO
import com.martianlab.recipes.tools.backend.dto.RecipeDTO
import com.martianlab.recipes.tools.backend.dto.RecipeIngredientDTO

class RecipeSearchResponseBodyDTO(

    @SerializedName("RecipeList")
    val recipeList: List<RecipeDTO>?,

    @SerializedName("RecipeCookingList")
    val recipeCookingList: List<RecipeCookingDTO>?,

    @SerializedName("Category")
    val categoryList: List<CategoryDTO>?,

    @SerializedName("Total")
    val total: Int,

    @SerializedName("ErrorList")
    val errors: Array<UtkerrorDTO>?,

    @SerializedName("RecipeIngredientList")
    val ingredientList: List<RecipeIngredientDTO>?
)