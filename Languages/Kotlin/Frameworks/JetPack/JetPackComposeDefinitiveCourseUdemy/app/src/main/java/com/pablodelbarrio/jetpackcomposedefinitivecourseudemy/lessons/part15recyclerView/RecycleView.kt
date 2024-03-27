package com.pablodelbarrio.jetpackcomposedefinitivecourseudemy.lessons.part15recyclerView

import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pablodelbarrio.jetpackcomposedefinitivecourseudemy.R
import kotlinx.coroutines.launch

@Composable
fun SimpleRecycleView() {
    val people = listOf("Pablo", "Ines", "Laura")
    LazyColumn {
        item { Text(text = "Single item") }
        items(7) { Text(text = "Collection item number $it") }
        items(people) { Text(text = "Hi person $it") }
    }
}

@Composable
fun VerticalRecycleView() {
    val context = LocalContext.current
    LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        items(getSuperHeroes()) {
            Column(modifier = Modifier.clickable {
                Toast.makeText(
                    context,
                    it.name,
                    Toast.LENGTH_SHORT
                ).show()
            }) {
                Row {
                    Text(text = it.name)
                    Text(text = it.realName)
                    Text(text = it.publisher)
                }
                Image(painter = painterResource(id = it.photo), contentDescription = "image")
            }
        }
    }
}

@Composable
fun GridRecycleView() {
    val context = LocalContext.current
    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
        items(getSuperHeroes()) {
            Column(modifier = Modifier.clickable {
                Toast.makeText(
                    context,
                    it.name,
                    Toast.LENGTH_SHORT
                ).show()
            }) {
                Row {
                    Text(text = it.name)
                    Text(text = it.realName)
                    Text(text = it.publisher)
                }
                Image(painter = painterResource(id = it.photo), contentDescription = "image")
            }
        }
    }
}

@Composable
fun VerticalRecycleScrollControlView() {
    val context = LocalContext.current
    val rvState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    Column(Modifier.fillMaxWidth()) {
        LazyColumn(
            state = rvState,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterHorizontally)
        ) {
            items(getSuperHeroes()) {
                Column(modifier = Modifier.clickable {
                    Toast.makeText(
                        context,
                        it.name,
                        Toast.LENGTH_SHORT
                    ).show()
                }) {
                    Row {
                        Text(text = it.name)
                        Text(text = it.realName)
                        Text(text = it.publisher)
                    }
                    Image(
                        painter = painterResource(id = it.photo),
                        contentDescription = "image",
                        modifier = Modifier.size(200.dp)
                    )
                }
            }
        }

        // Show only the button when view is not full top
        val showButton by remember {
            derivedStateOf { rvState.firstVisibleItemIndex > 0 }
        }

        if (showButton) {
            Button(
                onClick = { coroutineScope.launch { rvState.animateScrollToItem(0) } },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(text = "Go top")
            }
        }
    }
}

fun getSuperHeroes(): List<SuperHero> {
    return listOf(
        SuperHero("Spiderman", "Peter Parker", "Marvel", R.drawable.spiderman),
        SuperHero("Wolverine", "James Howlett", "Marvel", R.drawable.logan),
        SuperHero("Batman", "Bruce Banner", "DC", R.drawable.batman),
        SuperHero("Thor", "Thor Odinson", "Marvel", R.drawable.thor),
        SuperHero("Green Lantern", "Alan Scott", "DC", R.drawable.green_lantern),
        SuperHero("Wonder woman", "Diana", "Marvel", R.drawable.wonder_woman),
    )
}

data class SuperHero(
    var name: String,
    val realName: String,
    var publisher: String,
    @DrawableRes var photo: Int
)

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun RecycleViewPreview() {
    VerticalRecycleScrollControlView()
}
