package com.countlesswrongs.compound.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.countlesswrongs.compound.R
import com.countlesswrongs.compound.databinding.FragmentGameFinishedBinding
import com.countlesswrongs.compound.domain.entity.GameResult


class GameFinishedFragment : Fragment() {

    private val args by navArgs<GameFinishedFragmentArgs>()
    private val gameResult by lazy {
        args.gameResult
    }

    private var _binding: FragmentGameFinishedBinding? = null
    private val binding: FragmentGameFinishedBinding
        get() = _binding ?: throw RuntimeException("FragmentGameFinishedBinding is NULL")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameFinishedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpFragmentControls()
        setEmoji()
        showResults()
    }

    private fun showResults() {
        with(binding) {
            tvRequiredAnswers.text = String.format(
                resources.getString(R.string.tv_required_score),
                gameResult.gameSettings.minAmountOfRightAnswers.toString()
            )
            tvScoreAnswers.text = String.format(
                resources.getString(R.string.tv_score_answers),
                gameResult.amountOfRightAnswers.toString()
            )
            tvRequiredPercentage.text = String.format(
                resources.getString(R.string.tv_required_percentage),
                gameResult.gameSettings.minPercentOfRightAnswers.toString()
            )
            tvScorePercentage.text = String.format(
                resources.getString(R.string.tv_score_percentage),
                gameResult.percentageOfRightAnswers.toString()
            )
        }
    }

    private fun setEmoji() {
        val drawable = if (gameResult.didWin) {
            ContextCompat.getDrawable(requireContext(), R.drawable.ic_happy)
        } else {
            ContextCompat.getDrawable(requireContext(), R.drawable.ic_sad)
        }
        binding.ivEmojiResult.setImageDrawable(drawable)
    }

    private fun setUpFragmentControls() {
        binding.buttonRetry.setOnClickListener {
            retryGame()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun retryGame() {
        findNavController().popBackStack()
    }

    companion object {

        const val KEY_GAME_RESULT = "game_result"

        fun newInstance(gameResult: GameResult): GameFinishedFragment {
            return GameFinishedFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_GAME_RESULT, gameResult)
                }
            }
        }
    }

}
