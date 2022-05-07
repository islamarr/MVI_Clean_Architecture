package com.move.islam.presentation.view.main.adapter


import androidx.recyclerview.widget.DiffUtil
import com.move.islam.domain.entites.CarImage

class CarsDiffCallback : DiffUtil.ItemCallback<CarImage>() {

    override fun areItemsTheSame(oldItem: CarImage, newItem: CarImage) =
        oldItem.uri == newItem.uri //TODO replace it with Id

    override fun areContentsTheSame(oldItem: CarImage, newItem: CarImage) =
        oldItem == newItem
}