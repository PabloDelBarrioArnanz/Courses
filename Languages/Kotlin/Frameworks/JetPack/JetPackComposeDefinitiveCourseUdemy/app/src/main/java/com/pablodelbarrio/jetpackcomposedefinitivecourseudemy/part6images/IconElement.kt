package com.pablodelbarrio.jetpackcomposedefinitivecourseudemy.part6images

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun MyIcon() {
    /*
        An icon it's really similar to a Image, the icons by default are of 25px (h/w) and can be tinted
        Here you are all icons the missing one can be added with a library https://fonts.google.com/icons
        but it's really heavy then add it only if need
     */
    Icon(imageVector = Icons.Rounded.Star, contentDescription = "Start Icon", tint = Color.Blue)
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MyIconPreview() {
    MyIcon()
}