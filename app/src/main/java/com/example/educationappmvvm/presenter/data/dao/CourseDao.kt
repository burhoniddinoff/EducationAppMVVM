package com.example.educationappmvvm.presenter.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.educationappmvvm.presenter.data.model.CourseData

@Dao
interface CourseDao {

    @Query("SELECT * FROM coursetable")
    fun getAllCourse(): List<CourseData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCourse(data: CourseData)

    @Update
    fun updateCourse(data: CourseData)

    @Delete
    fun deleteCourse(data: CourseData)



}