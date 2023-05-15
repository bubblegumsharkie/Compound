package com.countlesswrongs.compound.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GameResult(
    val didWin: Boolean,
    val amountOfRightAnswers: Int,
    val amountOfQuestions: Int,
    val percentageOfRightAnswers: Int,
    val gameSettings: GameSettings
) : Parcelable
