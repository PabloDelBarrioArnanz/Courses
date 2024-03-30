package com.pablodelbarrio.jetpackcomposedefinitivecourseudemy.lessons.part8slectionControl

import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun MySwitch() {
    var state by rememberSaveable { mutableStateOf(true) }

    Switch(
        checked = state, onCheckedChange = { state = !state }, colors = SwitchDefaults.colors(
            uncheckedThumbColor = Color.Red,
            checkedThumbColor = Color.Green,
            uncheckedTrackColor = Color.Magenta,
            checkedTrackColor = Color.Cyan,
            uncheckedBorderColor = Color.Red
            // ...
        )
    )
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SwitchPreview() {
    MySwitch()
}