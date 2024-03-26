package com.pablodelbarrio.jetpackcomposedefinitivecourseudemy.lessons.part1layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MyBox() {
    /*
        A box layout with no size defined will use only the space needed
        If we set a size smaller than content we can use scroll to see all content
        The alignment property only applies for items inside that layout not for children
    */
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Box(
            modifier = Modifier
                .width(50.dp)
                .height(50.dp)
                .background(Color.Cyan)
                .verticalScroll(rememberScrollState()),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "This is a box layout")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun BoxPreview() {
    MyBox()
}