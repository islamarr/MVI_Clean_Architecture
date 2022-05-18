package com.islam.mvisample.presentation.view.details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.islam.mvisample.R
import com.islam.mvisample.common.IMAGE_SIZE_MULTIPLIER
import com.islam.mvisample.databinding.DetailsFragmentBinding
import com.islam.mvisample.presentation.view.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : BaseFragment<DetailsFragmentBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> DetailsFragmentBinding
        get() = DetailsFragmentBinding::inflate

    private val args: DetailsFragmentArgs by navArgs()

    override fun setupOnViewCreated(view: View) {
        val url = args.url
        loadImage(url)
    }

    private fun loadImage(url: String?) {
        Glide.with(requireContext()).load(url)
            .placeholder(R.drawable.loading_img)
            .error(R.drawable.placeholder_img)
            .thumbnail(IMAGE_SIZE_MULTIPLIER)
            .into(binding.carHDImage)
    }

}