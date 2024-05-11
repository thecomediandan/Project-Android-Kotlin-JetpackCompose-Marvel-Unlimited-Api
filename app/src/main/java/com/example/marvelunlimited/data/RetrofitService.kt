package com.example.marvelunlimited.data

import com.example.marvelunlimited.data.helpers.baseUrl
import com.example.marvelunlimited.model.ResponseApi
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
// import retrofit2.http.Path
import retrofit2.http.Query
import java.security.MessageDigest

interface RetrofitService {

    @GET("/v1/public/comics")
    suspend fun listAllComics(
        @Query("ts") ts: String, @Query("apikey") apikey: String, @Query("hash") hash: String
    ): Response<ResponseApi>
}
