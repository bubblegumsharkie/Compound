package com.countlesswrongs.compound.domain.entity

import java.io.Serializable

data class GameResult(
    val didWin: Boolean,
    val amountOfRightAnswers: Int,
    val countOfQuestions: Int,
    val gameSettings: GameSettings
) : Serializable
