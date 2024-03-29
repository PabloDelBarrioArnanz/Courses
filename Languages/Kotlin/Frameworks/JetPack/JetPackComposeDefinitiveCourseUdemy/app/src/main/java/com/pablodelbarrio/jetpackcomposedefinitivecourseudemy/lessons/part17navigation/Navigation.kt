package com.pablodelbarrio.jetpackcomposedefinitivecourseudemy.lessons.part17navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun Screen1(navigationController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Cyan)
    ) {
        Column(modifier = Modifier.align(Alignment.Center)) {
            Text(text = "Screen 1")
            Button(onClick = { navigationController.navigate(Routes.Screen2.route) }) {
                Text(text = "Navigate to screen 2")
            }
            Button(onClick = { navigationController.navigate(Routes.Screen3.route) }) {
                Text(text = "Navigate to screen 3")
            }
        }
    }
}

@Composable
fun Screen2(navigationController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Green)
    ) {
        Column(modifier = Modifier.align(Alignment.Center)) {
            Text(text = "Screen 2")
            Button(onClick = { navigationController.navigate(Routes.Screen1.route) }) {
                Text(text = "Navigate to screen 1")
            }
            Button(onClick = { navigationController.navigate(Routes.Screen3.route) }) {
                Text(text = "Navigate to screen 3")
            }
        }
    }
}

@Composable
fun Screen3(navigationController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Yellow)
    ) {
        Column(modifier = Modifier.align(Alignment.Center)) {
            Text(text = "Screen 3")
            Button(onClick = { navigationController.navigate(Routes.Screen1.route) }) {
                Text(text = "Navigate to screen 1")
            }
            Button(onClick = { navigationController.navigate(Routes.Screen2.route) }) {
                Text(text = "Navigate to screen 2")
            }
            Button(onClick = { navigationController.navigate(Routes.Screen4.createRoute("Pablo")) }) {
                Text(text = "Navigate to screen 4")
            }
            Button(onClick = { navigationController.navigate(Routes.Screen5.createRoute(27)) }) {
                Text(text = "Navigate to screen 5")
            }
        }
    }
}

@Composable
fun Screen4(navigationController: NavHostController, name: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Red)
    ) {
        Column(modifier = Modifier.align(Alignment.Center)) {
            Text(text = "Screen 4 Received $name")
            Button(onClick = { navigationController.navigate(Routes.Screen1.route) }) {
                Text(text = "Navigate to screen 1")
            }
            Button(onClick = { navigationController.navigate(Routes.Screen2.route) }) {
                Text(text = "Navigate to screen 2")
            }
            Button(onClick = { navigationController.navigate(Routes.Screen3.route) }) {
                Text(text = "Navigate to screen 3")
            }
        }
    }
}

@Composable
fun Screen5(navigationController: NavHostController, number: Int) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Red)
    ) {
        Column(modifier = Modifier.align(Alignment.Center)) {
            Text(text = "Screen 5 Received? $number")
            Button(onClick = { navigationController.navigate(Routes.Screen1.route) }) {
                Text(text = "Navigate to screen 1")
            }
            Button(onClick = { navigationController.navigate(Routes.Screen2.route) }) {
                Text(text = "Navigate to screen 2")
            }
            Button(onClick = { navigationController.navigate(Routes.Screen3.route) }) {
                Text(text = "Navigate to screen 3")
            }
        }
    }
}

@Composable
fun NavController() {
    val navigationController = rememberNavController()
    NavHost(navController = navigationController, startDestination = Routes.Screen1.route) {
        composable(Routes.Screen1.route) { Screen1(navigationController) }
        composable(Routes.Screen2.route) { Screen2(navigationController) }
        composable(Routes.Screen3.route) { Screen3(navigationController) }
        composable(
            Routes.Screen4.route,
            arguments = listOf(navArgument("name") { type = NavType.StringType })
        ) { navBackStackEntry ->
            Screen4(
                navigationController,
                navBackStackEntry.arguments?.getString("name")!!
            )
        }
        composable(
            Routes.Screen5.route,
            arguments = listOf(navArgument("number") { defaultValue = 0 })
        ) { navBackStackEntry ->
            Screen5(
                navigationController,
                navBackStackEntry.arguments?.getInt("number")!!
            )
        }
    }
}

sealed class Routes(val route: String) {
    data object Screen1 : Routes("screen1")
    data object Screen2 : Routes("screen2")
    data object Screen3 : Routes("screen3")
    data object Screen4 : Routes("screen4/{name}") {
        fun createRoute(name: String) = "screen4/$name"
    }

    data object Screen5 : Routes("screen5?number={number}") {
        fun createRoute(number: Int) = "screen5?number=$number"
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun NavigationPreview() {
    NavController()
}