package com.example.apinterative.api

import com.example.apinterative.model.PopularMovies
import io.reactivex.Observable
import retrofit2.http.GET

interface TmdbEndpoints {

    @GET("films")
    fun getMovies(): Observable<PopularMovies>

}