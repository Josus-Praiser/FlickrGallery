package com.josus.flickr

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.josus.flickr.networking.Photo

class ItemAdapter(private val context:Context,private val data: List<Photo>) :
    RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val img: ImageView = view.findViewById(R.id.item_img)

        fun bind(data: Photo, context: Context)  {
            Glide.with(context).load(data.url_s).placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background).into(img)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)

        return ItemViewHolder(adapterLayout)

    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = data.get(position)
        holder.bind(item,context)

    }

    override fun getItemCount(): Int {

            return data.size

    }

}