package com.example.colourconquest
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.colourconquest.ui.theme.Alert80

@Composable
fun firstPage(navController: NavController, viewModel: GameViewModel, darktheme:Boolean, switch:()-> Unit){

    val statee = viewModel.statee.value
    var aboutButton by remember { mutableStateOf(false)}
    var PlayerRecord by remember { mutableStateOf(false)}
    val context = LocalContext.current
    val verticalGradient = Brush.verticalGradient(
        0.0f to Color.hsv(34.0f,0.56f,1f),
        1.0f to Color.hsv(355.0f,0.62f,1f),
        startY = 0.0f,
        endY = 2200.0f
    )
    val verticalGradientForText = Brush.verticalGradient(
        0.0f to Color.hsv(0.0f,0.0f,0.0f),
        0.6f to Color.hsv(34.0f,0.56f,1f),
        1.0f to Color.hsv(355.0f,0.62f,1f),
        startY = 0.0f,
        endY = 950.0f
    )

    Box(modifier = Modifier
        .background(verticalGradient)
        .fillMaxSize())

    Column (modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally){
        Row(horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp, 0.dp)
                .height(50.dp)){
            Text(text = "Dark/Light Mode    " , color = MaterialTheme.colorScheme.onBackground , fontFamily = FontFamily.Cursive, fontSize = 20.sp)
            Switch(checked =darktheme , onCheckedChange = {switch(); viewModel.vibration(context = context)} )
        }
        Spacer(modifier = Modifier.padding(20.dp))
        Text(text = "COLOR\nCONQUEST",
            fontSize = 65.sp ,
            fontFamily = FontFamily.Cursive ,
            fontWeight = FontWeight.Black,
            textAlign = TextAlign.Center,
            style = TextStyle(verticalGradientForText)
        )

        Image(
            painter = painterResource(id = R.drawable.firstpagephoto),
            contentDescription = "Front Page Two Player Photo",
            modifier = Modifier.size(425.dp, 425.dp)
        )

        Row(horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ){
            Button(onClick = { PlayerRecord=true; viewModel.button_click_01(context)} ,
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.background),
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .size(70.dp)
                    .shadow(elevation = 4.dp, shape = RoundedCornerShape(50.dp))
                    .aspectRatio(1f)
                    .BounceClickEffect()) {
                Icon(imageVector = Icons.Default.List, contentDescription = null, tint = MaterialTheme.colorScheme.onBackground)
            }
            
            Button(onClick = { navController.navigate(Screen.gameMode.route); viewModel.button_click_01(context)},
                colors = ButtonDefaults.
                buttonColors(containerColor = Color.hsv(195f, 0.80f,0.94f)),
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .shadow(elevation = 4.dp, shape = RoundedCornerShape(50.dp))
                    .BounceClickEffect()
                ) {
                Text(text = "PLAY" , fontSize = 45.sp)

            }

            Button(onClick = { aboutButton=true; viewModel.button_click_01(context) } ,
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.background),
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .size(70.dp)
                    .shadow(elevation = 4.dp, shape = RoundedCornerShape(50.dp))
                    .aspectRatio(1f)
                    .BounceClickEffect()
                    ) {
                Icon(painter = painterResource(id = R.drawable.baseline_question_mark_24), contentDescription = null,
                    modifier = Modifier.size(100.dp), tint = MaterialTheme.colorScheme.onBackground)
            }
        }
    }


    if (aboutButton){
        AlertDialog(onDismissRequest = { aboutButton=false },
            modifier=Modifier.height(500.dp),
            title = { Text(text = "About Game" ,fontSize = 30.sp, fontFamily = FontFamily.Cursive, textAlign = TextAlign.Center, fontWeight = FontWeight.Black,) },
            containerColor = MaterialTheme.colorScheme.background,
            shape= RoundedCornerShape(20.dp),
            confirmButton = {
                Button(onClick = { aboutButton=false },
                    colors = ButtonDefaults.
                    buttonColors(containerColor = Color.hsv(355f, 0.62f,1f))) {
                    Text(text = "Close")
                    Icon(imageVector = Icons.Default.Close, contentDescription = null, tint = MaterialTheme.colorScheme.onBackground)}
            },
            text ={
                aboutButton()
            }
        )

    }

    if (PlayerRecord){
        AlertDialog(onDismissRequest = { PlayerRecord=false },
            title = { Text(text = "Player Records", color = MaterialTheme.colorScheme.onBackground,fontSize = 30.sp, fontFamily = FontFamily.Cursive, textAlign = TextAlign.Center, fontWeight = FontWeight.Black,) },
            containerColor = MaterialTheme.colorScheme.background,
            shape= RoundedCornerShape(20.dp),
            confirmButton = {
                Button(onClick = { PlayerRecord=false },
                    colors = ButtonDefaults.
                    buttonColors(containerColor = Color.hsv(355f, 0.62f,1f))) {
                    Text(text = "Close")
                    Icon(imageVector = Icons.Default.Close, contentDescription = null)}
            },
            text = {
                if (statee.dataReceived.isEmpty()) {
                    Text(text = "No Games Played Yet", color = MaterialTheme.colorScheme.onBackground,fontSize = 15.sp)
                } else {
                    val scrollState = rememberScrollState()
                    Column {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Player Name",
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp,
                                color = MaterialTheme.colorScheme.onBackground

                            )
                            Text(text = "Score",
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp,
                                color = MaterialTheme.colorScheme.onBackground
                            )
                        }
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(0.dp, 8.dp)
                                .verticalScroll(scrollState)
                        ) {
                            repeat(1) {
                                Text(text = statee.dataReceived, fontSize = 23.sp, color = MaterialTheme.colorScheme.onBackground)

                            }
                        }

                    }

                }
            }
        )

    }

}

@Composable
fun aboutButton(){
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp, 8.dp)
            .verticalScroll(scrollState)
    ) {
        repeat(1) {
            Text(text = "1st Turn of each player:\n", fontSize = 15.sp, fontWeight = FontWeight.ExtraBold )
            Text("Players can choose any tile on the grid on this turn only. Clicking a tile assigns your colour to it and awards you 3 points on that tile.\n" , fontSize = 15.sp,)
            Text(text = "Subsequent Turns: \n", fontSize = 15.sp, fontWeight = FontWeight.ExtraBold )
            Text("After the first turn, players can only click on tiles that already have their own colour. Clicking a tile with your colour adds 1 point to that tile.The background colour indicates the next player.\n" , fontSize = 15.sp,)
            Text(text = "Conquest and Expansion: \n", fontSize = 15.sp, fontWeight = FontWeight.ExtraBold )
            Text("When a tile with your colour reaches 4 points, it triggers an expansion:\n" +
                    "The colour completely disappears from the original tile.\n" +
                    "Your colour spreads to the four surrounding squares in a plus shape (up, down, left, right).\n" +
                    "Each of the four surrounding squares gains 1 point with your colour.\n" +
                    "If any of the four has your opponent’s colour, you conquer the opponent's points on that tile while adding a point to it, completely erasing theirs.\n" +
                    "The expansion is retriggered if the neighbouring tile as well reaches 4 points this way.\n\n" +
                    "Players take turns clicking on tiles and the objective is to eliminate your opponent's colour entirely from the screen.\n" , fontSize = 15.sp,)
        }
    }
}