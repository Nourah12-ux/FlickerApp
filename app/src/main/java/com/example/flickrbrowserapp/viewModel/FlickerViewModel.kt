package com.example.flickrbrowserapp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.flickrbrowserapp.model.APIClint
import com.example.flickrbrowserapp.model.APIInterface
import com.example.flickrbrowserapp.model.PhotoItem
import com.example.flickrbrowserapp.model.Repository

class FlickerViewModel():ViewModel() {
    lateinit var repository: Repository
    init {
        val apiClint = APIClint().getClint()
        if (apiClint != null) {
            val apiInterface = apiClint.create(APIInterface::class.java)
           repository=Repository(apiInterface)

        }

    }

    fun getImeges(input:String):MutableLiveData<List<PhotoItem>>{
        return repository.getImages(input)
    }
}