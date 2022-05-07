package com.move.mvisample.presentation.view.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.move.mvisample.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}