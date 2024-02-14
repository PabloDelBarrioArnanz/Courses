package com.pablodelbarrio.jetpackcomposedefinitivecourseudemy.part1components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MyColumn() {
    /*
        If we don't use components all item will be displayed in the same position
        Text(text = "Text 1")
        Text(text = "Text 2")
        Text(text = "Text 3")

        Weight it's a way to split all the space of the component with a weighted value
        For example if the component has 100px if we have 3 children with a weight of 2, 1, 1
        The first one will fit the half of the space and the other two a quarter each

        verticalArrangement will distribute the elements in the component with the pattern choose

        If the component it's smaller than all children we can use scroll
    */
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Text 1", modifier = Modifier
                .background(Color.Red)
                .fillMaxWidth()
                .height(100.dp)
            //.weight(1f)
        )
        Text(
            text = "Text 2", modifier = Modifier
                .background(Color.Black)
                .fillMaxWidth()
                .height(100.dp)
            //.weight(2f)
        )
        Text(
            text = "Text 3", modifier = Modifier
                .background(Color.Cyan)
                .fillMaxWidth()
                .height(100.dp)
            //.weight(1f)
        )
        Text(
            text = "Text 4", modifier = Modifier
                .background(Color.Blue)
                .fillMaxWidth()
                .height(100.dp)
            //.weight(1f)
        )
        Text(
            text = "Text 1", modifier = Modifier
                .background(Color.Red)
                .fillMaxWidth()
                .height(100.dp)
            //.weight(1f)
        )
        Text(
            text = "Text 2", modifier = Modifier
                .background(Color.Black)
                .fillMaxWidth()
                .height(100.dp)
            //.weight(2f)
        )
        Text(
            text = "Text 3", modifier = Modifier
                .background(Color.Cyan)
                .fillMaxWidth()
                .height(100.dp)
            //.weight(1f)
        )
        Text(
            text = "Text 4", modifier = Modifier
                .background(Color.Blue)
                .fillMaxWidth()
                .height(100.dp)
            //.weight(1f)
        )
        Text(
            text = "Text 1", modifier = Modifier
                .background(Color.Red)
                .fillMaxWidth()
                .height(100.dp)
            //.weight(1f)
        )
        Text(
            text = "Text 2", modifier = Modifier
                .background(Color.Black)
                .fillMaxWidth()
                .height(100.dp)
            //.weight(2f)
        )
        Text(
            text = "Text 3", modifier = Modifier
                .background(Color.Cyan)
                .fillMaxWidth()
                .height(100.dp)
            //.weight(1f)
        )
        Text(
            text = "Text 4", modifier = Modifier
                .background(Color.Blue)
                .fillMaxWidth()
                .height(100.dp)
            //.weight(1f)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ColumnPreview() {
    MyColumn()
}