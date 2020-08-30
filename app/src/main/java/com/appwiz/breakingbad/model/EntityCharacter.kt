package com.appwiz.breakingbad.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "character_table")
data class EntityCharacter (
    @PrimaryKey
    @ColumnInfo(name = "char_id")
    var id: Int,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "birthday")
    var birthday: String,
    @ColumnInfo(name = "occupation")
    var occupation: String,
    @ColumnInfo(name = "img")
    var image: String,
    @ColumnInfo(name = "status")
    var status: String,
    @ColumnInfo(name = "nickname")
    var nickname: String,
    @ColumnInfo(name = "portrayed")
    var portrayed: String,
    @ColumnInfo(name = "appearance")
    var appearance: String,
    @ColumnInfo(name = "better_call_saul_appearance")
    var appearanceBCS: String,
    @ColumnInfo(name = "category")
    var category: String
)
