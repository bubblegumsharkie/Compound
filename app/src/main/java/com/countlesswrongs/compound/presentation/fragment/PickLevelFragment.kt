package com.countlesswrongs.compound.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.countlesswrongs.compound.databinding.FragmentPickLevelBinding


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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
