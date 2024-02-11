package com.pablodelbarrio.mouredevjetpacktutorial.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.pablodelbarrio.mouredevjetpacktutorial.navigation.AppScreens


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FirstScreen(navController: NavController) {
    // it's an element to structure item in a screen
    Scaffold(topBar = {
        TopAppBar(title = {
            Text(text = "First Screen")
        })
    }) {
        FirstBodyContent(navController)
    }
}

@Composable
fun FirstBodyContent(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Hi Navigation from screen 1")
        Button(onClick = {
            navController.navigate(route = AppScreens.SecondScreen.route + "/This is a param")
        }) {
            Text(text = "Navigate")
        }
    }
}
