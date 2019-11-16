package com.martianlab.recipes.tools.db.mapper

import com.martianlab.recipes.entities.Category
import com.martianlab.recipes.entities.Recipe
import com.martianlab.recipes.tools.db.entities.CategoryEntity
import com.martianlab.recipes.tools.db.entities.RecipeEntity

internal object CategoryEntityMapper {
    fun map2Model( entity: CategoryEntity) : Category =
        Category(
            id = entity.id,
            title = entity.title,
            imageUrl = entity.imageUrl,
            sort = entity.sort,
            total = entity.total
        )

    fun map2Entity( model: Category) : CategoryEntity =
        CategoryEntity(
            id = model.id,
            title = model.title,
            imageUrl = model.imageUrl,
            sort = model.sort,
            total = model.total
        )

}


fun CategoryEntity.toModel() =
    CategoryEntityMapper.map2Model(this)

fun Category.toEntity() =
    CategoryEntityMapper.map2Entity(this)