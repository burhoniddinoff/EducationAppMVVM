package com.example.educationappmvvm.presenter.screen.course

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
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
import com.example.educationappmvvm.presenter.utils.replaceScreen

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
            addCourse()
        }

        adapter.setOnLongClickListener {
            showDialog(it)
        }

        adapter.setOnClickListener {
            val descr = CourseScreenDirections.actionCourseScreenToGroupScreen(it)
            navController.navigate(descr)
        }

        viewModel.loadCourse()

    }

    private fun addCourse() {
        val addCourse = AddCourseScreen()

//            addCourse.setUsedData(viewModel())
        addCourse.setOnSaveClickListener { name ->
            viewModel.addCourse(CourseData(0, name))
        }

        replaceScreen(addCourse)
//            navController.navigate(CourseScreenDirections.actionCourseScreenToAddCourseScreen())

    }

    private fun showDialog(data: CourseData) {
        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.bottom_sheet)

        dialog.findViewById<ImageView>(R.id.dialog_delete).setOnClickListener {
            viewModel.deleteCourse(data)
            dialog.dismiss()
        }

        dialog.findViewById<ImageView>(R.id.dialog_edit).setOnClickListener {

            val titleDialog = AddCourseScreen()

            titleDialog.setEditableData(data.name)
//            titleDialog.setUsedData(viewModel.getAllCourse())
            titleDialog.setOnSaveClickListener { name ->
                viewModel.updateCourse(CourseData(data.id, name))
            }

            dialog.dismiss()
            replaceScreen(titleDialog)
        }

        dialog.show()

        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
        )

        dialog.window?.attributes?.windowAnimations = R.style.DialogAnimation
        dialog.window?.setGravity(Gravity.BOTTOM)
    }

}