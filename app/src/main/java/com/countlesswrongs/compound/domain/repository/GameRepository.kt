package com.countlesswrongs.compound.domain.repository

import com.countlesswrongs.compound.domain.entity.GameSettings
import com.countlesswrongs.compound.domain.entity.Level
import com.countlesswrongs.compound.domain.entity.Question

interface GameRepository {

    fun generateQuestion(
        maxSumValue: Int,
        countOfOptions: Int
    ): Question

    fun getGameSettings(level: Level): GameSettings

}
