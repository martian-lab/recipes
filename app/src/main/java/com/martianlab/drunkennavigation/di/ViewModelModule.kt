package com.martianlab.drunkennavigation.di

import androidx.lifecycle.ViewModelProvider
import com.martianlab.drunkennavigation.data.RecipesRepositoryImpl
import com.martianlab.drunkennavigation.domain.RecipesRepository


import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface ViewModelModule {


    @Singleton
    @Binds
    fun provideRecipesRepository(repositoryImpl: RecipesRepositoryImpl): RecipesRepository

}
