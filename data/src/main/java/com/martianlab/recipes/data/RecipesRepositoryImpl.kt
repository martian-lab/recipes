package com.martianlab.recipes.data

import android.content.SharedPreferences
import com.martianlab.recipes.domain.*
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class RecipesRepositoryImpl @Inject constructor(
    private val dbApi: DbApi,
    private val backendApi: backendApi,
    private val preferences: SharedPreferences
) : RecipesRepository {



}


