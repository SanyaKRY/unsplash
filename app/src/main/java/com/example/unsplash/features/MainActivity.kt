package com.example.unsplash.features

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.unsplash.R
import com.example.unsplash.features.unsplashphotos.presentation.ui.UnsplashPhotosFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val unsplashPhotosFragment = UnsplashPhotosFragment()
        val transaction = supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container_view, unsplashPhotosFragment)
            .commit()
    }
}