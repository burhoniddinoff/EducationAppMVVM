package com.example.educationappmvvm.presenter.screen.add

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.educationappmvvm.R
import com.example.educationappmvvm.databinding.ScreenAddCourseBinding
import com.example.educationappmvvm.presenter.utils.myLog

class AddCourseScreen : Fragment(R.layout.screen_add_course) {
    private var listener: ((String) -> Unit)? = null

    private val binding by viewBinding(ScreenAddCourseBinding::bind)
    private val navigation by lazy(LazyThreadSafetyMode.NONE) { findNavController() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.btnBack.setOnClickListener {
            navigation.popBackStack()
        }

        binding.btnSave.setOnClickListener {
            val enteredName = binding.editTextGroupName.text.toString().trim()
            if (enteredName.length >= 3) {

                listener?.invoke(enteredName)
//                navigation.navigate(R.id.action_addCourseScreen_to_courseScreen, Bundle().apply {
//                    putString("courseName", enteredName)
//                })

                navigation.popBackStack()

                "add course screen ${listener?.invoke(enteredName)}".myLog()

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

    fun setListener(block: (String) -> Unit) {
        listener = block
    }


}