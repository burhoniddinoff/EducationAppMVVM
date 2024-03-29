package com.example.educationappmvvm.presenter.screen.add

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.educationappmvvm.R
import com.example.educationappmvvm.databinding.ScreenAddCourseBinding
import com.example.educationappmvvm.presenter.data.model.CourseData
import com.example.educationappmvvm.presenter.screen.course.CourseViewModel
import com.example.educationappmvvm.presenter.utils.myLog
import com.example.educationappmvvm.presenter.utils.popBackStack

class AddCourseScreen : Fragment(R.layout.screen_add_course) {
    private var listener: ((String) -> Unit)? = null
    private var currentData: String = ""
    private var currentUsedDates: List<CourseData> = emptyList()
    private val binding by viewBinding(ScreenAddCourseBinding::bind)

    private val navController by lazy(LazyThreadSafetyMode.NONE) { findNavController() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val name = binding.editTextGroupName
        name.setText(currentData)

        binding.btnBack.setOnClickListener {
//            navController.popBackStack()
            popBackStack()
        }

        binding.btnSave.setOnClickListener {
            val enteredName = name.text.toString().trim()
            if (enteredName.length >= 3 && checkName(enteredName)) {
                listener?.invoke(enteredName)
//                navController.popBackStack()
                popBackStack()
            } else if (enteredName.isEmpty()) {
                binding.groupInput.error = "Please, enter course name!"
            } else if (enteredName.length < 3) {
                binding.groupInput.error = "Course name should consist of at least 3 characters!"
            } else {
                binding.groupInput.error =
                    "This course name is already in use, or didn't meet required criteria!"

                binding.editTextGroupName.addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(
                        s: CharSequence?,
                        start: Int,
                        count: Int,
                        after: Int,
                    ) {
                    }

                    override fun onTextChanged(
                        s: CharSequence?,
                        start: Int,
                        before: Int,
                        count: Int,
                    ) {
                    }

                    override fun afterTextChanged(s: Editable?) {
                        binding.groupInput.isErrorEnabled =
                            binding.editTextGroupName.text.toString().trim().length < 3
                    }
                })

            }
        }
    }

    private fun checkName(enteredName: String): Boolean {
        return !currentUsedDates.any { it.name == enteredName.trim() }
    }


    fun setEditableData(name: String) {
        currentData = name
    }

    fun setUsedData(data: List<CourseData>) {
        currentUsedDates = data
    }

    fun setOnSaveClickListener(block: (String) -> Unit) {
        listener = block
    }
}