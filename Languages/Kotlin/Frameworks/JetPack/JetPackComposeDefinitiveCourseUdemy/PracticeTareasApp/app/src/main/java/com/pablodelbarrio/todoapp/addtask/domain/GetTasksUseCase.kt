package com.pablodelbarrio.todoapp.addtask.domain

import com.pablodelbarrio.todoapp.addtask.data.TaskRepository
import com.pablodelbarrio.todoapp.addtask.ui.model.TaskModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTasksUseCase @Inject constructor(private val taskRepository: TaskRepository) {

    operator fun invoke(): Flow<List<TaskModel>> = taskRepository.tasks

}