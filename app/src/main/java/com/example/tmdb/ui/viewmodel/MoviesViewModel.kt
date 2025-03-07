package com.example.tmdb.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdb.data.RetrofitServiceFactory
import com.example.tmdb.data.model.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MoviesViewModel : ViewModel() {
    private val service = RetrofitServiceFactory.makeRetrofitService()
    private val _movies = MutableStateFlow<List<Result>>(emptyList())
    val movies: StateFlow<List<Result>> = _movies

    init {
        fetchMovies()
    }

    private fun fetchMovies() {
        viewModelScope.launch {
            val movies = service.listNowPlayingMovies("c76ed6d50b96d2bfc0920abaeade0be3", "1")
            movies.let {
                _movies.value = it.results
            }
        }
    }
}