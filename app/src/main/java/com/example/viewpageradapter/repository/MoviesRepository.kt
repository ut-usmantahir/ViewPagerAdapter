package com.example.viewpageradapter.repository

import com.example.viewpageradapter.network.MoviesApi
import com.example.viewpageradapter.network.SafeApiRequest

class MoviesRepository(
    private val api: MoviesApi
): SafeApiRequest(){

    suspend fun getMovies() = apiRequest {api.getMovies()}
}