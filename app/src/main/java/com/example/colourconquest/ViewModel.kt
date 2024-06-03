package com.example.colourconquest

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import kotlin.math.roundToInt
import android.content.Context
import android.media.MediaPlayer
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import java.io.*

class GameViewModel : ViewModel() {
    private val _state = mutableStateOf(GameStates())
    val state: State<GameStates> = _state

    private val __state = mutableStateOf(Record())
    val statee: State<Record> = __state


    @Composable
    fun playerInfo(){
        var (PlayerA , PlayerB) = playerInfoLayout(state.value.computerMode)
        _state.value = _state.value.copy(PlayerAName = PlayerA, PlayerBName = PlayerB)
    }

    fun forTimer(value:Boolean){
        _state.value = _state.value.copy(timer = value)
    }
    fun pcMode(){
        _state.value = _state.value.copy(computerMode = !state.value.computerMode)
    }

    fun forSlider(value:Float){
        _state.value = _state.value.copy(gridSize = value.roundToInt())
        _state.value.gridSize=value.roundToInt()
    }

    fun bestOfAdd(){
        val x = state.value.bestOf + 2
        _state.value = _state.value.copy(bestOf = x)
    }

    fun bestOfSubtract(){
        if ( _state.value.bestOf>2){
            val x = state.value.bestOf - 2
            _state.value = _state.value.copy(bestOf = x)
        }
    }

    fun buttonClick(itemNo : Int, context: Context){
        val boxValuesA = state.value.BoxValuesA.toMutableList()
        val boxValuesB = state.value.BoxValuesB.toMutableList()
        if (state.value.whichPlayer && state.value.BoxValuesB[itemNo]==0){
            if(state.value.isInitialA ){
                if(state.value.doubleInitial && state.value.doubleInitialCount<1){
                    boxValuesA[itemNo] = 3
                    _state.value = _state.value.copy(BoxValuesA = boxValuesA , doubleInitialCount = state.value.doubleInitialCount+1)
                }
                else{
                    boxValuesA[itemNo] = boxValuesA[itemNo]+3
                    _state.value = _state.value.copy(BoxValuesA = boxValuesA ,isInitialA = false ,whichPlayer = false , doubleInitial = false, doubleInitialCount = 0 )
                }
                button_click_02(context)
            }
            else if(state.value.BoxValuesA[itemNo]>0 && state.value.PlayerScoreA>0){
                if(!state.value.poweruptwoA) {
                    boxValuesA[itemNo] += 1
                    _state.value = _state.value.copy(
                        BoxValuesA = boxValuesA,
                        whichPlayer = false,
                    )
                }
                else{
                    boxValuesA[itemNo] += 2
                    _state.value = _state.value.copy(
                        BoxValuesA = boxValuesA,
                        whichPlayer = false,
                    )
                }
                button_click_02(context)
            }
            else{vibration(context)}
        }
        else if(!state.value.whichPlayer && state.value.BoxValuesA[itemNo]== 0){
            if(state.value.isInitialB){
                if(state.value.doubleInitial && state.value.doubleInitialCount<1) {
                    boxValuesB[itemNo] = 3
                    _state.value = _state.value.copy(
                        BoxValuesB = boxValuesB,
                        doubleInitialCount = state.value.doubleInitialCount+1
                    )
                }
                else{
                    boxValuesB[itemNo] = boxValuesB[itemNo]+3
                    _state.value = _state.value.copy(
                        BoxValuesB = boxValuesB,
                        isInitialB = false,
                        whichPlayer = true,
                        doubleInitial = false,
                        doubleInitialCount = 0
                    )
                }
                button_click_02(context)
            }
            else if(state.value.BoxValuesB[itemNo]>0  && state.value.PlayerScoreB>0){
                if (!state.value.poweruptwoB) {
                    boxValuesB[itemNo] += 1
                    _state.value = _state.value.copy(
                        BoxValuesB = boxValuesB,
                        whichPlayer = true,
                    )
                }
                else{
                    boxValuesB[itemNo] += 2
                    _state.value = _state.value.copy(
                        BoxValuesB = boxValuesB,
                        whichPlayer = true,
                    )
                }
                button_click_02(context)
            }
            else{vibration(context)}
        }
        else{vibration(context)}
        _state.value = _state.value.copy(PlayerScoreA = state.value.BoxValuesA.sum() + state.value.overTookA,
            PlayerScoreB = state.value.BoxValuesB.sum() + state.value.overTookB)
    }

    @Composable
    fun computerMode(){
        val context = LocalContext.current
        LaunchedEffect(key1 = state.value.whichPlayer){
            if (state.value.computerMode && state.value.whichPlayer){
                delay(1000)
                var index: Int
                if(state.value.isInitialA){
                    index = (0..<(state.value.gridSize*state.value.gridSize)).random()
                }
                else {
                    val maxNumber = state.value.BoxValuesA.max()
                    index = state.value.BoxValuesA.indexOf(maxNumber)
                }
                buttonClick(index,context)
            }
        }
    }

    fun checkFour(itemNo : Int){
        val boxValuesA = state.value.BoxValuesA.toMutableList()
        val boxValuesB = state.value.BoxValuesB.toMutableList()
        var toA : Int = state.value.overTookA
        var toB : Int = state.value.overTookB
        var tobeMultiplied = itemNo/state.value.gridSize

        if(state.value.BoxValuesA[itemNo]>3){
            if(itemNo-1>-1 && itemNo-1 > (tobeMultiplied*state.value.gridSize -1)){
                boxValuesA[itemNo-1]+=1
                toA += boxValuesB[itemNo - 1]
                boxValuesB[itemNo-1]= 0
            }
            if(itemNo+1<(state.value.gridSize*state.value.gridSize) && itemNo+1 < ((tobeMultiplied+1)*state.value.gridSize)){
                boxValuesA[itemNo+1]+=1
                toA += boxValuesB[itemNo + 1]
                boxValuesB[itemNo+1]= 0
            }
            if(itemNo-state.value.gridSize>-1){
                boxValuesA[itemNo-state.value.gridSize]+=1
                toA += boxValuesB[itemNo - state.value.gridSize]
                boxValuesB[itemNo-state.value.gridSize]= 0
            }
            if(itemNo+state.value.gridSize<(state.value.gridSize*state.value.gridSize)){
                boxValuesA[itemNo+state.value.gridSize]+=1
                toA += boxValuesB[itemNo + state.value.gridSize]
                boxValuesB[itemNo+state.value.gridSize]= 0
            }
            if(state.value.poweruptwoA){
                boxValuesA[itemNo]=1
            }else if (state.value.BoxValuesA[itemNo]==6){
                boxValuesA[itemNo]=2
            }else{
                boxValuesA[itemNo]=0
            }
        }
        if(state.value.BoxValuesB[itemNo]>3){
            if(itemNo-1>-1 && itemNo-1 > (tobeMultiplied*state.value.gridSize -1)){
                boxValuesB[itemNo-1]+=1
                toB += boxValuesA[itemNo - 1]
                boxValuesA[itemNo-1]= 0
            }
            if(itemNo+1<(state.value.gridSize*state.value.gridSize) && itemNo+1 < ((tobeMultiplied+1)*state.value.gridSize)){
                boxValuesB[itemNo+1]+=1
                toB += boxValuesA[itemNo + 1]
                boxValuesA[itemNo+1]= 0
            }
            if(itemNo-state.value.gridSize>-1){
                boxValuesB[itemNo-state.value.gridSize]+=1
                toB += boxValuesA[itemNo - state.value.gridSize]
                boxValuesA[itemNo-state.value.gridSize]= 0
            }
            if(itemNo+state.value.gridSize<(state.value.gridSize*state.value.gridSize)){
                boxValuesB[itemNo+state.value.gridSize]+=1
                toB += boxValuesA[itemNo + state.value.gridSize]
                boxValuesA[itemNo+state.value.gridSize]= 0
            }
            if(state.value.poweruptwoB){
                boxValuesB[itemNo]=1
            }else if (state.value.BoxValuesB[itemNo]==6){
                boxValuesB[itemNo]=2
            }else{
                boxValuesB[itemNo]=0
            }
        }
        _state.value = _state.value.copy(
            BoxValuesA = boxValuesA,
            BoxValuesB = boxValuesB,
            overTookA = toA,
            overTookB = toB,
            PlayerScoreA = state.value.BoxValuesA.sum() + state.value.overTookA,
            PlayerScoreB = state.value.BoxValuesB.sum() + state.value.overTookB)
    }

    fun gameOverCheck() {
        if (state.value.timeLeftA==0 || state.value.timeLeftB ==0){
            _state.value= _state.value.copy(
                gameOver = true,
                timerPaused = true,
            )
        }
        if (!state.value.isInitialA && !state.value.isInitialB){
            if (state.value.BoxValuesA.sum()==0 || state.value.BoxValuesB.sum()==0){
                _state.value= _state.value.copy(
                    gameOver = true,
                    timerPaused = true,
                )
            }
        }
    }

    fun gameReset(){
        _state.value = _state.value.copy(
            BoxValuesA = state.value.BoxValuesA.map {it*0},
            BoxValuesB = state.value.BoxValuesB.map {it*0},
            overTookA = 0,
            overTookB = 0,
            timeLeftA = 30,
            timeLeftB = 30,
            whichPlayer = true,
            isInitialA = true,
            isInitialB = true,
            gameOver = false 
        )
    }
    fun gameRestart(){
        _state.value= GameStates()
    }

    fun playAgain(){
        _state.value = _state.value.copy(
            BoxValuesA = state.value.BoxValuesA.map {it*0},
            BoxValuesB = state.value.BoxValuesB.map {it*0},
            overTookA = 0,
            overTookB = 0,
            timeLeftA = 30,
            timeLeftB = 30,
            gameOver = false,
            isInitialA = true,
            isInitialB = true,
            timerPaused = false,
            matchCount = 0,
            bestOfA = 0,
            bestOfB = 0,
            whichPlayer = true,
        )
    }

    @Composable
    fun gameOver(context: Context, playagain:()-> Unit , home:()->Unit){
        if(state.value.timeLeftA==0 || state.value.BoxValuesA.sum() ==0){
            var x = state.value.bestOfB
            if((x+1) == ((state.value.bestOf/2)+1)){
                gameover(Name =state.value.PlayerBName, playagain, home)
                executeOnceB()
                SaveData(LocalContext.current)
                SaveBestScore(context)
            }
            else{
                if(bestOfAlert(Name = state.value.PlayerBName, Score = (x+1), state.value.PlayerAName , state.value.bestOfA)
                        && providePowerups(Name = state.value.PlayerAName, state.value.timer, computerMode = state.value.computerMode,
                        nopoweroffclick = {_state.value = _state.value.copy(doubleInitial = false, poweruptwoB = false, poweruptwoA = false, poweruptimerB = false, poweruptimerA = false);powerup(context)},
                        plustwoclick = {_state.value = _state.value.copy(poweruptwoA = true ,doubleInitial = false, poweruptwoB = false, poweruptimerA = false, poweruptimerB = false);powerup(context)},
                        doubleInitial ={_state.value = _state.value.copy(doubleInitial = true, poweruptwoB = false, poweruptwoA = false, poweruptimerB = false, poweruptimerA = false);powerup(context)} ,
                        timeroffclick ={_state.value = _state.value.copy(poweruptimerA = true,doubleInitial = false, poweruptimerB = false, poweruptwoB = false, poweruptwoA = false);powerup(context)})
                 ){
                    _state.value = _state.value.copy(
                        matchCount = state.value.matchCount+1,
                        bestOfB = x+1,
                        BoxValuesA = state.value.BoxValuesA.map {it*0},
                        BoxValuesB = state.value.BoxValuesB.map {it*0},
                        overTookA = 0,
                        overTookB = 0,
                        timeLeftA = 30,
                        timeLeftB = 30,
                        gameOver = false,
                        isInitialA = true,
                        isInitialB = true,
                        timerPaused = false,
                        whichPlayer = true
                    )

                }
                executeOnceB()
                SaveData(LocalContext.current)
            }
        }
        else if(state.value.timeLeftB==0 || state.value.BoxValuesB.sum() ==0){
            var x = state.value.bestOfA
            if((x+1) == ((state.value.bestOf/2)+1)){
                gameover(Name =state.value.PlayerAName, playagain, home)
                executeOnceA()
                SaveData(LocalContext.current)
                SaveBestScore(context)
            }
            else{
                if(bestOfAlert(Name = state.value.PlayerAName, Score = (x+1), state.value.PlayerBName , state.value.bestOfB)
                    && providePowerups(Name = state.value.PlayerBName, state.value.timer, computerMode = state.value.computerMode,
                        nopoweroffclick = {_state.value = _state.value.copy(doubleInitial = false, poweruptwoB = false, poweruptwoA = false, poweruptimerB = false, poweruptimerA = false);powerup(context)},
                        plustwoclick = {_state.value = _state.value.copy(poweruptwoB = true, doubleInitial = false,poweruptwoA = false, poweruptimerB = false, poweruptimerA = false);powerup(context)},
                        doubleInitial ={_state.value = _state.value.copy(doubleInitial = true,poweruptwoB = false, poweruptwoA = false, poweruptimerB = false, poweruptimerA = false);powerup(context)},
                        timeroffclick ={_state.value = _state.value.copy(poweruptimerB = true, doubleInitial = false,poweruptimerA = false, poweruptwoA = false, poweruptwoB = false);powerup(context)})
                    ){
                    _state.value = _state.value.copy(
                        matchCount = state.value.matchCount+1,
                        bestOfA = x+1,
                        BoxValuesA = state.value.BoxValuesA.map {it*0},
                        BoxValuesB = state.value.BoxValuesB.map {it*0},
                        overTookA = 0,
                        overTookB = 0,
                        timeLeftA = 30,
                        timeLeftB = 30,
                        gameOver = false,
                        isInitialA = true,
                        isInitialB = true,
                        timerPaused = false,
                        whichPlayer = false
                    )
                }
                executeOnceA()
                SaveData(LocalContext.current)
            }
        }
    }

    @Composable
    fun timerMode(){
        if(state.value.timer){
            if (state.value.whichPlayer) {
                LaunchedEffect(key1 = state.value.timeLeftA) {
                    while (state.value.timeLeftA > 0 &&!state.value.timerPaused &&!state.value.poweruptimerA) {
                        delay(1000L)
                        _state.value = _state.value.copy(timeLeftA = _state.value.timeLeftA - 1)
                    }
                }

            }
            else{
                LaunchedEffect(key1 = state.value.timeLeftB) {
                    while (state.value.timeLeftB > 0 &&!state.value.timerPaused &&!state.value.poweruptimerB) {
                        delay(1000L)
                        _state.value = _state.value.copy(timeLeftB = _state.value.timeLeftB - 1)
                    }
                }
            }
        }
    }

    @Composable
    fun PrgressBarA():Boolean{
        if(state.value.timer){
            progressBarA(A = state.value.timeLeftA.toFloat())
            return false
        }
        return true
    }

    @Composable
    fun PrgressBarB():Boolean{
        if(state.value.timer){
            progressBarB(B = state.value.timeLeftB.toFloat())
            return false
        }
        return true
    }

    @Composable
    fun bestOf():Boolean{
        if(state.value.bestOf>1){
            displayBestOf(state.value.matchCount , state.value.bestOf)
            return false
        }
        return true
    }

    fun SaveData(context: Context){
        try{
            val fos: FileOutputStream=
                context.openFileOutput("scoreboard.txt", Context.MODE_PRIVATE)
            fos.write(statee.value.dataToBeSent.toByteArray())
            fos.flush()
            fos.close()
        }catch (e: IOException){
            e.printStackTrace()
        }

    }
    fun SaveBestScore(context: Context){
        try{
            val fos: FileOutputStream=
                context.openFileOutput("bestscore.txt", Context.MODE_PRIVATE)
            fos.write(statee.value.bestScore.toString().toByteArray())
            fos.flush()
            fos.close()
        }catch (e: IOException){
            e.printStackTrace()
        }

    }

    fun fetchData(context: Context){
        try {
            val fin: FileInputStream = context.openFileInput("scoreboard.txt")
            var a: Int
            val temp = StringBuilder()
            while (fin.read().also { a = it } != -1) {
                temp.append(a.toChar())
            }

            __state.value = __state.value.copy(dataReceived =temp.toString())
            fin.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    @Composable
    fun executeOnceB(){
        var hasExecutedOnce by remember { mutableStateOf(false) }
        LaunchedEffect(Unit) {
            if(state.value.timer){_state.value =_state.value.copy(PlayerScoreB = state.value.PlayerScoreB+ (state.value.timeLeftB/2))}
            if (!hasExecutedOnce) {
                __state.value.dataToBeSent = __state.value.dataReceived
                __state.value = __state.value.copy(
                    dataToBeSent = statee.value.dataToBeSent + "${state.value.PlayerBName}                               ${state.value.PlayerScoreB}\n"
                )
                __state.value = __state.value.copy(
                    dataReceived = statee.value.dataToBeSent
                )
                hasExecutedOnce = true
            }
            if(statee.value.bestScore<state.value.PlayerScoreB){__state.value=__state.value.copy(bestScore =state.value.PlayerScoreB)}
        }
    }

    @Composable
    fun executeOnceA(){
        var hasExecutedOnce by remember { mutableStateOf(false) }
        LaunchedEffect(Unit) {
            if(state.value.timer){_state.value =_state.value.copy(PlayerScoreA = state.value.PlayerScoreA+ (state.value.timeLeftA/2))}
            if (!hasExecutedOnce) {
                __state.value.dataToBeSent = __state.value.dataReceived
                __state.value = __state.value.copy(
                    dataToBeSent = statee.value.dataToBeSent + "${state.value.PlayerAName}                               ${state.value.PlayerScoreA}\n"
                )
                __state.value = __state.value.copy(
                   dataReceived = statee.value.dataToBeSent
                )
                hasExecutedOnce = true
            }
            if(statee.value.bestScore<state.value.PlayerScoreA){__state.value=__state.value.copy(bestScore =state.value.PlayerScoreA)}
        }
    }

    @Composable
    fun refresh(){
        LaunchedEffect(Unit) {
            gameReset()
        }
    }

    fun button_click_01(context: Context){
        val mMediaPlayer = MediaPlayer.create(context, R.raw.button_click_01)
        mMediaPlayer.start()
    }
    fun button_click_02(context: Context){
        val mMediaPlayer = MediaPlayer.create(context, R.raw.button_click_02)
        mMediaPlayer.start()
    }
    fun button_click_03(context: Context){
        val mMediaPlayer = MediaPlayer.create(context, R.raw.button_click_03)
        mMediaPlayer.start()
    }
    fun powerup(context: Context){
        val mMediaPlayer = MediaPlayer.create(context, R.raw.powerup)
        mMediaPlayer.start()
    }

    fun vibration(context: Context){
        val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        val vibrationEffect2: VibrationEffect
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            vibrationEffect2 =
                VibrationEffect.createPredefined(VibrationEffect.EFFECT_CLICK)
            vibrator.cancel()
            vibrator.vibrate(vibrationEffect2)
        }
    }
}

