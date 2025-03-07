package com.example.tmdb.data


import com.example.tmdb.data.model.RemoteResult
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET("popular?")
    suspend fun listPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: String,
    ): RemoteResult

    @GET("now_playing?")
    suspend fun listNowPlayingMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: String,
    ): RemoteResult
}

object RetrofitServiceFactory {
    fun makeRetrofitService(): RetrofitService {
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/movie/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RetrofitService::class.java)
    }
}