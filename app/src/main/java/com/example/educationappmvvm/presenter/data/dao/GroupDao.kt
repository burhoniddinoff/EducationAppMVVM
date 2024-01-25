package com.example.educationappmvvm.presenter.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.educationappmvvm.presenter.data.model.GroupData

@Dao
interface GroupDao {

    @Query("SELECT * FROM groupTable")
    fun getAllGroup(): List<GroupData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGroup(data: GroupData)

    @Update
    fun updateGroup(data: GroupData)

    @Delete
    fun deleteGroup(data: GroupData)


}