package com.example.unsplash.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.unsplash.R
import com.example.unsplash.ui.unsplash.UnsplashFragment
import com.example.unsplash.viewmodel.UnsplashViewModel

class MainActivity : AppCompatActivity() {

    private val unsplashViewModel: UnsplashViewModel by viewModels<UnsplashViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        unsplashViewModel.unsplashPhotoLiveData.observe(this, Observer {
//                listOfUnsplashPhoto -> Log.d("class MainActivity", listOfUnsplashPhoto.size.toString())
//        })

        val unsplashFragment = UnsplashFragment()
        val transaction = supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container_view, unsplashFragment)
            .commit()
    }
}