package com.example.kangaroo._1_login

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



object DataModel {
    data class response(
        @Expose
        @SerializedName("fruit")
        val fruit: String,

        @Expose
        @SerializedName("time")
        val time: String
    )

    data class loginBody(
        val account: String,
        val password: String
    )
}