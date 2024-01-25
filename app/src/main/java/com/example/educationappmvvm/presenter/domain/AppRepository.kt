package com.example.educationappmvvm.presenter.domain

import com.example.educationappmvvm.presenter.data.dao.CourseDao
import com.example.educationappmvvm.presenter.data.model.CourseData
import com.example.educationappmvvm.presenter.data.model.GroupData

interface AppRepository {

    fun allCourse(): List<CourseData>
    fun addCourse(data: CourseData)
    fun updateCourse(data: CourseData)
    fun deleteCourse(data: CourseData)

    fun allGroup(): List<GroupData>
    fun addGroup(data: GroupData)
    fun updateGroup(data: GroupData)
    fun deleteGroup(data: GroupData)


}