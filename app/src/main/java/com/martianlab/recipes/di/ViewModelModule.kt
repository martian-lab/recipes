package com.martianlab.recipes.di

import com.martianlab.recipes.data.RecipesRepositoryImpl
import com.martianlab.recipes.domain.RecipesRepository


import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface ViewModelModule {


    @Singleton
    @Binds
    fun provideRecipesRepository(repositoryImpl: com.martianlab.recipes.data.RecipesRepositoryImpl): com.martianlab.recipes.domain.RecipesRepository

}
