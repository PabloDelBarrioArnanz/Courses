package com.pablodelbarrio.jetpackcomposedefinitivecourseudemy.part5buttons

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

// Also it's possible to make a text clickable
@Composable
fun MyButtonText() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        TextButton(onClick = {}) {
            Text(text = "Hi")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ButtonTextPreview() {
    MyButtonText()
}