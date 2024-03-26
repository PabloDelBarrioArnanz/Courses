package com.pablodelbarrio.jetpackcomposedefinitivecourseudemy.lessons.part6images

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pablodelbarrio.jetpackcomposedefinitivecourseudemy.R


@Composable
fun MyImage() {
    Image(
        painter = painterResource(id = R.drawable.ic_launcher_background),
        contentDescription = "Image description",
        alpha = 0.5f
    )
}

@Composable
fun MyAdvanceImage() {
    Image(
        painter = painterResource(id = R.drawable.ic_launcher_background),
        contentDescription = "Image description",
        //modifier = Modifier.clip(RoundedCornerShape(25f))
        modifier = Modifier
            .clip(CircleShape)
            .border(3.dp, Color.Blue, CircleShape)
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MyImagePreview() {
    MyAdvanceImage()
}