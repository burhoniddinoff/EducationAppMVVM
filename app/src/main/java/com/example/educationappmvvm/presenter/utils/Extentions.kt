package com.example.educationappmvvm.presenter.utils

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.educationappmvvm.R

fun String.myLog() = Log.d("TTT", this)

fun FragmentActivity.replaceScreen(fm: Fragment) {
    supportFragmentManager.beginTransaction()
        .replace(R.id.container, fm)
        .addToBackStack(fm::class.java.name)
        .commit()
}

fun Fragment.replaceScreen(fm: Fragment) {
    requireActivity().replaceScreen(fm)
}