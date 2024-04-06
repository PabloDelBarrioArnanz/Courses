package com.pablodelbarrio.todoapp.addtask.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pablodelbarrio.todoapp.addtask.domain.AddTaskUseCase
import com.pablodelbarrio.todoapp.addtask.domain.DeleteTaskUseCase
import com.pablodelbarrio.todoapp.addtask.domain.GetTasksUseCase
import com.pablodelbarrio.todoapp.addtask.domain.UpdateStatusTaskUseCase
import com.pablodelbarrio.todoapp.addtask.ui.TaskUIState.Error
import com.pablodelbarrio.todoapp.addtask.ui.TaskUIState.Loading
import com.pablodelbarrio.todoapp.addtask.ui.TaskUIState.Success
import com.pablodelbarrio.todoapp.addtask.ui.model.TaskModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(
    private val addTaskUseCase: AddTaskUseCase,
    getTasksUseCase: GetTasksUseCase,
    private val updateStatusTaskUseCase: UpdateStatusTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase
) : ViewModel() {

    val uiState: StateFlow<TaskUIState> = getTasksUseCase().map(::Success)
        .catch { Error(it) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), Loading)

    private val _showDialog = MutableLiveData<Boolean>()
    var showDialog: LiveData<Boolean> = _showDialog

    // Better not use MutableLiveData for lists/mutableLists other option it's use Flow from Kotlin
    /*
        private val _tasks = mutableStateListOf<TaskModel>()
        var tasks: List<TaskModel> = _tasks
     */

    fun onDialogClose() {
        _showDialog.value = false
    }

    fun onShowDialog() {
        _showDialog.value = true
    }

    fun onTaskCreated(newTask: String) {
        _showDialog.value = false
        // _tasks.add(TaskModel(description = newTask))

        viewModelScope.launch {
            addTaskUseCase(TaskModel(description = newTask))
        }
    }

    fun onCheckTask(task: TaskModel) {
        // val index = _tasks.indexOfFirst { it.id == task.id }
        // _tasks[index] = _tasks[index].let { it.copy(completed = !it.completed) }
        viewModelScope.launch {
            updateStatusTaskUseCase(task.copy(completed = !task.completed))
        }
    }

    fun onItemRemove(task: TaskModel) {
        // val index = _tasks.indexOfFirst { it.id == task.id }
        // _tasks.removeAt(index)
        viewModelScope.launch {
            deleteTaskUseCase(task)
        }
    }

}