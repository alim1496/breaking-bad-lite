package com.appwiz.breakingbad.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "quotes_table")
data class Quote (
    @Expose
    @PrimaryKey
    @SerializedName("quote_id")
    @ColumnInfo(name = "quote_id")
    var id: Int,
    @Expose
    @SerializedName("quote")
    @ColumnInfo(name = "quote")
    var quote: String,
    @Expose
    @SerializedName("author")
    @ColumnInfo(name = "author")
    var author: String,
    @Expose
    @SerializedName("series")
    @ColumnInfo(name = "series")
    var series: String
)
