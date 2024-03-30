package com.pablodelbarrio.jetpackcomposedefinitivecourseudemy.lessons.part5buttons

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun MyButton() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Button(
            onClick = { Log.i("Info", "This is a log example") },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Blue,
                contentColor = Color.White,
                disabledContainerColor = Color.White,
                disabledContentColor = Color.Blue
            ),
            border = BorderStroke(2.dp, Color.Gray)
        ) {
            // The lambda inside of the button it's a row scope where we can place elements
            Text(text = "Hi ")
            Text(text = "Pablo!")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ButtonPreview() {
    MyButton()
}