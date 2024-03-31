package com.pablodelbarrio.todoapp.addtask.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.pablodelbarrio.todoapp.addtask.ui.model.TaskModel


@Composable
fun TasksScreen(tasksViewModel: TasksViewModel) {

    val showDialog by tasksViewModel.showDialog.observeAsState(initial = false)

    Box(Modifier.fillMaxSize()) {
        AddTasksDialog(
            show = showDialog,
            onTaskAdd = { tasksViewModel.onTaskCreated(it) },
            onDismiss = { tasksViewModel.onDialogClose() })
        FabDialog(Modifier.align(Alignment.BottomEnd)) {
            tasksViewModel.onShowDialog()
        }
        TaskList(tasksViewModel)
    }
}

@Composable
private fun FabDialog(modifier: Modifier, onShowDialog: () -> Unit) {
    FloatingActionButton(
        modifier = modifier.padding(16.dp),
        onClick = {
            onShowDialog()
        }) {
        Icon(Icons.Filled.Add, contentDescription = "Add new Task")
    }
}

@Composable
private fun AddTasksDialog(show: Boolean, onTaskAdd: (String) -> Unit, onDismiss: () -> Unit) {
    var newTask by remember { mutableStateOf("") }

    if (show) {
        Dialog(onDismissRequest = { onDismiss() }) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(16.dp)
            ) {
                Text(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    text = "Add new task", fontSize = 16.sp, fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.size(16.dp))
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = newTask,
                    onValueChange = { newTask = it })
                Spacer(Modifier.size(16.dp))
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    enabled = newTask.isNotBlank(),
                    onClick = {
                        onTaskAdd(newTask)
                        newTask = ""
                    }) {
                    Text(text = "Save task")
                }
            }
        }
    }
}

@Composable
private fun TaskList(tasksViewModel: TasksViewModel) {
    val tasks = tasksViewModel.tasks

    LazyColumn {
        items(tasks, key = { it.id }) { task ->
            TaskComponent(
                task,
                { tasksViewModel.onCheckTask(it) },
                { tasksViewModel.onItemRemove(it) })
        }
    }
}

@Composable
private fun TaskComponent(
    task: TaskModel,
    onTaskComplete: (TaskModel) -> Unit,
    onItemRemove: (TaskModel) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .pointerInput(Unit) {
                detectTapGestures(
                    onLongPress = { onItemRemove(task) },
                    onDoubleTap = { onTaskComplete(task) })
            }
    ) {
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Text(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 4.dp),
                style = if (task.completed) TextStyle(textDecoration = TextDecoration.LineThrough) else TextStyle(),
                text = task.description
            )
            Checkbox(
                checked = task.completed,
                onCheckedChange = { onTaskComplete(task) })
        }
    }
}