package com.martianlab.recipes.tools.db.dao

import androidx.room.*

@Dao
interface UserDao {

//    @Query("SELECT * FROM user")
//    suspend fun getAll(): List<User>
//
//    @Query("SELECT * FROM user WHERE id = :id")
//    suspend fun getById(id: Int): User
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertAll(users: List<User>)
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insert(user: User)
//
//    @Update
//    suspend fun updateUser( user: User)
}