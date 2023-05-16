package com.countlesswrongs.compound.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.countlesswrongs.compound.databinding.FragmentPickLevelBinding
import com.countlesswrongs.compound.domain.entity.Level


class PickLevelFragment : Fragment() {

    private var _binding: FragmentPickLevelBinding? = null
    private val binding: FragmentPickLevelBinding
        get() = _binding ?: throw RuntimeException("FragmentPickLevelBinding is NULL")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPickLevelBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            buttonLevelTest.setOnClickListener {
                launchGameFragment(Level.TEST)
            }
            buttonLevelEasy.setOnClickListener {
                launchGameFragment(Level.EASY)
            }
            buttonLevelNormal.setOnClickListener {
                launchGameFragment(Level.NORMAL)
            }
            buttonLevelHard.setOnClickListener {
                launchGameFragment(Level.HARD)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun launchGameFragment(level: Level) {
        findNavController().navigate(
            PickLevelFragmentDirections.actionPickLevelFragmentToGameFragment(level)
        )
    }

}
