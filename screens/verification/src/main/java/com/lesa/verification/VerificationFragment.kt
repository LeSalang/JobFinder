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
        setupEmailInTitle()
    }

    private fun setupEmailInTitle() {
        val email = arguments?.getString(VERIFICATION_KEY)
        if (email != null) {
            binding.emailTitle.text = getString(R.string.code_sent_message, email)
        }
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

    companion object {
        private const val VERIFICATION_KEY = "verification_key"

        fun getNewInstance(email: String): VerificationFragment {
            return VerificationFragment().apply {
                arguments = Bundle().apply {
                    putString(VERIFICATION_KEY, email)
                }
            }
        }
    }
}
