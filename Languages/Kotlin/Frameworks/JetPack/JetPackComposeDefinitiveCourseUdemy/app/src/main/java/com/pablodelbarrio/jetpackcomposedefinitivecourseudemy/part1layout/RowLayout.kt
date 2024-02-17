package com.pablodelbarrio.jetpackcomposedefinitivecourseudemy.part1layout

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MyRow() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Text 1", modifier = Modifier
                .background(Color.Green)
                .width(100.dp)
        )
        Text(
            text = "Text 2", modifier = Modifier
                .background(Color.Red)
                .width(100.dp)
        )
        Text(
            text = "Text 3", modifier = Modifier
                .background(Color.Blue)
                .width(100.dp)
        )
        Text(
            text = "Text 1", modifier = Modifier
                .background(Color.Green)
                .width(100.dp)
        )
        Text(
            text = "Text 2", modifier = Modifier
                .background(Color.Red)
                .width(100.dp)
        )
        Text(
            text = "Text 3", modifier = Modifier
                .background(Color.Blue)
                .width(100.dp)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RowPreview() {
    MyRow()
}