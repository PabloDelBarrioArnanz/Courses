package com.pablodelbarrio.jetpackcomposedefinitivecourseudemy.lessons.part7progressbar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MyAutomaticProgressBar() {
    Column(
        Modifier
            .padding(24.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(color = Color.Red, strokeWidth = 10.dp)
        LinearProgressIndicator(color = Color.Black, trackColor = Color.Green)
    }
}

@Composable
fun MyManualProgressBar() {
    var progress by rememberSaveable { mutableFloatStateOf(0f) }
    Column(
        Modifier
            .padding(24.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(progress = progress)
        LinearProgressIndicator(progress = progress)
        Text(text = "The progress is ${progress * 100}")
        Button(onClick = { if (progress <= 1f) progress += 0.1f else progress = 0f }) {
            Text(text = "Load faster!")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProcessBarPreview() {
    MyManualProgressBar()
}