package com.countlesswrongs.compound.domain.entity

import java.io.Serializable

data class GameSettings (
    val maxSumValue: Int,
    val minAmountOfRightAnswers: Int,
    val minPercentOfRightAnswers: Int,
    val gameTimeInSeconds: Int
) : Serializable
