package com.martianlab.recipes.di

import com.martianlab.recipes.data.RecipesRepositoryImpl
import com.martianlab.recipes.domain.RecipesInteractor
import com.martianlab.recipes.domain.RecipesInteractorImpl
import com.martianlab.recipes.domain.RecipesRepository


import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface ViewModelModule {


    @Singleton
    @Binds
    fun provideRecipesRepository(repositoryImpl: RecipesRepositoryImpl): RecipesRepository


    @Singleton
    @Binds
    fun provideRecipesInteractor(interactorImpl: RecipesInteractorImpl): RecipesInteractor
}
