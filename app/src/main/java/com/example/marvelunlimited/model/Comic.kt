package com.example.marvelunlimited.model

data class ResponseApi(val data: Data)

data class Data(val results: List<Comic>)

data class Comic(val title: String, val creators: Creators, val thumbnail: Image, val description: String, val textobjects: List<TextObject>?, val prices: List<Price>?)

data class Creators(val items: List<Creator>)
data class Creator(val name: String, val role: String)

data class Image(val path: String, val extension: String)

data class TextObject(val type: String, val language: String, val text: String)

data class Price(val type: String, val price: String)
