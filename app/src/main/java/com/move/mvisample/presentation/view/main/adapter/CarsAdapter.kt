package com.move.mvisample.presentation.view.main.adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.move.mvisample.R
import com.move.mvisample.databinding.OneItemListBinding
import com.move.mvisample.domain.entites.CarImage


class CarsAdapter : ListAdapter<CarImage, CarsAdapter.ViewHolder>(CarsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            OneItemListBinding.bind(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.one_item_list, parent, false)
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val listItems = getItem(position)
        holder.bind(listItems)
    }

    inner class ViewHolder(itemView: OneItemListBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        private val carImage: ImageView = itemView.carImage

        fun bind(item: CarImage) {
            val context = itemView.context
            loadImage(context, item.uri, carImage)

            itemView.setOnClickListener { view ->
                //TODO navigate to 2 screen
                Toast.makeText(context, "ssssssssssssss", Toast.LENGTH_LONG).show()
            }
        }
    }


    private fun loadImage(context: Context, url: String?, logo: ImageView) {
        Glide.with(context).load(Uri.parse(url))
            .placeholder(R.drawable.ic_launcher_background)
            .thumbnail(0.1f)
            .into(logo)
    }

}