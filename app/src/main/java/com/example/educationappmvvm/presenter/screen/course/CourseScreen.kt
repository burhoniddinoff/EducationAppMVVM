package com.example.educationappmvvm.presenter.screen.course

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.educationappmvvm.R
import com.example.educationappmvvm.databinding.ScreenCourseBinding
import com.example.educationappmvvm.presenter.data.model.CourseData
import com.example.educationappmvvm.presenter.screen.adapter.CourseAdapter
import com.example.educationappmvvm.presenter.screen.add.AddCourseScreen
import com.example.educationappmvvm.presenter.utils.myLog

class CourseScreen : Fragment(R.layout.screen_course) {
    private val binding by viewBinding(ScreenCourseBinding::bind)
    private val viewModel = CourseViewModel()
    private val adapter by lazy { CourseAdapter() }
    private val navController by lazy(LazyThreadSafetyMode.NONE) { findNavController() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.lsCourse.adapter = adapter

        viewModel.course.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        viewModel.isEmpty.observe(viewLifecycleOwner) {
            binding.textik.isVisible = !it
            binding.containerEmptyState.isVisible = it
        }

        binding.btnAddCourse.setOnClickListener {

            val addCourseScreen = AddCourseScreen()

            addCourseScreen.setListener {
                it.myLog()
                viewModel.addCourse(CourseData(0, it))
            }

            navController.navigate(CourseScreenDirections.actionCourseScreenToAddCourseScreen())
        }



        viewModel.loadCourse()

    }

}