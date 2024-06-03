package com.example.colourconquest
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun secondPagemain(navController: NavController,
                   viewModel: GameViewModel){
    val context = LocalContext.current
    val state = viewModel.state.value
    val verticalGradient = Brush.verticalGradient(
        0.0f to Color.hsv(34.0f,0.56f,1f),
        1.0f to Color.hsv(355.0f,0.62f,1f),
        startY = 0.0f,
        endY = 2600.0f
    )
    Box(modifier = Modifier
        .background(verticalGradient)
        .fillMaxSize())


    Column (horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround,
        modifier = Modifier.padding(16.dp)){

        topCard("PLAYER INFORMATION")

        viewModel.playerInfo()

        Image(
            painter = painterResource(id = R.drawable.firstpagephoto),
            contentDescription = "Front Page Two Player Photo",
            modifier = Modifier.size(400.dp, 150.dp)
        )


        Button(onClick = {
            if (state.PlayerAName.isEmpty() || state.PlayerBName.isEmpty()){
                Toast.makeText(context, "Enter a Valid Name", Toast.LENGTH_SHORT).show()
                viewModel.button_click_01(context)
            }else{
                navController.navigate(Screen.gamePage.route)
                viewModel.button_click_01(context)
            }
        },
            colors = ButtonDefaults.
            buttonColors(containerColor = Color.hsv(195f, 0.80f,0.94f)),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .shadow(elevation = 4.dp, shape = RoundedCornerShape(50.dp))
                .BounceClickEffect()
        ) {
            Text(text = "START" , fontSize = 45.sp)

        }
    }

}