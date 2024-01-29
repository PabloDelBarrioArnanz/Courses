package com.pablodelbarrio.mouredevjetpacktutorial

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pablodelbarrio.mouredevjetpacktutorial.ui.theme.MoureDevJetPackTutorialTheme

class Modifiers : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            /*
                The theme of the app, it's defined in the ui.theme folder
                With this view hierarchy we can use theme elements in our view
                In the theme are defined the set of colors, fonts... for any view
                It's built by default when we create the project but it's configurable
            */
            MoureDevJetPackTutorialTheme {
                ComponentWithModifiers()
            }
        }
    }
}

@Composable
fun ComponentWithModifiers() {
    Row(
        /*
            Adding modifiers we can set the component to to our case
            Any modifier it's a function witch returns the modifier
            then we can chain all modifiers like a builder
            Many of this modifiers are common for all components
            Warning, the order of the modifier affect the final result
        */
        modifier = Modifier
            .padding(8.dp)
            .background(MaterialTheme.colorScheme.onBackground) // Our app theme it's a child of Material then we can use his colors
    ) {
        Text(text = "Left text", color = MaterialTheme.colorScheme.primary, style = MaterialTheme.typography.displayLarge)
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = "Right text")
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO) // Enable or disable the dark mode
@Composable
fun ModifiersPreview() {
    MoureDevJetPackTutorialTheme {
        ComponentWithModifiers()
    }
}