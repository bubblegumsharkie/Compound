package com.countlesswrongs.compound.domain.usecase

import com.countlesswrongs.compound.domain.entity.GameSettings
import com.countlesswrongs.compound.domain.entity.Level
import com.countlesswrongs.compound.domain.repository.GameRepository

class GetGameSettingsUseCase(
    private val repository: GameRepository
) {

    operator fun invoke(level: Level): GameSettings {
        return repository.getGameSettings(level)
    }

}
