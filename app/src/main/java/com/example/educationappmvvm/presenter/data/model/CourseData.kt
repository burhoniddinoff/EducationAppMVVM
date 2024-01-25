package com.example.educationappmvvm.presenter.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CourseTable")
data class CourseData(
    @PrimaryKey(autoGenerate = true) var id: Long,
    var name: String,
)