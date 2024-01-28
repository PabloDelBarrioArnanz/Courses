package com.pablodelbarrio.mouredevjetpacktutorial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pablodelbarrio.mouredevjetpacktutorial.ui.theme.MoureDevJetPackTutorialTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { // Activity block all in this block will be displayed
            MoureDevJetPackTutorialTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable // Elements with Composable can be used in JetPack
fun Greeting(name: String, modifier: Modifier = Modifier) {
    // If we don't use a components grouper all of them will be displayed in the same position
    Column( // Column it's similar to VStack
        // Properties for
        modifier = Modifier.padding(16.dp).fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(R.drawable.avatar_icon), contentDescription = "Avatar photo")
        Text(text = "Hello I'm $name!", modifier = modifier)
        Text(text = "I'm Software developer")
    }
}

/*
    Preview it's similar to SwiftUI preview, invokes the class with given parameters to test the view
    It's possible to have more than 1 to preview the view with different data
    Also exist a layout inspector to inspect components and their props
*/
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MoureDevJetPackTutorialTheme {
        Greeting("Pablo")
    }
}