package com.example.colourconquest
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun gameMode(navController: NavController , viewModel: GameViewModel){
    val context = LocalContext.current
    var state = viewModel.state.value
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
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        topCard(text = "GAME MODE")
        Spacer(modifier = Modifier.padding(16.dp))
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween){
            RadioButton(selected = !state.computerMode, onClick = { viewModel.pcMode(); viewModel.button_click_03(context)})
            Text(text = "2-Player",
                fontSize = 25.sp ,
                fontFamily = FontFamily.Cursive ,
                fontWeight = FontWeight.Black,
                textAlign = TextAlign.Center,
                color = Color.DarkGray)
            RadioButton(selected =state.computerMode , onClick = { viewModel.pcMode(); viewModel.button_click_03(context) })
            Text(text = "Computer",
                fontSize = 25.sp ,
                fontFamily = FontFamily.Cursive ,
                fontWeight = FontWeight.Black,
                textAlign = TextAlign.Center,
                color = Color.DarkGray)

        }
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = "Timer Mode",
                fontSize = 30.sp ,
                fontFamily = FontFamily.Cursive ,
                fontWeight = FontWeight.Black,
                textAlign = TextAlign.Center,
                color = Color.DarkGray)
            Switch(checked =state.timer , onCheckedChange = {viewModel.forTimer(it); viewModel.button_click_03(context)} )
        }
        Spacer(modifier = Modifier.padding(16.dp))
        Text(text = "Grid Size",
            fontSize = 35.sp ,
            fontFamily = FontFamily.Cursive ,
            fontWeight = FontWeight.Black,
            textAlign = TextAlign.Center,
            color = Color.DarkGray)
        Slider(
            value = state.gridSize.toFloat(),
            onValueChange = { viewModel.forSlider(it); viewModel.button_click_03(context) },
            steps = 0,
            valueRange = 3f..10f
        )

        Text(text = "${state.gridSize}",
            fontSize = 50.sp ,
            fontFamily = FontFamily.Default ,
            fontWeight = FontWeight.Black,
            textAlign = TextAlign.Center,
            color = Color.DarkGray)
        Spacer(modifier = Modifier.padding(32.dp))
        Text(text = "Best Of",
            fontSize = 35.sp ,
            fontFamily = FontFamily.Cursive ,
            fontWeight = FontWeight.Black,
            textAlign = TextAlign.Center,
            color = Color.DarkGray)
        Row(modifier = Modifier.padding(8.dp)) {
            Button(onClick = { viewModel.bestOfSubtract(); viewModel.button_click_03(context) } ,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .size(60.dp)
                    .shadow(elevation = 4.dp, shape = RoundedCornerShape(30.dp))
                    .aspectRatio(1f)
            ) {
                Icon(painter = painterResource(id = R.drawable.baseline_arrow_left_24), contentDescription = null)
            }
            Spacer(modifier = Modifier.padding(32.dp))
            Text(text = "${state.bestOf}",
                fontSize = 50.sp ,
                fontFamily = FontFamily.Default ,
                fontWeight = FontWeight.Black,
                textAlign = TextAlign.Center,
                color = Color.DarkGray)
            Spacer(modifier = Modifier.padding(32.dp))
            Button(onClick = { viewModel.bestOfAdd(); viewModel.button_click_03(context) } ,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .size(60.dp)
                    .shadow(elevation = 4.dp, shape = RoundedCornerShape(30.dp))
                    .aspectRatio(1f)
            ) {
                Icon(painter = painterResource(id = R.drawable.baseline_arrow_right_24), contentDescription = null,
                    modifier = Modifier.size(70.dp)
                )
            }
        }
        Spacer(modifier = Modifier.padding(32.dp))

        Button(
            onClick = { navController.navigate(Screen.playerInfoPage.route); viewModel.button_click_01(context) },
            colors = ButtonDefaults.buttonColors(containerColor = Color.hsv(195f, 0.80f, 0.94f)),
            modifier = Modifier
                .shadow(elevation = 4.dp, shape = RoundedCornerShape(50.dp))
                .BounceClickEffect()
        ) {
            Text(text = "NEXT", fontSize = 45.sp)
        }
    }
}