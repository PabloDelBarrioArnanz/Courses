package com.pablodelbarrio.todoapp.addtask.ui.model

data class TaskModel(
    val id: Long = System.nanoTime(),
    val description: String,
    var completed: Boolean = false
)
