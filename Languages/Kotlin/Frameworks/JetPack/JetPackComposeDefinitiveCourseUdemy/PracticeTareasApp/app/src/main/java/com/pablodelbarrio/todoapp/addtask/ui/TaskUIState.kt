package com.pablodelbarrio.todoapp.addtask.ui

import com.pablodelbarrio.todoapp.addtask.ui.model.TaskModel

sealed interface TaskUIState {

    data object Loading : TaskUIState
    data class Error(val throwable: Throwable) : TaskUIState
    data class Success(val tasks: List<TaskModel>) : TaskUIState

}