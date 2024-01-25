package com.example.educationappmvvm.presenter.domain

import com.example.educationappmvvm.presenter.data.model.CourseData
import com.example.educationappmvvm.presenter.data.model.GroupData
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
    private var getGroupDao = db.getGroupDao()

    override fun allCourse(): List<CourseData> = getCourseDao.getAllCourse()
    override fun addCourse(data: CourseData) = getCourseDao.insertCourse(data)
    override fun updateCourse(data: CourseData) = getCourseDao.updateCourse(data)
    override fun deleteCourse(data: CourseData) = getCourseDao.deleteCourse(data)

    override fun allGroup(): List<GroupData> = getGroupDao.getAllGroup()
    override fun addGroup(data: GroupData) = getGroupDao.insertGroup(data)
    override fun updateGroup(data: GroupData) = getGroupDao.updateGroup(data)
    override fun deleteGroup(data: GroupData) = getGroupDao.deleteGroup(data)


}