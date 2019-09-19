package com.example.kangaroo._1_login

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiInterface {

    @GET("getJson")
    fun getJsonSample(
    ): Call<DataModel.response>

    @POST("/users/login")
    fun loginAccount(
        @Body body : DataModel.loginBody
    ): Call<ResponseBody>

}