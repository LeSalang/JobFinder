package com.lesa.verification

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.lesa.navigation.Navigator
import com.lesa.search.SearchFragment
import com.lesa.verification.databinding.FragmentVerificationBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

private const val CODE_LENGTH = 4

@AndroidEntryPoint
class VerificationFragment : Fragment(R.layout.fragment_verification) {
    private val binding: FragmentVerificationBinding by viewBinding()

    @Inject
    lateinit var navigator: Navigator

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupConfirmButton()
        setupEmailInTitle()
        onConfirmButtonClicked()
    }

    private fun onConfirmButtonClicked() {
        binding.buttonConfirm.setOnClickListener {
            if (binding.otpView.getOtp().length == CODE_LENGTH) {
                navigator.newRootScreen(
                    FragmentScreen {
                        SearchFragment()
                    }
                )
            }
        }
    }

    private fun setupEmailInTitle() {
        val email = arguments?.getString(VERIFICATION_KEY)
        if (email != null) {
            binding.emailTitle.text = getString(R.string.code_sent_message, email)
        }
    }

    private fun setupConfirmButton() {
        val confirmButton = binding.buttonConfirm
        confirmButton.setBackgroundResource(com.lesa.ui_kit.R.drawable.btn_rounded_blue_bg_disabled)
        confirmButton.setTextColor(resources.getColor(com.lesa.ui_kit.R.color.grey_4, null))

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
