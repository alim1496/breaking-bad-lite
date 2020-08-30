package com.appwiz.breakingbad.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "episode_table")
data class EntityEpisode (
    @PrimaryKey
    @ColumnInfo(name = "episode_id")
    var id: Int,
    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "season")
    var season: String,
    @ColumnInfo(name = "characters")
    var characters: String,
    @ColumnInfo(name = "date")
    var date: String,
    @ColumnInfo(name = "episode")
    var episode: String,
    @ColumnInfo(name = "series")
    var series: String
)