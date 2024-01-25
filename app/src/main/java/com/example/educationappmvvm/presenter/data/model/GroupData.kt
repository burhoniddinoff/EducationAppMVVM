package com.example.educationappmvvm.presenter.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(tableName = "groupTable", foreignKeys = [
    ForeignKey(
        entity = CourseData::class,
        parentColumns = ["id"],
        childColumns = ["courseId"],
        onDelete = ForeignKey.CASCADE
    )
])
class GroupData(
    @PrimaryKey(autoGenerate = true) var id: Long,
    val name: String,
    val courseId: Long,
)
