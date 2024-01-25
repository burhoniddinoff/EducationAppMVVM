package com.example.educationappmvvm.presenter.domain

import com.example.educationappmvvm.presenter.data.dao.CourseDao
import com.example.educationappmvvm.presenter.data.model.CourseData

interface AppRepository {

    fun allCourse(): List<CourseData>
    fun addCourse(data: CourseData)
    fun deleteCourse(data: CourseData)

}