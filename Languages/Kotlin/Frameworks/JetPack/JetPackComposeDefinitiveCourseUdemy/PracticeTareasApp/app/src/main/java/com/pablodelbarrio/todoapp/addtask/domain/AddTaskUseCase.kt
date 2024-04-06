package com.pablodelbarrio.todoapp.addtask.domain

import com.pablodelbarrio.todoapp.addtask.data.TaskRepository
import com.pablodelbarrio.todoapp.addtask.ui.model.TaskModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AddTaskUseCase @Inject constructor(private val taskRepository: TaskRepository) {

    suspend operator fun invoke(taskModel: TaskModel) = taskRepository.addTask(taskModel)

}