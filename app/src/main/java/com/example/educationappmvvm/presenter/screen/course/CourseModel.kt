package com.example.educationappmvvm.presenter.screen.course

import com.example.educationappmvvm.presenter.data.model.CourseData
import com.example.educationappmvvm.presenter.domain.AppRepository
import com.example.educationappmvvm.presenter.domain.AppRepositoryImpl

class CourseModel {

    private var repository: AppRepository = AppRepositoryImpl.getInstance()

    fun allCourse(): List<CourseData> = repository.allCourse()
    fun addCourse(data: CourseData) = repository.addCourse(data)
    fun updateCourse(data: CourseData) = repository.updateCourse(data)
    fun deleteCourse(data: CourseData) = repository.deleteCourse(data)


}