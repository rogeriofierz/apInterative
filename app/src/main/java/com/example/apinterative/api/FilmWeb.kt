package com.example.apinterative.api

import com.google.gson.annotations.SerializedName

data class FilmResult(val results : List<Film>)

data class Film(val title : String,
                @SerializedName("episode_id") val episodeId : Int,
                val opening_crawl: String,
                val director: String,
                val producer: String,
                val release_date: String)