package com.pablodelbarrio.jetpackcomposedefinitivecourseudemy.lessons.part3components

import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun MyTextField() {
    var myText by remember { mutableStateOf("") }
    TextField(value = myText, onValueChange = { myText = it })
}

@Composable
fun MyTextFieldAdvance() {
    var myText by remember { mutableStateOf("") }
    TextField(value = myText, onValueChange = {
        myText = if (it.length < 10)
            it else it.substring(0, 9)
    }, label = {
        // Placeholder
        Text(text = "Write your name")
    })
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TextFieldPreview() {
    // MyTextField()
    MyTextFieldAdvance()
}