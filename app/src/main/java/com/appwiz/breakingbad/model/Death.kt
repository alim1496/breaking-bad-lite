package com.appwiz.breakingbad.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "death_table")
data class Death (
    @Expose
    @SerializedName("death_id")
    @PrimaryKey
    @ColumnInfo(name = "death_id")
    var id: Int,
    @Expose
    @SerializedName("death")
    @ColumnInfo(name = "death")
    var death: String,
    @Expose
    @SerializedName("cause")
    @ColumnInfo(name = "cause")
    var cause: String,
    @Expose
    @SerializedName("responsible")
    @ColumnInfo(name = "responsible")
    var responsible: String,
    @Expose
    @SerializedName("last_words")
    @ColumnInfo(name = "last_words")
    var lastWords: String,
    @Expose
    @SerializedName("season")
    @ColumnInfo(name = "season")
    var season: Int,
    @Expose
    @SerializedName("episode")
    @ColumnInfo(name = "episode")
    var episode: Int,
    @Expose
    @SerializedName("number_of_deaths")
    @ColumnInfo(name = "number_of_deaths")
    var nos: Int
)
