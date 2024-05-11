package com.example.marvelunlimited.model

interface ComicGateway {
    suspend fun getListComics(): List<Comic>
}