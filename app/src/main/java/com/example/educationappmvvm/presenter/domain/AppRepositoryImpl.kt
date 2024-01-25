package com.example.educationappmvvm.presenter.domain

import com.example.educationappmvvm.presenter.data.model.CourseData
import com.example.educationappmvvm.presenter.data.source.MyDatabase

class AppRepositoryImpl : AppRepository {

    companion object {

        private lateinit var instance: AppRepository

        fun getInstance(): AppRepository {
            if (!(Companion::instance.isInitialized)) instance = AppRepositoryImpl()

            return instance
        }

    }

    private var db = MyDatabase.getInstance()
    private var getCourseDao = db.getCourseDao()

    override fun allCourse(): List<CourseData> = getCourseDao.getAllCourse()

    override fun addCourse(data: CourseData) = getCourseDao.insertCourse(data)

    override fun deleteCourse(data: CourseData) = getCourseDao.deleteCourse(data)


}