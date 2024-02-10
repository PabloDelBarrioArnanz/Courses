package com.pablodelbarrio.mouredevjetpacktutorial

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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

    /*
        A normal var when it's update the jetpack compose doesn't watch it
        to indicate it's a variable witch his value can change an make the ui refresh we have to init it like this
    */
    var expandedRow by remember { mutableStateOf(false) }

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
            modifier = Modifier
                .padding(8.dp)
                .clickable {
                    expandedRow = !expandedRow
                }
        ) {
            Text(text = "Hello JetPack Compose!", color = MaterialTheme.colorScheme.primary)
            Text(
                text = "Are you ready!? Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book".trimMargin(),
                color = MaterialTheme.colorScheme.secondary,
                maxLines = if (expandedRow) Integer.MAX_VALUE else 1 // max lines displayed
            )
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