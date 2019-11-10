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
import com.android.example.github.util.LiveDataCallAdapterFactory
import com.martianlab.recipes.domain.BackendApi
import com.martianlab.recipes.tools.db.dao.RecipeDao
import com.martianlab.recipes.model.tools.AppExecutors
import com.martianlab.recipes.tools.db.dao.UserDao
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {
    @Singleton
    @Provides
    fun provideDNaviService(): BackendApi {
        return Retrofit.Builder()
            .baseUrl("http://example.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .build()
            .create(BackendApi::class.java)
    }

    @Singleton
    @Provides
    fun provideDb(app: Application): com.martianlab.recipes.tools.db.RecipesDb {
        return Room
            .databaseBuilder(app, com.martianlab.recipes.tools.db.RecipesDb::class.java, "recipes.db")
            .fallbackToDestructiveMigration()
            .build()
    }


    @Singleton
    @Provides
    fun provideUserDao(db: com.martianlab.recipes.tools.db.RecipesDb): UserDao {
        return db.userDao()
    }

    @Singleton
    @Provides
    fun provideRecipeDao(db: com.martianlab.recipes.tools.db.RecipesDb): RecipeDao {
        return db.recipeDao()
    }

    @Singleton
    @Provides
    fun provideExecutors() : AppExecutors = AppExecutors()


    @Singleton
    @Provides
    fun providePreferences( app: Application ) : SharedPreferences = PreferenceManager.getDefaultSharedPreferences(app)


}
