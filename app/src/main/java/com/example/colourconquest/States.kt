package com.example.colourconquest

data class GameStates (

    var PlayerAName: String = "",
    var PlayerBName: String = "",
    var timer:Boolean = false,
    var gridSize:Int = 10,
    var bestOf: Int = 1,
    var computerMode:Boolean=false,

    var whichPlayer: Boolean = true, //TRUE - BLUE ; FALSE - RED
    var isInitialA: Boolean =true,
    var isInitialB: Boolean =true,
    var BoxValuesA: List<Int> = List(gridSize*gridSize){0},
    var BoxValuesB: List<Int> = List(gridSize*gridSize){0},
    var overTookA:Int = 0,
    var overTookB:Int = 0,
    var PlayerScoreA: Int = BoxValuesA.sum() + overTookA,
    var PlayerScoreB: Int = BoxValuesB.sum() + overTookB,
    var timeLeftA : Int = 30,
    var timeLeftB : Int = 30,
    var timerPaused: Boolean = false,
    var bestOfA: Int = 0,
    var bestOfB:Int = 0,
    var matchCount: Int = 1,

    var gameOver: Boolean = false,
    var poweruptwoA: Boolean = false,
    var poweruptwoB: Boolean = false,
    var poweruptimerA: Boolean = false,
    var poweruptimerB: Boolean = false,
    var doubleInitial : Boolean =false,
    var doubleInitialCount : Int = 0
)

data class Record(
    var bestScore:Int = 0,
    var dataToBeSent: String = "",
    var dataReceived: String = "",
)