package com.example.marvelunlimited.data

import com.example.marvelunlimited.model.ComicGateway
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface ComicProvider {

    @Binds
    fun provideComic(comicService: ComicApiService): ComicGateway
}