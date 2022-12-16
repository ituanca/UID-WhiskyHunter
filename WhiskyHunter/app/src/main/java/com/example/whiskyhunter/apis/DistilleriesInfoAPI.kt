package com.example.whiskyhunter.apis

import com.example.whiskyhunter.models.DistilleriesInfo
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface DistilleriesInfoAPI {

    @GET("distilleries_info/")
    @Headers("Content-Type: application/json")
    fun getDistilleriesInfo(@Header("Authorization") token:String): Call<List<DistilleriesInfo>>

    companion object{
        fun createApi():DistilleriesInfoAPI{
            val retrofit = Retrofit.Builder()
                .baseUrl ( "https://whiskyhunter.net/api/" )
                .addConverterFactory ( GsonConverterFactory.create() )
                .build()
            return retrofit.create(DistilleriesInfoAPI::class.java)
        }
    }
}