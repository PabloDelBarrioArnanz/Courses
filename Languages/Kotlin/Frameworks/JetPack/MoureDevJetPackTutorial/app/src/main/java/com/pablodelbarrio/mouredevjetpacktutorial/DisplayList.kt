package com.pablodelbarrio.mouredevjetpacktutorial

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pablodelbarrio.mouredevjetpacktutorial.ui.theme.MoureDevJetPackTutorialTheme

class DisplayList : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoureDevJetPackTutorialTheme {
                MyList()
            }
        }
    }
}

@Composable
fun MyList() {

    /*
        Scroll point
        val scroll = rememberScrollState()
        Column(modifier = Modifier.verticalScroll(scroll)) {
            ..
        }

        A column paints all the elements inside it, although these may not be visible due to space limitations
        To avoid this we have to use LazyColumn to display only the visible ones (performance improve)
     */
    LazyColumn {
        items(15) {
            MyListItem()
        }
    }
}

@Composable
fun MyListItem() {
    Row(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Image(
            painterResource(id = R.drawable.ic_launcher_foreground),
            "Icon",
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primary)
        )
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = "Hello JetPack Compose!", color = MaterialTheme.colorScheme.primary)
            Text(text = "Are you ready!?", color = MaterialTheme.colorScheme.secondary)
        }
    }
}

@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DisplayListPreview() {
    MoureDevJetPackTutorialTheme {
        MyList()
    }
}