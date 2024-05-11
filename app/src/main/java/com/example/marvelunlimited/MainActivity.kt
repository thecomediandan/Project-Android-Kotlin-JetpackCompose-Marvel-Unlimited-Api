package com.example.marvelunlimited

import Home
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.marvelunlimited.model.ComicUsesCases
import com.example.marvelunlimited.ui.theme.MarvelUnlimitedTheme
import com.example.marvelunlimited.view.ComicViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity: ComponentActivity(){

    @Inject
    lateinit var comicUsesCases: ComicUsesCases

    private val comicViewModel: ComicViewModel by viewModels()

    @SuppressLint("PrivateResource", "UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MarvelUnlimitedTheme {
                Home(comicViewModel = comicViewModel, context = this, comicUsesCases = comicUsesCases, drawable = R.drawable.background, text = R.string.search)
            }
        }
    }
}