package com.pablodelbarrio.todoapp.addtask.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Query("SELECT * FROM TaskEntity")
    fun getTask(): Flow<List<TaskEntity>>

    @Insert
    suspend fun addTask(taskEntity: TaskEntity)

    @Update
    suspend fun updateTaskStatus(item: TaskEntity)

    @Delete
    suspend fun deleteTask(item: TaskEntity)

}