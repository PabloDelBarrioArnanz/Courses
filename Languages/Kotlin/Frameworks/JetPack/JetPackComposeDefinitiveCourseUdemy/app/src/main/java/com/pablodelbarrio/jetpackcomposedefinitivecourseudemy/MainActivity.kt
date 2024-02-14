package com.pablodelbarrio.jetpackcomposedefinitivecourseudemy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.pablodelbarrio.jetpackcomposedefinitivecourseudemy.ui.theme.JetPackComposeDefinitiveCourseUdemyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetPackComposeDefinitiveCourseUdemyTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        // With the modifier we can change any component properties, like height, color, clickable behaviour...
        modifier = modifier
            .background(Color.Gray)
            .fillMaxSize()
            .padding()
            .clickable {

            }
    )
}

/*
    Limitations the preview function can't receive parameters
    We can have multiple previews to
    The preview can receive many parameters like show background, showSystemUi, device to test...
*/
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    JetPackComposeDefinitiveCourseUdemyTheme {
        Greeting("Android")
    }
}