package com.example.educationappmvvm.presenter.screen.group

import androidx.fragment.app.Fragment
import com.example.educationappmvvm.R
import com.example.educationappmvvm.presenter.data.model.CourseData
import com.example.educationappmvvm.presenter.data.model.GroupData
import com.example.educationappmvvm.presenter.domain.AppRepository
import com.example.educationappmvvm.presenter.domain.AppRepositoryImpl

class GroupModel {

    private var repository: AppRepository = AppRepositoryImpl.getInstance()

    fun allGroup(): List<GroupData> = repository.allGroup()
    fun addGroup(data: GroupData) = repository.addGroup(data)
    fun updateGroup(data: GroupData) = repository.updateGroup(data)
    fun deleteGroup(data: GroupData) = repository.deleteGroup(data)

}