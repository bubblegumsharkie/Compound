package com.countlesswrongs.compound.presentation.viewmodel

import android.app.Application
import android.os.CountDownTimer
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.countlesswrongs.compound.R
import com.countlesswrongs.compound.data.repository.GameRepositoryImpl
import com.countlesswrongs.compound.domain.entity.GameResult
import com.countlesswrongs.compound.domain.entity.GameSettings
import com.countlesswrongs.compound.domain.entity.Level
import com.countlesswrongs.compound.domain.entity.Question
import com.countlesswrongs.compound.domain.usecase.GenerateQuestionUseCase
import com.countlesswrongs.compound.domain.usecase.GetGameSettingsUseCase

class GameViewModel(application: Application) : AndroidViewModel(application) {

    private lateinit var gameSettings: GameSettings
    private lateinit var level: Level

    private val context = application
    private val repository = GameRepositoryImpl

    private val _formattedTime = MutableLiveData<String>()
    val formattedTime: LiveData<String>
        get() = _formattedTime

    private val _question = MutableLiveData<Question>()
    val question: LiveData<Question>
        get() = _question

    private var amountOfCorrectAnswers = 0
    private var amountOfQuestions = 0

    private val _percentageOfCorrectAnswers = MutableLiveData<Int>()
    val percentageOfCorrectAnswers: LiveData<Int>
        get() = _percentageOfCorrectAnswers

    private val _progressAnswers = MutableLiveData<String>()
    val progressAnswers: LiveData<String>
        get() = _progressAnswers

    private val _enoughToWinAmountOfCorrectAnswers = MutableLiveData<Boolean>()
    val enoughToWinAmountOfCorrectAnswers: LiveData<Boolean>
        get() = _enoughToWinAmountOfCorrectAnswers

    private val _enoughToWinPercentageOfCorrectAnswers = MutableLiveData<Boolean>()
    val enoughToWinPercentageOfCorrectAnswers: LiveData<Boolean>
        get() = _enoughToWinPercentageOfCorrectAnswers

    private val _minPercentage = MutableLiveData<Int>()
    val minPercentage: LiveData<Int>
        get() = _minPercentage

    private val _gameResult = MutableLiveData<GameResult>()
    val gameResult: LiveData<GameResult>
        get() = _gameResult

    private val generateQuestionUseCase = GenerateQuestionUseCase(repository)
    private var getGameSettingsUseCase = GetGameSettingsUseCase(repository)

    private var timer: CountDownTimer? = null

    fun startGame(level: Level) {
        setupGame(level)
        generateQuestion()
        startTimer()
        updateGameProgress()
    }

    fun userPickedAnswer(answer: Int) {
        checkAnswer(answer)
        updateGameProgress()
        generateQuestion()
    }

    private fun checkAnswer(answer: Int) {
        val correctAnswer = question.value?.correctAnswer
        if (answer == correctAnswer) {
            amountOfCorrectAnswers++
        }
    }

    private fun updateGameProgress() {
        val percent = calculatePercentageOfCorrectAnswers()
        _percentageOfCorrectAnswers.value = percent
        _progressAnswers.value = String.format(
            context.resources.getString(R.string.progress_answers),
            amountOfCorrectAnswers,
            gameSettings.minAmountOfRightAnswers
        )

        _enoughToWinAmountOfCorrectAnswers.value =
            amountOfCorrectAnswers >= gameSettings.minAmountOfRightAnswers

        _enoughToWinPercentageOfCorrectAnswers.value =
            percent >= gameSettings.minPercentOfRightAnswers

    }

    private fun calculatePercentageOfCorrectAnswers(): Int {
        if (amountOfQuestions == 0) {
            return 0
        }
        return ((amountOfCorrectAnswers / amountOfQuestions.toDouble()) * 100).toInt()
    }

    private fun setupGame(level: Level) {
        this.level = level
        this.gameSettings = getGameSettingsUseCase(level)
        _minPercentage.value = gameSettings.minPercentOfRightAnswers
    }

    private fun startTimer() {
        timer = object : CountDownTimer(
            gameSettings.gameTimeInSeconds * MILLIS_IN_SECOND,
            MILLIS_IN_SECOND
        ) {
            override fun onTick(p0: Long) {
                _formattedTime.value = formatTime(p0)
            }

            override fun onFinish() {
                finishGame()
            }

        }
        timer?.start()
    }

    private fun generateQuestion() {
        _question.value = generateQuestionUseCase(gameSettings.maxSumValue)
        amountOfQuestions++
    }

    private fun formatTime(p0: Long): String {
        val seconds = p0 / MILLIS_IN_SECOND
        val minutes = seconds / SECONDS_IN_MINUTE
        val secondsLeft = seconds - (minutes * SECONDS_IN_MINUTE)
        return String.format("%02d:%02d", minutes, secondsLeft)
    }

    private fun finishGame() {
        _gameResult.value = GameResult(
            enoughToWinAmountOfCorrectAnswers.value == true && enoughToWinPercentageOfCorrectAnswers.value == true,
            amountOfCorrectAnswers,
            amountOfQuestions,
            percentageOfCorrectAnswers.value ?: 0,
            gameSettings
        )
    }

    override fun onCleared() {
        super.onCleared()
        timer?.cancel()
    }

    companion object {
        private const val MILLIS_IN_SECOND = 1000L
        private const val SECONDS_IN_MINUTE = 60
    }

}
