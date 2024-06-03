package com.example.colourconquest

import android.widget.Toast
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.repeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class customShape1 : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val path = Path().apply {
            moveTo(0f, size.height / 2f)
            lineTo(100f, 0f)
            lineTo(size.width, 0f)
            lineTo(size.width, size.height)
            lineTo(100f, size.height)
            close()
        }
        return Outline.Generic(path)
    }
}

class customShape2 : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val path = Path().apply {
            moveTo(0f, 0f)
            lineTo(size.width - 100f, 0f)
            lineTo(size.width, size.height / 2f)
            lineTo(size.width - 100f, size.height)
            lineTo(0f, size.height)
            close()
        }
        return Outline.Generic(path)
    }
}

class customShape3 : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val path = Path().apply {
            moveTo(0f, 0f)
            lineTo(size.width - 300f, 0f)
            arcTo(
                rect = Rect(size.width - 200f, 0f, size.width, size.height),
                startAngleDegrees = 270f,
                sweepAngleDegrees = 180f,
                forceMoveTo = false
            )
            lineTo(size.width - 50f, size.height)
            lineTo(0f, size.height)
            close()
        }
        return Outline.Generic(path)
    }
}

class customShape4 : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val path = Path().apply {
            arcTo(
                rect = Rect(0f, 0f, 200f, size.height),
                startAngleDegrees = 90f,
                sweepAngleDegrees = 180f,
                forceMoveTo = false
            )
            moveTo(100f, 0f)
            lineTo(size.width, 0f)
            lineTo(size.width, size.height)
            lineTo(100f, size.height)
            close()
        }
        return Outline.Generic(path)
    }
}

@Composable
fun topCard(text: String) {
    Box(modifier = Modifier
        .size(350.dp, 65.dp)

        .drawBehind {
            val path = Path()
                .apply {
                    addRoundRect(
                        RoundRect(
                            left = 150f,
                            top = 40f,
                            right = size.width - 150f,
                            bottom = size.height
                        )
                    )
                    moveTo(
                        x = 150f,
                        y = 40f
                    )
                    lineTo(
                        x = 50f,
                        y = size.height / 2 + 20f
                    )
                    lineTo(
                        x = 150f,
                        y = size.height
                    )
                    moveTo(
                        x = size.width - 150f,
                        y = 40f
                    )
                    lineTo(
                        x = size.width - 50f,
                        y = size.height / 2 + 20f
                    )
                    lineTo(
                        x = size.width - 150f,
                        y = size.height
                    )

                }
            drawPath(
                path = path,
                color = Color.hsv(26f, 0.28f, 1f)
            )
        }
    ) {
        Text(
            text = "\n${text}", fontSize = 22.sp, fontWeight = FontWeight.Black,
            modifier = Modifier.align(Alignment.TopCenter), color = Color.DarkGray
        )
    }
}

@Composable
fun gridCircle1(Value: Int) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp)
            .aspectRatio(1f)
            .clip(CircleShape)
            .background(Color.hsv(195f, 0.80f, 0.94f))
    ) {
        autoResizeText(text = "${Value}", style = TextStyle(fontSize = 45.sp))
    }
}

@Composable
fun gridCircle2(Value: Int) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp)
            .aspectRatio(1f)
            .clip(CircleShape)
            .background(Color.Red)
    ) {
        autoResizeText(text = "${Value}", style = TextStyle(fontSize = 45.sp))
    }
}

@Composable
fun autoResizeText(
    text: String,
    style: TextStyle,
    color: Color = Color.White
) {
    var resizeText by remember { mutableStateOf(style) }
    var shouldDraw by remember { mutableStateOf(false) }
    Text(text = text,
        color = color,
        fontWeight = FontWeight(500),
        softWrap = false,
        modifier = Modifier
            .fillMaxSize()
            .drawWithContent {
                if (shouldDraw) {
                    drawContent()
                }
            },
        textAlign = TextAlign.Center,
        style = resizeText,
        onTextLayout = { result ->
            if (result.didOverflowHeight) {
                resizeText = resizeText.copy(
                    fontSize = resizeText.fontSize * 0.95
                )
            } else if (result.didOverflowWidth) {
                resizeText = resizeText.copy(
                    fontSize = resizeText.fontSize * 0.95
                )
            } else {
                shouldDraw = true
            }
        }
    )
}


@Composable
fun gameover(Name: String, playagain:()->Unit, home:()->Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .graphicsLayer(alpha = 0.7f)
            .background(Color.LightGray)
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp, 245.dp)
            .background(Color.DarkGray, RoundedCornerShape(35.dp)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(50.dp)
                .background(Color.White, RoundedCornerShape(35.dp)),
        ) {
            Text(
                text = Name,
                color = Color.Black,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight(1000),
                fontSize = 25.sp
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Canvas(
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.CenterVertically)
            ) {

                drawLine(
                    start = Offset(30f, 0f),
                    end = Offset(300f, y = 0f),
                    color = Color.White,
                    strokeWidth = 8f
                )
            }

            Image(
                painter = painterResource(id = R.drawable.badge),
                contentDescription = "Front Page Two Player Photo",
                modifier = Modifier.size(80.dp, 80.dp)
            )

            Canvas(
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.CenterVertically)
            ) {
                drawLine(
                    start = Offset(-300f, 0f),
                    end = Offset(x = -30f, y = 0f),
                    color = Color.White,
                    strokeWidth = 8f
                )
            }
        }

        Text(
            text = "Wins",
            color = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight(1000),
            fontSize = 25.sp
        )

        Button(
            onClick = { playagain() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 8.dp)
                .height(50.dp),
            colors = ButtonDefaults
                .buttonColors(containerColor = Color.hsv(195f, 0.80f, 0.94f))
        )
        {
            Text(
                text = "Play Again",
                color = Color.White,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(2.dp),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight(1000),
                fontSize = 20.sp
            )
        }
        Button(
            onClick = { home() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 8.dp)
                .height(50.dp),
            colors = ButtonDefaults
                .buttonColors(containerColor = Color.hsv(3f, 0.66f, 1f))
        )
        {
            Text(
                text = "Home",
                color = Color.White,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(2.dp),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight(1000),
                fontSize = 20.sp
            )
        }

    }
}

private fun String.letters() = filter { it.isLetter() }

@Composable
fun playerInfoLayout(computerMode: Boolean): Pair<String, String> {
    var playerNameA by remember { mutableStateOf("") }
    var playerNameB by remember { mutableStateOf("") }
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Box(modifier = Modifier.size(160.dp, 140.dp)) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(180.dp, 100.dp)
                        .background(
                            Color.hsv(236f, 0.5f, 0.48f),
                            RoundedCornerShape(20.dp)
                        )
                        .size(90.dp, 80.dp)
                        .align(Alignment.BottomEnd)
                )
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Bottom
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.Top,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Canvas(
                            modifier = Modifier
                                .padding(4.dp)
                                .align(Alignment.CenterVertically)
                        ) {
                            drawLine(
                                start = Offset(-180f, 0f),
                                end = Offset(-50f, 0f),
                                color = Color.hsv(4f, 0.7f, 1f),
                                strokeWidth = 9f
                            )
                            drawLine(
                                start = Offset(+180f, 0f),
                                end = Offset(+50f, 0f),
                                color = Color.hsv(4f, 0.7f, 1f),
                                strokeWidth = 9f
                            )
                        }

                    }
                    Spacer(modifier = Modifier.padding(8.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .size(150.dp, 50.dp)
                            .padding(8.dp, 4.dp)
                            .background(
                                Color.hsv(4f, 0.7f, 1f),
                                RoundedCornerShape(20.dp)
                            )
                    ) {
                        Spacer(modifier = Modifier.padding(16.dp))
                        Text(
                            text = "PLAYER",
                            modifier = Modifier.fillMaxSize(),
                            textAlign = TextAlign.Center,
                            color = Color.White,
                            textDecoration = TextDecoration.Underline,
                            fontWeight = FontWeight.Bold,
                            fontSize = 25.sp
                        )
                    }

                }
                Text(
                    text = "1",
                    modifier = Modifier.fillMaxSize(),
                    textAlign = TextAlign.Center,
                    fontSize = 80.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.hsv(4f, 0.7f, 1f)
                )
                Text(
                    text = "1",
                    modifier = Modifier
                        .fillMaxSize()
                        .offset(0.dp, 10.dp),
                    style = TextStyle(drawStyle = Stroke(width = 10f)),
                    textAlign = TextAlign.Center,
                    fontSize = 80.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.hsv(236f, 0.5f, 0.48f)
                )
            }
            Box(modifier = Modifier.size(160.dp, 140.dp)) {
                Box(
                    modifier = Modifier
                        .background(
                            Color.hsv(236f, 0.5f, 0.48f),
                            RoundedCornerShape(20.dp)
                        )
                        .fillMaxWidth()
                        .size(180.dp, 100.dp)
                        .align(Alignment.BottomEnd)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceAround,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(10.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.player1),
                            contentDescription = "Player 1",
                            modifier = Modifier
                                .size(45.dp, 45.dp)
                                .fillMaxSize(),
                            alignment = Alignment.Center
                        )
                        if (!computerMode) {
                            BasicTextField(
                                value = playerNameA,
                                modifier = Modifier.size(180.dp, 15.dp),
                                textStyle = TextStyle(
                                    color = Color.White,
                                    fontSize = 15.sp,
                                    textAlign = TextAlign.Center
                                ),
                                singleLine = true,
                                onValueChange = {
                                    if (it.length < 6) {
                                        playerNameA = it.letters()
                                    }
                                },
                                decorationBox = { innerTextField ->
                                    Row(
                                        Modifier
                                            .fillMaxWidth(),
                                        horizontalArrangement = Arrangement.Center
                                    ) {
                                        if (playerNameA.isEmpty()) {
                                            Text(
                                                "Enter Player-1 Name",
                                                fontSize = 10.sp,
                                                color = Color.LightGray,
                                                modifier = Modifier.fillMaxSize(),
                                                textAlign = TextAlign.Center
                                            )
                                        }
                                        innerTextField()
                                    }
                                }
                            )
                        } else {
                            playerNameA = "PC"
                            Text(
                                text = playerNameA,
                                style = TextStyle(color = Color.White),
                                fontSize = 15.sp,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.size(180.dp, 15.dp),

                                )
                        }

                        Canvas(
                            modifier = Modifier
                                .padding(4.dp)
                        ) {
                            drawLine(
                                start = Offset(-175f, 0f),
                                end = Offset(170f, 0f),
                                color = Color.hsv(4f, 0.7f, 1f),
                                strokeWidth = 9f,
                                cap = StrokeCap.Round,
                                pathEffect = PathEffect.dashPathEffect(
                                    intervals = floatArrayOf(20f, 20f)
                                )
                            )
                        }
                    }
                }
            }
        }
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Box(modifier = Modifier.size(160.dp, 140.dp)) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(180.dp, 100.dp)
                        .background(
                            Color.hsv(236f, 0.5f, 0.48f),
                            RoundedCornerShape(20.dp)
                        )
                        .size(90.dp, 80.dp)
                        .align(Alignment.BottomEnd)
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Bottom
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.Top,
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Canvas(
                                modifier = Modifier
                                    .padding(4.dp)
                                    .align(Alignment.CenterVertically)
                            ) {
                                drawLine(
                                    start = Offset(-180f, 0f),
                                    end = Offset(-50f, 0f),
                                    color = Color.hsv(195f, 0.8f, 0.94f),
                                    strokeWidth = 9f
                                )
                                drawLine(
                                    start = Offset(+180f, 0f),
                                    end = Offset(+50f, 0f),
                                    color = Color.hsv(195f, 0.8f, 0.94f),
                                    strokeWidth = 9f
                                )
                            }

                        }
                        Spacer(modifier = Modifier.padding(8.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .size(150.dp, 50.dp)
                                .padding(8.dp, 4.dp)
                                .background(
                                    Color.hsv(195f, 0.8f, 0.94f),
                                    RoundedCornerShape(20.dp)
                                )
                        ) {
                            Spacer(modifier = Modifier.padding(16.dp))
                            Text(
                                text = "PLAYER",
                                modifier = Modifier.fillMaxSize(),
                                textAlign = TextAlign.Center,
                                color = Color.White,
                                textDecoration = TextDecoration.Underline,
                                fontWeight = FontWeight.Bold,
                                fontSize = 25.sp
                            )
                        }

                    }
                }
                Text(
                    text = "2",
                    modifier = Modifier.fillMaxSize(),
                    textAlign = TextAlign.Center,
                    fontSize = 80.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.hsv(195f, 0.8f, 0.94f)
                )
                Text(
                    text = "2",
                    modifier = Modifier
                        .fillMaxSize()
                        .offset(0.dp, 12.dp),
                    style = TextStyle(drawStyle = Stroke(width = 20f)),
                    textAlign = TextAlign.Center,
                    fontSize = 80.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.hsv(236f, 0.5f, 0.48f)
                )
            }
            Box(modifier = Modifier.size(160.dp, 140.dp)) {
                Box(
                    modifier = Modifier
                        .background(
                            Color.hsv(236f, 0.5f, 0.48f),
                            RoundedCornerShape(20.dp)
                        )
                        .fillMaxWidth()
                        .size(180.dp, 100.dp)
                        .align(Alignment.BottomEnd)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceAround,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(10.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.player2),
                            contentDescription = "Player 1",
                            modifier = Modifier
                                .size(45.dp, 45.dp)
                                .fillMaxSize(),
                            alignment = Alignment.Center
                        )
                        BasicTextField(
                            value = playerNameB,
                            modifier = Modifier.size(180.dp, 15.dp),
                            textStyle = TextStyle(
                                color = Color.White,
                                fontSize = 15.sp,
                                textAlign = TextAlign.Center
                            ),
                            singleLine = true,
                            onValueChange = {
                                if (it.length < 6) {
                                    playerNameB = it.letters()
                                }
                            },
                            decorationBox = { innerTextField ->
                                Row(
                                    Modifier
                                        .fillMaxWidth(),
                                    horizontalArrangement = Arrangement.Center

                                ) {
                                    if (playerNameB.isEmpty()) {
                                        Text(
                                            "Enter Player-2 Name",
                                            fontSize = 10.sp,
                                            color = Color.LightGray,
                                            modifier = Modifier.fillMaxSize(),
                                            textAlign = TextAlign.Center
                                        )
                                    }
                                    innerTextField()
                                }
                            }
                        )
                        Canvas(
                            modifier = Modifier
                                .padding(4.dp)
                        ) {
                            drawLine(
                                start = Offset(-175f, 0f),
                                end = Offset(170f, 0f),
                                color = Color.hsv(195f, 0.8f, 0.94f),
                                strokeWidth = 9f,
                                cap = StrokeCap.Round,
                                pathEffect = PathEffect.dashPathEffect(
                                    intervals = floatArrayOf(20f, 20f)
                                )
                            )
                        }
                    }
                }
            }
        }

    }
    return Pair(playerNameA, playerNameB)
}

@Composable
fun GameCell(buttonValue1: Int, buttonValue2: Int, onClick: () -> Unit) {
    ElevatedButton(
        onClick = onClick,
        elevation = ButtonDefaults.buttonElevation(6.dp),
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(Color.hsv(35f, 0.16f, 0.96f)),
        modifier = Modifier
            .padding(4.dp)
            .shadow(elevation = 5.dp, shape = RoundedCornerShape(2.dp))
            .aspectRatio(1f)
            .BounceClickEffect(),
        contentPadding = PaddingValues(0.dp)
    ) {

        if (buttonValue1 > 0) {
            gridCircle1(Value = buttonValue1)
        }
        if (buttonValue2 > 0) {
            gridCircle2(Value = buttonValue2)
        }
    }
}

@Composable
fun progressBarA(A: Float) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Time Left: ${A} sec",
            color = Color.DarkGray,
            fontSize = 15.sp,
            style = TextStyle(fontFamily = FontFamily.Monospace),
            textAlign = TextAlign.Center
        )
        LinearProgressIndicator(
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp),
            color = Color.White,
            progress = A / 30f,
            strokeCap = StrokeCap.Round
        )

    }
}

@Composable
fun progressBarB(B: Float) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Time Left: ${B.toInt()} sec",
            color = Color.DarkGray,
            fontSize = 15.sp,
            style = TextStyle(fontFamily = FontFamily.Monospace),
            textAlign = TextAlign.Center
        )
        LinearProgressIndicator(
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp),
            color = Color.White,
            progress = B / 30f,
            strokeCap = StrokeCap.Round
        )

    }
}

@Composable
fun displayBestOf(A: Int, B: Int) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {

        Column(
            modifier = Modifier
                .width(300.dp)
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
                    .height(40.dp)
                    .background(MaterialTheme.colorScheme.background, RoundedCornerShape(35.dp)),
            ) {
                Text(
                    text = "Match $A out of $B",
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                    fontFamily = FontFamily.Cursive,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Black,
                    fontSize = 18.sp
                )
            }
        }
    }

}

@Composable
fun bestOfAlert(
    Name: String,
    Score: Int,
    Name_: String,
    Score_: Int
): Boolean {
    var bestOF by remember { mutableStateOf(false) }
    var show by remember { mutableStateOf(true) }
    if (show) {
        AlertDialog(onDismissRequest = { show = false; bestOF = true },
            title = {
                Text(
                    text = "Game Result",
                    fontSize = 30.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontFamily = FontFamily.Cursive,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Black,
                )
            },
            containerColor = MaterialTheme.colorScheme.background,
            shape = RoundedCornerShape(20.dp),
            confirmButton = {
                Button(
                    onClick = { show = false; bestOF = true },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.hsv(
                            355f,
                            0.62f,
                            1f
                        )
                    )
                ) {
                    Text(text = "Close")
                    Icon(imageVector = Icons.Default.Close, contentDescription = null)
                }
            },
            text = {
                Text(
                    text = "Hooray! $Name has won.\n$Name won $Score match,\n$Name_ won $Score_ match.",
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 20.sp,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
        )
    }
    return bestOF
}

@Composable
fun background() {
    val verticalGradient = Brush.verticalGradient(
        0.0f to Color.hsv(34.0f, 0.56f, 1f),
        1.0f to Color.hsv(355.0f, 0.62f, 1f),
        startY = 0.0f,
        endY = 2200.0f
    )
    Box(
        modifier = Modifier
            .background(verticalGradient)
            .fillMaxSize()
    )
}

@Composable
fun Modifier.BounceClickEffect(): Modifier {
    var isPressed by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(if (isPressed) 0.70f else 1f)

    return this
        .graphicsLayer {
            scaleX = scale
            scaleY = scale
        }
        .pointerInput(isPressed) {
            awaitPointerEventScope {
                isPressed = if (isPressed) {
                    waitForUpOrCancellation()
                    false
                } else {
                    awaitFirstDown(false)
                    true
                }
            }
        }
}

@Composable
fun providePowerups(
    Name: String,
    istimerON: Boolean,
    nopoweroffclick: () -> Unit,
    plustwoclick: () -> Unit,
    doubleInitial: () -> Unit,
    timeroffclick: () -> Unit,
    computerMode:Boolean
): Boolean {
    val context = LocalContext.current
    var forif by remember {
        mutableStateOf(false)
    }
    var show by remember {
        mutableStateOf(false)
    }
    if (computerMode){
        Toast.makeText(context, "Computer chose no powerups buddy", Toast.LENGTH_SHORT).show()
        forif =true
    }
    else{
        show=true
    }
    if (show) {
        AlertDialog(onDismissRequest = { show = false },
            modifier = Modifier.fillMaxWidth(),
            title = {
                Text(
                    text = "$Name can choose a powerup !!",
                    fontSize = 30.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontFamily = FontFamily.Cursive,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Black,
                )
            },
            containerColor = MaterialTheme.colorScheme.background,
            shape = RoundedCornerShape(20.dp),
            confirmButton = {
                Button(
                    onClick = { show = false; nopoweroffclick(); forif = true },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.hsv(
                            355f,
                            0.62f,
                            1f
                        )
                    )
                ) {
                    Text(text = "No need powerups :(", fontSize = 15.sp)
                }
            },
            text = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Column(
                        modifier = Modifier.size(80.dp, 100.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Button(
                            onClick = { show = false; plustwoclick(); forif = true },
                            modifier = Modifier
                                .size(70.dp)
                                .shadow(elevation = 4.dp, shape = RoundedCornerShape(50.dp))
                                .aspectRatio(1f)
                                .BounceClickEffect()
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.plus_2),
                                contentDescription = null,
                                modifier = Modifier.size(100.dp),
                                tint = MaterialTheme.colorScheme.onBackground
                            )
                        }
                        Text(
                            text = "\n+2 points instead of +1",
                            fontSize = 7.sp,
                            fontStyle = FontStyle.Italic,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }
                    Column(
                        modifier = Modifier.size(80.dp, 100.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Button(
                            onClick = { show = false; doubleInitial(); forif = true },
                            modifier = Modifier
                                .size(70.dp)
                                .shadow(elevation = 4.dp, shape = RoundedCornerShape(50.dp))
                                .aspectRatio(1f)
                                .BounceClickEffect()
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.plues_two),
                                contentDescription = null,
                                modifier = Modifier.size(100.dp),
                                tint = MaterialTheme.colorScheme.onBackground
                            )
                        }
                        Text(
                            text = "\nDouble Initial move",
                            fontSize = 8.sp,
                            fontStyle = FontStyle.Italic,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }

                    if (istimerON) {
                        Column(
                            modifier = Modifier.size(80.dp, 100.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Button(
                                onClick = { show = false; timeroffclick(); forif = true },
                                modifier = Modifier
                                    .size(70.dp)
                                    .shadow(elevation = 4.dp, shape = RoundedCornerShape(50.dp))
                                    .aspectRatio(1f)
                                    .BounceClickEffect()
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.timer_off),
                                    contentDescription = null,
                                    modifier = Modifier.size(100.dp),
                                    tint = MaterialTheme.colorScheme.onBackground
                                )
                            }
                            Text(
                                text = "\ntimer off",
                                fontSize = 9.sp,
                                fontStyle = FontStyle.Italic,
                                color = MaterialTheme.colorScheme.onBackground
                            )
                        }
                    }
                }
            }
        )
    }
    return forif
}