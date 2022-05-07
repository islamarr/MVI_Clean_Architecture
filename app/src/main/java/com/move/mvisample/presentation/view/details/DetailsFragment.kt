package com.move.mvisample.presentation.view.details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.move.mvisample.R
import com.move.mvisample.databinding.DetailsFragmentBinding
import com.move.mvisample.presentation.view.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : BaseFragment<DetailsFragmentBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> DetailsFragmentBinding
        get() = DetailsFragmentBinding::inflate

    override fun setupOnViewCreated(view: View) {
        val url = arguments?.getString("url")
        loadImage(url)
    }

    private fun loadImage(url: String?) {
        Glide.with(requireContext()).load(url)
            .placeholder(R.drawable.ic_launcher_background)
            .thumbnail(0.1f)
            .into(binding?.carHDImage!!)
    }
}