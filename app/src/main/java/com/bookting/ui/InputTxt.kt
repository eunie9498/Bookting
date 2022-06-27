package com.bookting.ui

import android.R.attr.password
import android.content.Context
import android.text.InputType
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.bookting.R
import com.bookting.databinding.CustomInputTxtBinding


class InputTxt constructor(
    context: Context,
    attributeSet: AttributeSet? = null
) : ConstraintLayout(context, attributeSet) {

    val binding = CustomInputTxtBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        val a = context.obtainStyledAttributes(R.styleable.InputTxt)

        a.recycle()
    }

    fun getEt(): String {
        return binding.tvInput.text.toString()
    }

    fun setHt(hint: String) {
        binding.inputLayout.hint = hint
        if (hint == context.getString(R.string.pw))
            binding.tvInput.inputType =
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
    }

    fun setHtColor(color: Int) {
        binding.tvInput.setHintTextColor(ContextCompat.getColor(binding.root.context, color))
    }

    fun setTxtColor(color: Int) {
        binding.tvInput.setTextColor(ContextCompat.getColor(binding.root.context, color))
    }

    fun setErr(err: String) = with(binding) {
        inputLayout.boxStrokeColor = ContextCompat.getColor(binding.root.context, R.color.red800)
        inputLayout.helperText = err
        inputLayout.setHelperTextColor(
            ContextCompat.getColorStateList(
                binding.root.context,
                R.color.red900
            )
        )
    }
}