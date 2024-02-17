package com.pablodelbarrio.jetpackcomposedefinitivecourseudemy.part2states

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MyState() {

    /*
        - Mutable state wraps a value and triggers a view refresh when his value it's update
        - Remember makes that part of code don't change between refresh due in each refresh this function
          will be called then that part will restore the last value
    */
    val counter = remember { mutableIntStateOf(0) }

    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Clicked ${counter.intValue} times")
        Row(
            Modifier.height(80.dp).width(400.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = {
                counter.intValue++
            }) {
                Text(text = "Add Click!")
            }
            Button(onClick = {
                counter.intValue--
            }) {
                Text(text = "Decrees Click!")
            }
        }
        Button(onClick = {
            counter.intValue = 0
        }) {
            Text(text = "Reset Click!")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun StatePreview() {
    MyState()
}