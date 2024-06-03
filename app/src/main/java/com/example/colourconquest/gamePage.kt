package com.example.colourconquest
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun gamePage(navController: NavController , viewModel: GameViewModel){
    val state = viewModel.state.value
    val context = LocalContext.current
    if(state.whichPlayer){
        Box(modifier = Modifier
            .fillMaxSize()
            .background(Color.hsv(195f, 0.80f, 0.94f)))
    }
    else{
        Box(modifier = Modifier
            .fillMaxSize()
            .background(Color.hsv(6f, 0.9f, 1f)))
    }

    viewModel.refresh()
    Column (modifier = Modifier
        .fillMaxSize()
        .padding(0.dp, 10.dp), verticalArrangement = Arrangement.Center){
        if (viewModel.bestOf()){Spacer(modifier = Modifier.padding(40.dp)) }
        viewModel.timerMode()
        viewModel.computerMode()
            Row {
                Card(
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background),
                    modifier = Modifier
                        .clip(customShape3())
                        .size(100.dp, 50.dp)
                        .border(5.dp, MaterialTheme.colorScheme.background)
                ) {
                    Text(
                        text = ("${state.PlayerScoreA}"),
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Black,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(8.dp)
                            .rotate(180f),
                        color = Color.hsv(195f, 0.80f, 0.94f)
                    )
                }
            Spacer(modifier = Modifier.padding(5.dp))
            Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background),
                modifier = Modifier
                    .clip(customShape1())
                    .size(150.dp, 50.dp)
                    .border(5.dp, MaterialTheme.colorScheme.background)
            ) {
                Text(
                    text = state.PlayerAName, fontSize = 25.sp, fontWeight = FontWeight.Black,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(8.dp)
                        .rotate(180f),
                    color = Color.hsv(195f, 0.80f, 0.94f)
                )
            }
            Spacer(modifier = Modifier.padding(25.dp))
            Button(onClick = {viewModel.gameReset()} , colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.background),
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .size(60.dp)
                    .shadow(elevation = 5.dp, shape = RoundedCornerShape(30.dp))
                    .aspectRatio(1f)
            ) {
                Icon(
                    imageVector = Icons.Default.Clear, contentDescription = null,
                    modifier = Modifier.size(50.dp), tint = MaterialTheme.colorScheme.onBackground
                )
            }
        }


        if (viewModel.PrgressBarA()){
            Spacer(modifier = Modifier.padding(40.dp))
        }

        Column(modifier = Modifier.padding(20.dp)) {

            LazyVerticalGrid(columns = GridCells.Fixed(state.gridSize)) {
                items(state.gridSize * state.gridSize) { itemNo ->
                    GameCell(state.BoxValuesA[itemNo], state.BoxValuesB[itemNo]) {
                        viewModel.buttonClick(itemNo, context);
                    }
                    viewModel.checkFour(itemNo)
                    viewModel.gameOverCheck()
                }
            }

        }

        if (viewModel.PrgressBarB()){
            Spacer(modifier = Modifier.padding(40.dp))
        }

        Row(horizontalArrangement = Arrangement.End) {
            Spacer(modifier = Modifier.padding(70.dp))
            Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background),
                modifier = Modifier
                    .clip(customShape2())
                    .size(150.dp, 50.dp)
                    .border(5.dp, MaterialTheme.colorScheme.background)
            ) {
                Text(
                    text = state.PlayerBName, fontSize = 25.sp, fontWeight = FontWeight.Black,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(8.dp), color = Color.hsv(4f , 0.7f,1f)
                )
            }
            Spacer(modifier = Modifier.padding(5.dp))
            Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background),
                modifier = Modifier
                    .clip(customShape4())
                    .size(100.dp, 50.dp)
                    .border(5.dp, MaterialTheme.colorScheme.background)
            ) {
                Text(
                    text = ("${state.PlayerScoreB}"),
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Black,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(8.dp),
                    color = Color.hsv(4f , 0.7f,1f)
                )
            }

            }

    }
    if (state.gameOver){
        viewModel.gameOver(context, { viewModel.playAgain() }){
            navController.navigate(Screen.openingPage.route)
            viewModel.gameRestart()
        }
    }
}