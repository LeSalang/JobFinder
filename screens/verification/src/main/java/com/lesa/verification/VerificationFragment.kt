package com.lesa.verification

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.lesa.verification.databinding.FragmentVerificationBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VerificationFragment : Fragment(R.layout.fragment_verification) {
    private val binding: FragmentVerificationBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupConfirmButton()
    }

    private fun setupConfirmButton() {
        val confirmButton = binding.buttonConfirm

        binding.otpView.setOnOtpChangedListener { allFilled ->
            val buttonBackground = if (allFilled) {
                com.lesa.ui_kit.R.drawable.btn_rounded_blue_bg_active
            } else {
                com.lesa.ui_kit.R.drawable.btn_rounded_blue_bg_disabled
            }
            val buttonTextColor = if (allFilled) {
                com.lesa.ui_kit.R.color.white
            } else {
                com.lesa.ui_kit.R.color.grey_4
            }

            confirmButton.setBackgroundResource(buttonBackground)
            confirmButton.setTextColor(resources.getColor(buttonTextColor, null))
        }
    }
}
