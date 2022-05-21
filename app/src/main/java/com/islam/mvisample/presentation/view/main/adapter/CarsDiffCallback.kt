package com.islam.mvisample.presentation.view.main.adapter

import androidx.recyclerview.widget.DiffUtil
import com.islam.mvisample.domain.entites.CarImage

/**
DiffUtil to figure out the minimum number of changes to make from the old list to produce the new list.
RecyclerView can then update only the views that changed on screen.
 */
class CarsDiffCallback : DiffUtil.ItemCallback<CarImage>() {

    override fun areItemsTheSame(oldItem: CarImage, newItem: CarImage) =
        oldItem.uri == newItem.uri //TODO replace it with Id (Need to be added from backend side)

    override fun areContentsTheSame(oldItem: CarImage, newItem: CarImage) =
        oldItem == newItem
}