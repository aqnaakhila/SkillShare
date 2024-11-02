package com.example.skillsharehub.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.example.skillsharehub.R
import com.example.skillsharehub.models.ClassItem

class SummaryDialog(private val classItem: ClassItem) : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dialog_summary, container, false)

        val summaryTextView: TextView = view.findViewById(R.id.summaryTextView)
        val summaryText = """
            Materi Pembelajaran: ${classItem.name}
            Resource Sharing: Available
            Video Conference: Available
        """.trimIndent()
        summaryTextView.text = summaryText

        return view
    }
}