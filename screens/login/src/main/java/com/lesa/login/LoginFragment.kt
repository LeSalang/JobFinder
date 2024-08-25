package com.lesa.login

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.lesa.login.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {
    private val binding: FragmentLoginBinding by viewBinding()
    private val viewModel: LoginViewModel by viewModels()

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        observeViewModel()
    }

    private fun setupViews() {
        val resumeButton = binding.buttonResume
        val emailInputField = binding.editTextEmail
        val clearIcon = binding.iconClose

        setupEditText(emailInputField)
        setupCloseButton(clearIcon, emailInputField)
        setupResumeButton(resumeButton, emailInputField)
    }

    private fun setupEditText(editText: EditText) {
        editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                viewModel.onEmailTextChanged(s.isNullOrEmpty())
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    private fun setupCloseButton(closeButton: ImageView, editText: EditText) {
        closeButton.setOnClickListener {
            editText.text.clear()
        }
    }

    private fun setupResumeButton(resumeButton: View, editText: EditText) {
        resumeButton.setOnClickListener {
            viewModel.onResumeButtonClick(editText.text.toString())
        }
    }

    private fun observeViewModel() {
        viewModel.isEmailValid.observe(
            viewLifecycleOwner
        ) { isValid ->
            binding.errorEmailText.visibility = if (isValid) View.GONE else View.VISIBLE
            binding.containerEmail.setBackgroundResource(
                if (isValid) {
                    com.lesa.ui_kit.R.drawable.placeholder_text_bg
                } else {
                    com.lesa.ui_kit.R.drawable.placeholder_text_bg_error
                }
            )

            if (isValid) {
                // TODO: Send email to the next screen
            }
        }

        viewModel.isEmailEmpty.observe(
            viewLifecycleOwner
        ) { isEmpty ->
            updateIconsVisibility(isEmpty, binding.iconEmail, binding.iconClose)
        }
    }

    private fun updateIconsVisibility(
        isTextEmpty: Boolean,
        iconEmail: ImageView,
        iconClose: ImageView
    ) {
        iconEmail.visibility = if (isTextEmpty) View.VISIBLE else View.GONE
        iconClose.visibility = if (isTextEmpty) View.GONE else View.VISIBLE
    }
}
