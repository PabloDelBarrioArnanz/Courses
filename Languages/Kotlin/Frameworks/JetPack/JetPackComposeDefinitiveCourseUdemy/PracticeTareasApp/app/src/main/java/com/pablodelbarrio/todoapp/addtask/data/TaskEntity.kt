package com.pablodelbarrio.todoapp.addtask.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TaskEntity(
    @PrimaryKey val id: Long = System.nanoTime(),
    val description: String,
    var completed: Boolean = false
)
