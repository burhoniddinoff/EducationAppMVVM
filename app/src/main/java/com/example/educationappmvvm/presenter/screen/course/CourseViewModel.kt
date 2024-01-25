package com.example.educationappmvvm.presenter.screen.course

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.educationappmvvm.presenter.data.model.CourseData

class CourseViewModel {

    private var model: CourseModel = CourseModel()

    private val _course = MutableLiveData<List<CourseData>>()
    val course: LiveData<List<CourseData>> get() = _course

    private val _isEmpty = MutableLiveData<Boolean>()
    val isEmpty: LiveData<Boolean> get() = _isEmpty

    init {
        loadCourse()
    }

    fun loadCourse() {
        val ls = model.allCourse()
        _isEmpty.value = ls.isEmpty()
        _course.value = ls
    }

    fun addCourse(data: CourseData) {
        model.addCourse(data)
        loadCourse()
    }

    fun updateCourse(data: CourseData) {
        model.updateCourse(data)
        loadCourse()
    }

    fun deleteCourse(data: CourseData) {
        model.deleteCourse(data)
        loadCourse()
    }

}