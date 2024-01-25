package com.example.educationappmvvm.presenter.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.educationappmvvm.presenter.data.model.CourseData

@Dao
interface CourseDao {

    @Query("SELECT * FROM coursetable")
    fun getAllCourse(): List<CourseData>

}