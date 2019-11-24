/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.martianlab.recipes.di

import android.app.Application
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.room.Room
import com.martianlab.recipes.domain.BackendApi
import com.martianlab.recipes.data.db.DbApi
import com.martianlab.recipes.tools.db.dao.RecipeDao
import com.martianlab.recipes.tools.backend.BackendImpl
import com.martianlab.recipes.tools.db.DbImpl
import com.martianlab.recipes.tools.db.RecipesDb
import com.martianlab.recipes.tools.db.dao.CategoryDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {
    @Singleton
    @Provides
    fun provideBackendApi(): BackendApi {
        return BackendImpl
    }

    @Singleton
    @Provides
    fun provideDb(app: Application): RecipesDb {
        return Room
            .databaseBuilder(app, RecipesDb::class.java, "recipes.db")
            .fallbackToDestructiveMigration()
            .build()
    }


    @Singleton
    @Provides
    fun provideRecipeDao(db : RecipesDb ): RecipeDao {
        return db.recipeDao()
    }

    @Singleton
    @Provides
    fun provideCategoryDao(db : RecipesDb ): CategoryDao {
        return db.categoryDao()
    }

    @Singleton
    @Provides
    fun provideDbApi( recipeDao: RecipeDao, categoryDao: CategoryDao ): DbApi {
        return DbImpl(recipeDao, categoryDao)
    }


    @Singleton
    @Provides
    fun providePreferences( app: Application ) : SharedPreferences = PreferenceManager.getDefaultSharedPreferences(app)


}
