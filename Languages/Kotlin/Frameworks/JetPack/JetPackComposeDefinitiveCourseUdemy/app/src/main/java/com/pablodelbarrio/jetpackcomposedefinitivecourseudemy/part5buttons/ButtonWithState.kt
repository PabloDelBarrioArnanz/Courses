package com.pablodelbarrio.jetpackcomposedefinitivecourseudemy.part5buttons

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun MyButtonWithState() {
    var enabledFirst by rememberSaveable { mutableStateOf(true) }
    var enabledSecond by rememberSaveable { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Button(
            onClick = {
                enabledFirst = !enabledFirst
                enabledSecond = !enabledSecond
            },
            enabled = enabledFirst
        ) {
            Text(text = "Hi")
        }
        Button(
            onClick = {
                enabledFirst = !enabledFirst
                enabledSecond = !enabledSecond
            },
            enabled = enabledSecond
        ) {
            Text(text = "Hi")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ButtonWithStatePreview() {
    MyButtonWithState()
}