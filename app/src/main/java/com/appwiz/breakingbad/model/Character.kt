package com.appwiz.breakingbad.model

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

data class Character (
    @Expose
    @SerializedName("char_id")
    var id: Int,
    @Expose
    @SerializedName("name")
    var name: String,
    @Expose
    @SerializedName("birthday")
    var birthday: String,
    @Expose
    @SerializedName("occupation")
    var occupation: List<String>,
    @Expose
    @SerializedName("img")
    var image: String,
    @Expose
    @SerializedName("status")
    var status: String,
    @Expose
    @SerializedName("nickname")
    var nickname: String,
    @Expose
    @SerializedName("portrayed")
    var portrayed: String,
    @Expose
    @SerializedName("appearance")
    var appearance: List<Int>,
    @Expose
    @SerializedName("better_call_saul_appearance")
    var appearanceBCS: List<Int>,
    @Expose
    @SerializedName("category")
    var category: String
)
