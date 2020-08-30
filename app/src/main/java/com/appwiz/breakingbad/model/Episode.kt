package com.appwiz.breakingbad.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Episode (
    @Expose
    @SerializedName("episode_id")
    var id: Int,
    @Expose
    @SerializedName("title")
    var title: String,
    @Expose
    @SerializedName("season")
    var season: String,
    @Expose
    @SerializedName("characters")
    var characters: List<String>,
    @Expose
    @SerializedName("air_date")
    var date: String,
    @Expose
    @SerializedName("episode")
    var episode: String,
    @Expose
    @SerializedName("series")
    var series: String
)