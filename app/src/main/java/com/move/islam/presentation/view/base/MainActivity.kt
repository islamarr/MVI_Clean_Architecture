package com.move.islam.presentation.view.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.move.islam.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}