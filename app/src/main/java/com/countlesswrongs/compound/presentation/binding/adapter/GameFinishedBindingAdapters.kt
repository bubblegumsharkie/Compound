package com.countlesswrongs.compound.presentation.binding.adapter

import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.countlesswrongs.compound.R

@BindingAdapter("requiredAnswers")
fun bindRequiredAnswers(textView: TextView, amount: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.tv_required_score),
        amount
    )
}

@BindingAdapter("scoreAnswers")
fun bindScoreAnswers(textView: TextView, score: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.tv_score_answers),
        score
    )
}

@BindingAdapter("requiredPercentage")
fun bindRequiredPercentage(textView: TextView, percentage: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.tv_required_percentage),
        percentage
    )
}

@BindingAdapter("scorePercentage")
fun bindScorePercentage(textView: TextView, percentage: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.tv_score_percentage),
        percentage
    )
}

@BindingAdapter("setEmoji")
fun bindEmoji(imageView: ImageView, didWin: Boolean) {
    val drawable = if (didWin) {
        ContextCompat.getDrawable(imageView.context, R.drawable.ic_happy)
    } else {
        ContextCompat.getDrawable(imageView.context, R.drawable.ic_sad)
    }
    imageView.setImageDrawable(drawable)
}
