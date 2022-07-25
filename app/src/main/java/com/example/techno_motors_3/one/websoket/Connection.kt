package com.example.techno_motors_3.one.websoket


import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASEURL = "http://192.168.0.111"

class Connection {
    private val gson: Gson = GsonBuilder()
        .setLenient()
        .create()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASEURL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val apiServiceRequest = retrofit.create(ServerApi::class.java)
//    val call = apiServiceRequest.sendServiceRequest()

// for test


fun req(){

//    call.enqueue(object : Callback<String>{
//        override fun onResponse(call: Call<String>, response: Response<String>) {
//            TODO("Not yet implemented")
//        }
//
//        override fun onFailure(call: Call<String>, t: Throwable) {
//            TODO("Not yet implemented")
//        }
//
//    })
}










}
