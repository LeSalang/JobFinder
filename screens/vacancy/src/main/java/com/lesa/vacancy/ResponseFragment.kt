package com.lesa.vacancy

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.DialogFragment

class ResponseFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireContext())
        val inflater = requireActivity().layoutInflater
        val view = inflater.inflate(R.layout.fragment_response, null)
        builder.setView(view)
        val dialog = builder.create()
        dialog.setCanceledOnTouchOutside(true)

        val addCoverLetterButton = view.findViewById<TextView>(R.id.responseAddCoverLetter)
        val coverLetterEditText = view.findViewById<EditText>(R.id.responseYourCoverLetter)

        addCoverLetterButton.setOnClickListener {
            coverLetterEditText.visibility = View.VISIBLE
            addCoverLetterButton.visibility = View.GONE
        }

        return dialog
    }
}
