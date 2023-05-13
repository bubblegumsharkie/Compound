package com.countlesswrongs.compound.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GameSettings(
    val maxSumValue: Int,
    val minAmountOfRightAnswers: Int,
    val minPercentOfRightAnswers: Int,
    val gameTimeInSeconds: Int
) : Parcelable
