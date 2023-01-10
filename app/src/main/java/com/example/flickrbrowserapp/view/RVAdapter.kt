package com.example.flickrbrowserapp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.flickrbrowserapp.model.PhotoItem
import com.example.flickrbrowserapp.databinding.ItemRowBinding

class RVAdapter(val activity: MainActivity, var photoList:List<PhotoItem>):RecyclerView.Adapter<RVAdapter.ViewHolder>() {
    class ViewHolder(var binding:ItemRowBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        return ViewHolder(ItemRowBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        val photo=photoList[position]
        val server=photo.server
        val id=photo.id
        val secret=photo.secret

        holder.binding.apply{
            tvImageText.text=photo.title
            val PhotoLink="https://live.staticflickr.com/${server}/${id}_${secret}.jpg"
            Glide.with(activity).load(PhotoLink).into(photoView)
            conLayout.setOnClickListener { activity.showRV(PhotoLink) }


        }
    }

    override fun getItemCount(): Int
    {
        return photoList.size
    }

    fun updatephotolist(newList:List<PhotoItem>)
    {
        photoList=newList
        notifyDataSetChanged()
    }
}