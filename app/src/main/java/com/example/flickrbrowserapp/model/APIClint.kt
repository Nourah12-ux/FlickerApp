package com.example.flickrbrowserapp.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIClint {
    var retrofit:Retrofit? = null
    fun getClint(): Retrofit? {
       retrofit = Retrofit.Builder()
        .baseUrl("https://www.flickr.com/services/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        return retrofit
    }
}