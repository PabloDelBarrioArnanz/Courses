package com.pablodelbarrio.jetpackcomposedefinitivecourseudemy.part4statehoisting


import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview

/*
    The hoisting it's a way to define the states out of the composable and pass to it like parameters,
    that way we can use the state in other components and none of this will be the owner.
    Useful for MVVM architecture
*/

@Composable
fun MyNoHoisting() {
    var myText by remember { mutableStateOf("") }
    OutlinedTextField(value = myText, onValueChange = { myText = it })
}

@Composable
fun MyHoisting(name: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(value = name, onValueChange = { onValueChange(it) })
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Hoisting() {
    var myText by remember { mutableStateOf("") }
    MyHoisting(myText) {
        myText = it
    }
}