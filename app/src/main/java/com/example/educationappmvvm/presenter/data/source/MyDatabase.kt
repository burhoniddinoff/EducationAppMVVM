package com.example.educationappmvvm.presenter.data.source

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.educationappmvvm.presenter.data.dao.CourseDao
import com.example.educationappmvvm.presenter.data.model.CourseData

@Database(entities = [CourseData::class], version = 1)
abstract class MyDatabase : RoomDatabase() {

    abstract fun getCourseDao(): CourseDao

    companion object {
        private lateinit var instance: MyDatabase

        fun init(context: Context) {
            if (!(::instance.isInitialized)) instance =
                Room.databaseBuilder(context, MyDatabase::class.java, "educationmvvm.db")
                    .allowMainThreadQueries().build()
        }

        fun getInstance(): MyDatabase = instance
    }

}