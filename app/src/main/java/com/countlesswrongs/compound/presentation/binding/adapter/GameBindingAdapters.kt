package com.countlesswrongs.compound.presentation.binding.adapter

import android.content.res.ColorStateList
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter

@BindingAdapter("setNumberAsString")
fun bindQuestion(textView: TextView, question: Int) {
    textView.text = question.toString()
}

@BindingAdapter("setColorRunningResults")
fun bindColorRunningResults(view: TextView, argument: Boolean) {
    view.setTextColor(getColorByState(view, argument))
}

@BindingAdapter("setProgressBarProgress")
fun bindProgressBarProgress(view: ProgressBar, argument: Int) {
    view.setProgress(argument, true)
}

@BindingAdapter("setProgressBarColor")
fun bindProgressBarColor(view: ProgressBar, argument: Boolean) {
    view.progressTintList = ColorStateList.valueOf(getColorByState(view, argument))
}

@BindingAdapter("setOnOptionClickListener")
fun bindOnOptionClickListener(view: TextView, clickListener: OnOptionClickListener) {
    view.setOnClickListener {
        clickListener.onOptionClick(view.text.toString().toInt())
    }
}

private fun getColorByState(view: View, it: Boolean): Int {
    val colorResId = if (it) {
        android.R.color.holo_green_light
    } else {
        android.R.color.holo_red_light
    }
    return ContextCompat.getColor(view.context, colorResId)
}

interface OnOptionClickListener {
    fun onOptionClick(option: Int)
}