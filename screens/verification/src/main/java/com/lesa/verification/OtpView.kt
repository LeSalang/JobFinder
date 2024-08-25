package com.lesa.verification

import android.content.Context
import android.text.Editable
import android.text.InputFilter
import android.text.InputType
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import androidx.core.content.ContextCompat

class OtpView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val editTexts: Array<EditText>
    private var submitButton: Button? = null
    private var onOtpChangedListener: ((Boolean) -> Unit)? = null

    init {
        orientation = HORIZONTAL
        LayoutInflater.from(context).inflate(R.layout.view_otp, this, true)
        editTexts = arrayOf(
            findViewById(R.id.etDigit1),
            findViewById(R.id.etDigit2),
            findViewById(R.id.etDigit3),
            findViewById(R.id.etDigit4)
        )
        submitButton = findViewById(R.id.buttonConfirm)

        setupEditTexts()
    }

    private fun setupEditTexts() {
        for (i in editTexts.indices) {
            editTexts[i].apply {
                inputType = InputType.TYPE_CLASS_NUMBER
                filters = arrayOf(InputFilter.LengthFilter(1))

                addTextChangedListener(object : TextWatcher {
                    override fun afterTextChanged(s: Editable?) {
                        if (s.isNullOrEmpty()) return

                        if (s.length == 1) {
                            if (i < editTexts.size - 1) {
                                editTexts[i + 1].requestFocus()
                            } else {
                                hideCursorAndKeyboard()
                            }
                        } else if (s.length > 1) {
                            setText(s[0].toString())
                            setSelection(1)
                        }
                        checkOtpAndUpdateButton()
                    }

                    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
                })

                setOnFocusChangeListener { _, hasFocus ->
                    if (hasFocus && text.isNotEmpty()) {
                        setText("")
                        setSelection(0)
                    }
                    if (!hasFocus) {
                        isCursorVisible = false
                    }
                }

                setOnKeyListener { _, keyCode, event ->
                    if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_DEL) {
                        if (i > 0 && text.isEmpty()) {
                            editTexts[i - 1].requestFocus()
                        }
                        checkOtpAndUpdateButton()
                    }
                    false
                }
            }
        }
        checkOtpAndUpdateButton()
    }

    private fun hideCursorAndKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
        for (editText in editTexts) {
            editText.isCursorVisible = false
        }
    }

    private fun checkOtpAndUpdateButton() {
        val allFilled = editTexts.all { it.text.length == 1 }
        submitButton?.isEnabled = allFilled

        val drawable = ContextCompat.getDrawable(
            context,
            if (allFilled) {
                com.lesa.ui_kit.R.drawable.btn_rounded_blue_bg_active
            } else {
                com.lesa.ui_kit.R.drawable.btn_rounded_blue_bg_disabled
            }
        )
        submitButton?.background = drawable
        submitButton?.invalidate()

        onOtpChangedListener?.invoke(allFilled)
    }

    fun setOnOtpChangedListener(listener: (Boolean) -> Unit) {
        onOtpChangedListener = listener
    }

    fun getOtp(): String {
        return editTexts.joinToString("") { it.text.toString() }
    }
}
