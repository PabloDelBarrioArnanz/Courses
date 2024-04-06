package com.pablodelbarrio.todoapp.addtask.domain

import com.pablodelbarrio.todoapp.addtask.data.TaskRepository
import com.pablodelbarrio.todoapp.addtask.ui.model.TaskModel
import javax.inject.Inject

class DeleteTaskUseCase @Inject constructor(private val taskRepository: TaskRepository) {

    suspend operator fun invoke(taskModel: TaskModel) = taskRepository.deleteTask(taskModel)

}