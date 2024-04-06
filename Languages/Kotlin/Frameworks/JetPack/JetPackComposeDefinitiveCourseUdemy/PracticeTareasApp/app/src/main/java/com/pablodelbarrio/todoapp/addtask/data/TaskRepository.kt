package com.pablodelbarrio.todoapp.addtask.data

import com.pablodelbarrio.todoapp.addtask.ui.model.TaskModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskRepository @Inject constructor(private val taskDao: TaskDao) {

    val tasks: Flow<List<TaskModel>> = taskDao.getTask().map { taskEntityList ->
        taskEntityList.map { TaskModel(it.id, it.description, it.completed) }
    }

    suspend fun addTask(taskModel: TaskModel) {
        taskDao.addTask(TaskEntity(taskModel.id, taskModel.description, taskModel.completed))
    }

}