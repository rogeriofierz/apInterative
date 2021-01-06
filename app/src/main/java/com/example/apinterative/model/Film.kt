package com.example.apinterative.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PopularMovies(
        val results: List<ResultFilm>
) : Parcelable

@Parcelize
data class ResultFilm(
        @SerializedName("episode_id") val episodeId : Int,
        val title: String,
        val opening_crawl: String,
        val director: String,
        val producer: String,
        val release_date: String
) : Parcelable