package com.pablodelbarrio.jetpackcomposedefinitivecourseudemy.part3components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MyText() {
    Column(Modifier.fillMaxSize()) {

        Text(text = "Simple text component")
        Text(text = "Text with color", color = Color.Blue)
        Text(text = "Text with font weight", fontWeight = FontWeight.ExtraBold)
        Text(text = "Text with font style", fontFamily = FontFamily.Cursive)
        Text(
            text = "Text with decorator",
            textDecoration = TextDecoration.LineThrough
        )
        Text(
            text = "Text with many decorator",
            // Accessing to all style object
            style = TextStyle(
                textDecoration = TextDecoration.combine(
                    listOf(
                        TextDecoration.LineThrough,
                        TextDecoration.Underline
                    )
                )
            )
        )
        Text(text = "Text with size", fontSize = 20.sp)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TextPreview() {
    MyText()
}