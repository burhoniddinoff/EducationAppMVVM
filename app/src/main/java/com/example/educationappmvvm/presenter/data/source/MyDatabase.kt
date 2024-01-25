package com.example.educationappmvvm.presenter.data.source

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.educationappmvvm.presenter.data.dao.CourseDao
import com.example.educationappmvvm.presenter.data.dao.GroupDao
import com.example.educationappmvvm.presenter.data.model.CourseData
import com.example.educationappmvvm.presenter.data.model.GroupData

@Database(entities = [CourseData::class, GroupData::class], version = 1)
abstract class MyDatabase : RoomDatabase() {

    abstract fun getCourseDao(): CourseDao
    abstract fun getGroupDao(): GroupDao

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