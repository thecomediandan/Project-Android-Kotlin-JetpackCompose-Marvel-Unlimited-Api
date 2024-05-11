package com.example.marvelunlimited.view

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ComicViewModel @Inject constructor(): ViewModel() {
    private val _isLoading: MutableStateFlow<Boolean> = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    fun stopLoading() {
        _isLoading.value = false
    }
}