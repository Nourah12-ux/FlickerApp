package com.example.flickrbrowserapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.flickrbrowserapp.databinding.ActivityMainBinding
import com.example.flickrbrowserapp.model.APIClint
import com.example.flickrbrowserapp.model.APIInterface
import com.example.flickrbrowserapp.model.Flicker
import com.example.flickrbrowserapp.model.PhotoItem
import com.example.flickrbrowserapp.viewModel.FlickerViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity()
{
    lateinit var binding:ActivityMainBinding
    lateinit var viewModel: FlickerViewModel
     //var apiKey="1255cfc2cc28d37d8ac0d027a6c6c13c"
     var input =""
    val photoList= arrayListOf<PhotoItem>()
    override fun onCreate(savedInstanceState: Bundle?)
    {
        //https://www.flickr.com/services/rest/?method=flickr.photos.search&api_key=1255cfc2cc28d37d8ac0d027a6c6c13c&tags=cat&per_page=&format=json&nojsoncallback=1
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        viewModel=ViewModelProvider(this).get(FlickerViewModel::class.java)

        val adapter= RVAdapter(this,photoList)
        binding.rvMain.adapter=adapter

        binding.photosView.setOnClickListener{
            hideRV()
        }


        binding.btSearch.setOnClickListener {
            input = binding.etSearch.text.toString()
            viewModel.getImeges(input).observe(this) {
                adapter.updatephotolist(it)
            }
        }
    }
    fun showRV(link:String)
    {
        binding.apply {
            Glide.with(this@MainActivity).load(link).into(photosView)
            photosView.isVisible = true
            rvMain.isVisible = false
            ll.isVisible = false
        }
    }
    fun hideRV()
    {
        binding.apply {
            photosView.isVisible = false
            rvMain.isVisible = true
            ll.isVisible = true
        }
    }
}