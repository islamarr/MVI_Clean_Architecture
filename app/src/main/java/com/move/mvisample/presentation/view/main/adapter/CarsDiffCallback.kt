package com.move.mvisample.presentation.view.main.adapter


import androidx.recyclerview.widget.DiffUtil
import com.move.mvisample.domain.entites.CarImage

class CarsDiffCallback : DiffUtil.ItemCallback<CarImage>() {

    override fun areItemsTheSame(oldItem: CarImage, newItem: CarImage) =
        oldItem.uri == newItem.uri //TODO replace it with Id (Need to be added from backend side)

    override fun areContentsTheSame(oldItem: CarImage, newItem: CarImage) =
        oldItem == newItem
}