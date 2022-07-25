package com.example.techno_motors_3.one.websoket

import com.example.techno_motors_3.one.domain.ServiceRequestForm
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ServerApi {
    @POST("/api/sendServiceRequest.php")
    fun sendServiceRequest(@Body serviceRequestForm: ServiceRequestForm): Call<String>
}