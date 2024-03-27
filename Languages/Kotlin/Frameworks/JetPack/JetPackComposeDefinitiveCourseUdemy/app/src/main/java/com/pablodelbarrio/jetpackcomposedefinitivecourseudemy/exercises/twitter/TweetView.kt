package com.pablodelbarrio.jetpackcomposedefinitivecourseudemy.exercises.twitter

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pablodelbarrio.jetpackcomposedefinitivecourseudemy.R

@Composable
fun TweetView() {
    var chatSelected by rememberSaveable { mutableStateOf(false) }
    var rtSelect by rememberSaveable { mutableStateOf(false) }
    var likeSelect by rememberSaveable { mutableStateOf(false) }

    Row(
        Modifier
            .fillMaxWidth()
            .fillMaxSize()
            .background(Color(0xFF161D26))
            .padding(16.dp)
    )
    {
        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = "Avatar",
            modifier = Modifier
                .clip(CircleShape)
                .size(55.dp)
        )
        Column(
            Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(Modifier.fillMaxWidth()) {
                TextTitle("Root", Modifier.padding(end = 8.dp))
                DefaultText("@delbarriopablo", Modifier.padding(end = 8.dp))
                DefaultText("4h", Modifier.padding(end = 8.dp))
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    painter = painterResource(id = R.drawable.ic_dots),
                    contentDescription = "Options",
                    tint = Color.White
                )
            }
            TextBody(Modifier.padding(bottom = 16.dp))
            Image(
                painterResource(id = R.drawable.profile),
                contentDescription = "Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(10)),
                contentScale = ContentScale.Crop
            )
            Row(Modifier.padding(top = 16.dp)) {
                SocialIcon(
                    modifier = Modifier.weight(1f),
                    isSelected = chatSelected,
                    selectedIcon = {
                        Icon(
                            painterResource(id = R.drawable.ic_chat),
                            contentDescription = "",
                            tint = Color(0xFF7E8B98)
                        )
                    },
                    unselectedIcon = {
                        Icon(
                            painterResource(id = R.drawable.ic_chat_filled),
                            contentDescription = "",
                            tint = Color(0xFF7E8B98)
                        )
                    }
                ) { chatSelected = !chatSelected }
                SocialIcon(
                    modifier = Modifier.weight(1f),
                    isSelected = rtSelect,
                    selectedIcon = {
                        Icon(
                            painterResource(id = R.drawable.ic_rt),
                            contentDescription = "",
                            tint = Color.Green
                        )
                    },
                    unselectedIcon = {
                        Icon(
                            painterResource(id = R.drawable.ic_rt),
                            contentDescription = "",
                            tint = Color(0xFF7E8B98)
                        )
                    }
                ) { rtSelect = !rtSelect }
                SocialIcon(
                    modifier = Modifier.weight(1f),
                    isSelected = likeSelect,
                    selectedIcon = {
                        Icon(
                            painterResource(id = R.drawable.ic_like_filled),
                            contentDescription = "",
                            tint = Color.Red
                        )
                    },
                    unselectedIcon = {
                        Icon(
                            painterResource(id = R.drawable.ic_like),
                            contentDescription = "",
                            tint = Color(0xFF7E8B98)
                        )
                    }
                ) { likeSelect = !likeSelect }

            }
        }
    }
}

@Composable
fun TextTitle(title: String, modifier: Modifier = Modifier) {
    Text(text = title, color = Color.White, fontWeight = FontWeight.ExtraBold, modifier = modifier)
}

@Composable
fun DefaultText(title: String, modifier: Modifier = Modifier) {
    Text(text = title, color = Color.Gray, modifier = modifier)
}

@Composable
fun TextBody(modifier: Modifier = Modifier) {
    Text(
        text = "Ejemplo de tweet en la interfaz de nuestra aplicacion twitter android, ejemplo de tweet en la interfaz de nuestra aplicacion twitter android",
        color = Color.White,
        modifier = modifier
    )
}

@Composable
fun SocialIcon(
    modifier: Modifier = Modifier,
    isSelected: Boolean,
    selectedIcon: @Composable () -> Unit,
    unselectedIcon: @Composable () -> Unit,
    onItemSelected: () -> Unit
) {
    val defaultValue = 1

    Row(
        modifier = modifier.clickable { onItemSelected() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (isSelected) {
            selectedIcon()
            Text(text = "${defaultValue + 1}", color = Color(0xFF7E8B98))
        } else {
            unselectedIcon()
            Text(text = "$defaultValue", color = Color(0xFF7E8B98))
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TweetViewPreview() {
    TweetView()
}
