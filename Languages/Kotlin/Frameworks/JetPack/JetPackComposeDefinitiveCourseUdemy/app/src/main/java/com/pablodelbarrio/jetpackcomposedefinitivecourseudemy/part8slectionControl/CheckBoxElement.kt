package com.pablodelbarrio.jetpackcomposedefinitivecourseudemy.part8slectionControl

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MyCheckBox() {
    var state by rememberSaveable { mutableStateOf(true) }

    Checkbox(checked = state, onCheckedChange = { state = !state })
}

@Composable
fun MyAdvanceCheckBox() {
    val options = getOptions(listOf("Option 1", "Option 2", "Option 3"))

    Column(Modifier.padding(8.dp)) {
        options.forEach { CheckBoxWithText(it) }
    }
}

@Composable
fun getOptions(options: List<String>): List<CheckInfo> {
    return options.map {
        var state by rememberSaveable { mutableStateOf(true) }
        CheckInfo(it, state) { newStatus -> state = newStatus}
    }
}

@Composable
private fun CheckBoxWithText(checkInfo: CheckInfo) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(checked = checkInfo.checked, onCheckedChange = {
            checkInfo.onCheckedChange(!checkInfo.checked)
        })
        Text(text = checkInfo.option)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CheckBoxPreview() {
    MyAdvanceCheckBox()
}

data class CheckInfo(
    val option: String,
    var checked: Boolean = false,
    val onCheckedChange: (Boolean) -> Unit
)