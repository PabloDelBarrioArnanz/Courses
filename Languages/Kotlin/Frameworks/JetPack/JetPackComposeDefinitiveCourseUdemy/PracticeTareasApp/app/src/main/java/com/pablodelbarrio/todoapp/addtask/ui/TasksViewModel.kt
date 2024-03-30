package com.pablodelbarrio.todoapp.addtask.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pablodelbarrio.todoapp.addtask.ui.model.TaskModel
import javax.inject.Inject

class TasksViewModel @Inject constructor() : ViewModel() {

    private val _showDialog = MutableLiveData<Boolean>()
    var showDialog: LiveData<Boolean> = _showDialog

    // better not use MutableLiveData for lists/mutableLists other option it's use Flow from Kotlin
    private val _tasks = mutableStateListOf<TaskModel>()
    var tasks: List<TaskModel> = _tasks

    fun onDialogClose() {
        _showDialog.value = false
    }

    fun onShowDialog() {
        _showDialog.value = true
    }

    fun onTaskCreated(newTask: String) {
        _showDialog.value = false
        _tasks.add(TaskModel(description = newTask))
    }

    fun onCheckTask(taskId: Long) {

    }

}