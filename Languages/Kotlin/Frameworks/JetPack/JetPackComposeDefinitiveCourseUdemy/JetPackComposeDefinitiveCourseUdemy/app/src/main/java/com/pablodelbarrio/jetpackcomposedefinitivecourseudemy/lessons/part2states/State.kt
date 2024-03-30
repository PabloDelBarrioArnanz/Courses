package com.pablodelbarrio.jetpackcomposedefinitivecourseudemy.lessons.part2states

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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

        - Be careful with the life cycle of the Android app, when the app it's refreshing it self remember works
          but when the app it's destroy and recreated from zero remember can't do nothing we need to use rememberSaveable
          (for example when the phone rotates the screen the complete activity will be destroyed)
    */

    // val counter = remember { mutableIntStateOf(0) }
    // val counter = rememberSaveable { mutableIntStateOf(0) }

    // by makes initialize the state as = but makes his value accessible directly with no counter.intValue...
    // Needs to be var
    var counter by rememberSaveable { mutableIntStateOf(0) }

    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Clicked $counter times")
        Row(
            Modifier
                .height(80.dp)
                .width(400.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = {
                // counter.intValue++
                counter = counter.inc()
            }) {
                Text(text = "Add Click!")
            }
            Button(onClick = {
                // counter.intValue--
                counter = counter.dec()
            }) {
                Text(text = "Decrees Click!")
            }
        }
        Button(onClick = {
            // counter.intValue = 0
            counter = counter.minus(counter)
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