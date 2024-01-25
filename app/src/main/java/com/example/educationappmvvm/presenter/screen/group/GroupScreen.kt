package com.example.educationappmvvm.presenter.screen.group

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
import androidx.navigation.fragment.navArgs
import com.example.educationappmvvm.databinding.ScreenGroupBinding
import com.example.educationappmvvm.presenter.data.model.GroupData
import com.example.educationappmvvm.presenter.screen.adapter.GroupAdapter
import com.example.educationappmvvm.presenter.screen.add.AddGroupScreen
import com.example.educationappmvvm.presenter.utils.replaceScreen

class GroupScreen : Fragment(R.layout.screen_group) {
    private val binding by viewBinding(ScreenGroupBinding::bind)
    private val viewModel = GroupViewModel()
    private val adapter by lazy { GroupAdapter() }
    private val navController by lazy(LazyThreadSafetyMode.NONE) { findNavController() }
    private val navArgs: GroupScreenArgs by navArgs()
    private var courseId = 0L

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        courseId = navArgs.courseId
        binding.lsGroup.adapter = adapter

        viewModel.group.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        viewModel.isEmpty.observe(viewLifecycleOwner) {
            binding.textik.isVisible = !it
            binding.containerEmptyState.isVisible = it
        }

        binding.buttonAdd.setOnClickListener {
            addGroup()
        }

        binding.imageBack.setOnClickListener {
            navController.popBackStack()
        }


        adapter.onClickListener = {
            navController.navigate(GroupScreenDirections.actionGroupScreenToAttendanceScreen(it.id))
        }

        adapter.onLongClickListener = {
            showDialog(it)
        }

        viewModel.loadGroup()


    }

    private fun addGroup() {
        val addDialog = AddGroupScreen()

//        addDialog.setUsedData(presenter.getAllGroupsByCourseId(courseId)) // Pass existing groups to avoid duplicates

        addDialog.setOnSaveClickListener { groupName ->
            viewModel.addGroup(GroupData(0, groupName, courseId))
//            viewModel.allGroup(groupName, courseId)
//            presenter.insertPosData(PositionData(0, 0, calendar))
        }

        replaceScreen(addDialog)
    }

    private fun showDialog(data: GroupData) {
        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.bottom_sheet)

        dialog.findViewById<ImageView>(R.id.dialog_delete).setOnClickListener {
            viewModel.deleteGroup(data)
            dialog.dismiss()
        }

        dialog.findViewById<ImageView>(R.id.dialog_edit).setOnClickListener {

            val titleDialog = AddGroupScreen()

            titleDialog.setEditableData(data.name)
//            titleDialog.setUsedData(presenter.getAllGroupsByCourseId(courseId))
            titleDialog.setOnEditClickListener { name ->
                viewModel.updateGroup(GroupData(data.id, name, courseId))
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