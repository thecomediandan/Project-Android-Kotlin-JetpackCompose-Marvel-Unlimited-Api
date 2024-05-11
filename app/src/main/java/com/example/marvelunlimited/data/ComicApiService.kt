package com.example.marvelunlimited.data

import android.util.Log
import com.example.marvelunlimited.data.helpers.apiKeyPrivate
import com.example.marvelunlimited.data.helpers.apiKeyPublic
import com.example.marvelunlimited.model.Comic
import com.example.marvelunlimited.model.ComicGateway
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.security.MessageDigest
import javax.inject.Inject

class ComicApiService @Inject constructor(
    private val retrofitService: RetrofitService
): ComicGateway {
    override suspend fun getListComics(): List<Comic> {
        return withContext(Dispatchers.IO) {
            val ts = getTimestamp()
            val hash = getHash("$ts$apiKeyPrivate$apiKeyPublic")
            val comics = retrofitService.listAllComics(ts, apiKeyPublic, hash)
            if (comics.isSuccessful) Log.e("ComicProvider", "Success") else Log.e("ComicProvider", "Failed")
            comics.body()?.data?.results ?: emptyList()
        }
    }

    private fun getTimestamp() = (System.currentTimeMillis() / 1000).toString()

    private fun getHash(input: String): String {
        val bytes = input.toByteArray()
        val md = MessageDigest.getInstance("MD5")
        val digest = md.digest(bytes)
        return digest.joinToString("") { "%02x".format(it) }
    }
}