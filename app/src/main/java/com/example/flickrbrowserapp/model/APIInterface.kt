package com.example.flickrbrowserapp.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIInterface
{
        @GET("rest/")
        fun getImages(@Query("method") method:String, @Query("api_key") apiKey:String,
                      @Query("tags")tags:String
                      , @Query("per_page") par_page:String,
                      @Query("format")format:String
                      ,@Query("nojsoncallback")nojsoncallback:String):Call<Flicker>


}