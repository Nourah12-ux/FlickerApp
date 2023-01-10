package com.example.flickrbrowserapp.model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository(val apiInterface: APIInterface) {
    var apiKey="1255cfc2cc28d37d8ac0d027a6c6c13c"
    var mutablephotolist=MutableLiveData<List<PhotoItem>>()
    val photoList= arrayListOf<PhotoItem>()

    fun getImages(input:String):MutableLiveData<List<PhotoItem>>{
        apiInterface.getImages("flickr.photos.search", apiKey, input, "10",
            "json", "1").enqueue(object : Callback<Flicker> {
            override fun onResponse(call: Call<Flicker>, response: Response<Flicker>) {
                val body = response.body()
                if (body != null) {
                    val photos = body.photos
                    val photosList = photos.photo
                    for (photo in photosList)
                    {
                        val photoItem= PhotoItem(photo.id,photo.server,photo.secret,photo.title)
                        photoList.add(photoItem)
                        Log.d("retrofit", "onResponse: ${photo.title}")
                    }
                    mutablephotolist.postValue(photoList)
                }
            }

            override fun onFailure(call: Call<Flicker>, t: Throwable) {
//                        Toast.makeText(this@MainActivity, "No Images Found", Toast.LENGTH_LONG)
//                            .show()
                Log.d("retrofit", "onFailure:${t.message}")

            }
        })
        return mutablephotolist
    }
}