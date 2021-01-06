package com.example.apinterative.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

object ServiceBuilder {
    private val client = OkHttpClient
            .Builder()
            .build()

    private val retrofit = Retrofit.Builder()
            .baseUrl("https://swapi.dev/api/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(TmdbEndpoints::class.java)

    fun buildService(): TmdbEndpoints {
        return retrofit
    }
}