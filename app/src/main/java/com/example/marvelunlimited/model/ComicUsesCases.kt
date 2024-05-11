package com.example.marvelunlimited.model

import com.example.marvelunlimited.data.ComicApiService
import javax.inject.Inject

class ComicUsesCases @Inject constructor(
    private val comicGateway: ComicGateway
) {
    suspend fun getListComics() = comicGateway.getListComics()
}