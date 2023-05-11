package com.countlesswrongs.compound.domain.entity

data class GameResult(
    val didWin: Boolean,
    val amountOfRightAnswers: Int,
    val countOfQuestions: Int,
    val gameSettings: GameSettings
)
