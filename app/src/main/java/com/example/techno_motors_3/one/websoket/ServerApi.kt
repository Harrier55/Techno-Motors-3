package com.example.techno_motors_3.one.websoket

import okhttp3.Response
import retrofit2.Call
import retrofit2.http.POST

interface ServerApi {
    @POST("/api/sendServiceRequest.php")
    fun sendServiceRequest(): Call<String>
}