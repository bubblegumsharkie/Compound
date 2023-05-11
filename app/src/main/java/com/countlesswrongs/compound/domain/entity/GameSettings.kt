package com.countlesswrongs.compound.domain.entity

data class GameSettings (
    val maxSumValue: Int,
    val minAmountOfRightAnswers: Int,
    val minPercentOfRightAnswers: Int,
    val gameTimeInSeconds: Int
)
