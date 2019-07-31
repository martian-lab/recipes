package com.martianlab.drunkennavigation.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.martianlab.drunkennavigation.data.db.entities.Category
import com.martianlab.drunkennavigation.data.db.entities.User

@Dao
interface CategoryDao {

    @Query("SELECT * FROM category")
    fun getAll(): LiveData<List<Category>>

    @Query("SELECT * FROM category WHERE id = :id")
    fun getById(id: Int): LiveData<Category>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(categories: List<Category>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(category: Category)

    @Update
    fun updateCategory( category: Category )
}