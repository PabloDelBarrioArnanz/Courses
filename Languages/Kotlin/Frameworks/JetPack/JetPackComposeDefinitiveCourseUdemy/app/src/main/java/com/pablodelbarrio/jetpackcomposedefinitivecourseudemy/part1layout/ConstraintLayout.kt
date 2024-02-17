package com.pablodelbarrio.jetpackcomposedefinitivecourseudemy.part1layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun MyConstraint() {
    /*
        It's a layout witch allows us to set his inner objects in reference with the parent
        or with other children
    */
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {

        val (boxRed, boxBlue, boxYellow, boxMagenta, boxGreen) = createRefs()

        Box(modifier = Modifier
            .constrainAs(boxRed) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            }
            .size(125.dp)
            .background(Color.Red)
        )
        Box(modifier = Modifier
            .constrainAs(boxBlue) {
                end.linkTo(boxRed.start)
                top.linkTo(boxRed.bottom)
            }
            .size(125.dp)
            .background(Color.Blue)
        )
        Box(modifier = Modifier
            .constrainAs(boxYellow) {
                bottom.linkTo(boxRed.top)
                end.linkTo(boxRed.start)
            }
            .size(125.dp)
            .background(Color.Yellow)
        )
        Box(modifier = Modifier
            .constrainAs(boxMagenta) {
                bottom.linkTo(boxRed.top)
                start.linkTo(boxRed.end)
            }
            .size(125.dp)
            .background(Color.Magenta)
        )
        Box(modifier = Modifier
            .constrainAs(boxGreen) {
                start.linkTo(boxRed.end)
                top.linkTo(boxRed.bottom)
            }
            .size(125.dp)
            .background(Color.Green)
        )
    }
}

@Composable
fun MyConstraintAdvance() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {

        val boxRed = createRef()

        // val startGuide = createGuidelineFromTop(16.dp)
        val topGuide = createGuidelineFromTop(0.1f) // 10%
        val startGuide = createGuidelineFromStart(0.25f) // 25%

        Box(modifier = Modifier
            .size(125.dp)
            .background(Color.Red)
            .constrainAs(boxRed) {
                top.linkTo(topGuide)
                start.linkTo(startGuide)
            })
    }
}

@Composable
fun ConstraintBarrier() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {

        val (boxRed, boxGreen, boxYellow) = createRefs()

        // It's a non trespassing for selected items, independent of theis size
        val barrier = createEndBarrier(boxRed, boxGreen)

        Box(modifier = Modifier
            .size(125.dp)
            .background(Color.Green)
            .constrainAs(boxGreen) {
                start.linkTo(parent.start, margin = 16.dp)
            })
        Box(modifier = Modifier
            .size(235.dp)
            .background(Color.Red)
            .constrainAs(boxRed) {
                top.linkTo(boxGreen.bottom)
                start.linkTo(parent.start, margin = 32.dp)
            })
        Box(modifier = Modifier
            .size(50.dp)
            .background(Color.Yellow)
            .constrainAs(boxYellow) {
                start.linkTo(barrier)
            })
    }
}

@Composable
fun ConstraintChain() {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {

        val (boxRed, boxGreen, boxYellow) = createRefs()

        Box(modifier = Modifier
            .size(75.dp)
            .background(Color.Green)
            .constrainAs(boxGreen) {
                start.linkTo(parent.start)
                end.linkTo(boxRed.start)
            })
        Box(modifier = Modifier
            .size(75.dp)
            .background(Color.Red)
            .constrainAs(boxRed) {
                start.linkTo(boxGreen.end)
                end.linkTo(boxYellow.start)
            })
        Box(modifier = Modifier
            .size(75.dp)
            .background(Color.Yellow)
            .constrainAs(boxYellow) {
                start.linkTo(boxRed.end)
                end.linkTo(parent.end)
            })

        createHorizontalChain(boxRed, boxGreen, boxYellow, chainStyle = ChainStyle.Packed) // join
        createHorizontalChain(
            boxRed,
            boxGreen,
            boxYellow,
            chainStyle = ChainStyle.Spread
        ) // default
        createHorizontalChain(
            boxRed,
            boxGreen,
            boxYellow,
            chainStyle = ChainStyle.SpreadInside
        ) // as much separated as possible
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ConstraintPreview() {
    // MyConstraint()
    // MyConstraintAdvance()
    // ConstraintBarrier()
    ConstraintChain()
}