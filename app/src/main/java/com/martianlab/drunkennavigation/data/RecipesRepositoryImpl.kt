package com.martianlab.drunkennavigation.data

import android.content.SharedPreferences
import com.martianlab.drunkennavigation.data.db.UserDao
import com.martianlab.drunkennavigation.domain.*
import com.martianlab.drunkennavigation.model.tools.AppExecutors
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class RecipesRepositoryImpl @Inject constructor(
    private val appExecutors: AppExecutors,
    private val userDao: UserDao,
    private val recipesService: RecipesService,
    private val preferences: SharedPreferences
) : RecipesRepository {



}


