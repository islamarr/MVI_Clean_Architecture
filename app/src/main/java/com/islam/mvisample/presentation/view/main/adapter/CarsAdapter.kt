package com.islam.mvisample.presentation.view.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.islam.mvisample.R
import com.islam.mvisample.common.IMAGE_SIZE_MULTIPLIER
import com.islam.mvisample.databinding.OneItemListBinding
import com.islam.mvisample.domain.entites.CarImage
import com.islam.mvisample.domain.usecases.ImageURLQUERY
import com.islam.mvisample.presentation.view.main.MainFragmentDirections


class CarsAdapter(private val initialImagesDimens: Int) :
    ListAdapter<CarImage, CarsAdapter.ViewHolder>(CarsDiffCallback()) {

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
                val hqImageUrl = getHighQualityImageUrl(item)
                navigateToDetails(view, hqImageUrl)
            }
        }
    }

    private fun getHighQualityImageUrl(item: CarImage) = item.uri.replace(
        ImageURLQUERY.LOW_QUALITY.query,
        ImageURLQUERY.HIGH_QUALITY.query
    )

    private fun navigateToDetails(view: View, url: String) {
        view.findNavController().navigate(
            MainFragmentDirections.actionMainFragmentToDetailsFragment(url)
        )
    }

    private fun loadImage(context: Context, url: String?, logo: ImageView) {
        Glide.with(context).load(url)
            .placeholder(R.drawable.loading_img)
            .error(R.drawable.placeholder_img)
            .override(initialImagesDimens, initialImagesDimens)
            .thumbnail(IMAGE_SIZE_MULTIPLIER)
            .into(logo)
    }

}