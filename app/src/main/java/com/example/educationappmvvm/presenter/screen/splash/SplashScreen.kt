package com.example.educationappmvvm.presenter.screen.splash

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.educationappmvvm.R

@SuppressLint("CustomSplashScreen")
class SplashScreen : Fragment(R.layout.screen_splash) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Handler().postDelayed({
            findNavController().navigate(SplashScreenDirections.actionSplashScreenToCourseScreen())
        }, 1500)

    }

}