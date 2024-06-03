package com.example.colourconquest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.colourconquest.ui.theme.ColourConquestTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var darktheme:Boolean by remember{ mutableStateOf(true) }
            ColourConquestTheme(darktheme) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    myGame(viewModel = GameViewModel(), darktheme){
                        darktheme=!darktheme
                    }
                }
            }
        }
    }
}

@Composable
fun myGame(viewModel: GameViewModel, darktheme:Boolean, switch:()->Unit){
    val navController = rememberNavController()
    viewModel.fetchData(LocalContext.current)
    background()
    NavHost(navController = navController, startDestination = Screen.openingPage.route,

        enterTransition = {
            fadeIn(
                animationSpec = tween(
                    400, easing = LinearOutSlowInEasing
                )
            ) + slideIntoContainer(
                animationSpec = tween(400, easing = EaseIn),
                towards = AnimatedContentTransitionScope.SlideDirection.Left
            )
        },
        exitTransition = {
            fadeOut(
                animationSpec = tween(
                    400, easing = LinearOutSlowInEasing
                )
            ) + slideOutOfContainer(
                animationSpec = tween(400, easing = EaseOut),
                towards = AnimatedContentTransitionScope.SlideDirection.Left
            )
        },
        popEnterTransition = {
            fadeIn(
                animationSpec = tween(
                    400, easing = LinearOutSlowInEasing
                )
            ) + slideIntoContainer(
                animationSpec = tween(400, easing = EaseIn),
                towards = AnimatedContentTransitionScope.SlideDirection.Right
            )
        },
        popExitTransition = {
            fadeOut(
                animationSpec = tween(
                    400, easing = LinearOutSlowInEasing
                )
            ) + slideOutOfContainer(
                animationSpec = tween(400, easing = EaseOut),
                towards = AnimatedContentTransitionScope.SlideDirection.Right
            )
        }
        ){
        composable(route = Screen.openingPage.route,

        ){
            firstPage(navController = navController, viewModel, darktheme,switch)
        }
        composable(route = Screen.playerInfoPage.route ){
            secondPagemain(navController = navController , viewModel)
        }
        composable(route = Screen.gameMode.route ){
            gameMode(navController = navController, viewModel)
        }
        composable(route = Screen.gamePage.route ){
            gamePage(navController = navController, viewModel)
        }
    }
}