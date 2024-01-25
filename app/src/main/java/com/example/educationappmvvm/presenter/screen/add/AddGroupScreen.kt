package com.example.educationappmvvm.presenter.screen.add

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.educationappmvvm.R
import com.example.educationappmvvm.databinding.ScreenAddGroupBinding
import com.example.educationappmvvm.presenter.data.model.GroupData
import com.example.educationappmvvm.presenter.utils.popBackStack

class AddGroupScreen : Fragment(R.layout.screen_add_group) {
    private var listener: ((String) -> Unit)? = null
    private var listenerEdit: ((String) -> Unit)? = null

    private var currentData: String = ""
    private var currentUsedDates: List<GroupData> = emptyList()
    private val binding by viewBinding(ScreenAddGroupBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val name = binding.editTextGroupName
        name.setText(currentData)

        binding.imageBack.setOnClickListener {
            popBackStack()
        }

        binding.btnSave.setOnClickListener {
            val enteredName = name.text.toString().trim()
            if (enteredName.length >= 3 && checkName(enteredName)) {
                listener?.invoke(enteredName)
                listenerEdit?.invoke(enteredName)
                popBackStack()
            } else if (enteredName.isEmpty()) {
                binding.groupInput.error = " Please, enter name!"
            } else if (enteredName.length < 3) {
                binding.groupInput.error = "Group name should consist of at least 3 characters!"
            } else {
                binding.groupInput.error =
                    "This group name is already in use, or didn't meet required criteria!"

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
                            binding.editTextGroupName.text.toString().length < 3
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

    fun setUsedData(data: List<GroupData>) {
        currentUsedDates = data
    }

    fun setOnSaveClickListener(block: (String) -> Unit) {
        listener = block
    }

    fun setOnEditClickListener(block: (String) -> Unit) {
        listenerEdit = block
    }
}