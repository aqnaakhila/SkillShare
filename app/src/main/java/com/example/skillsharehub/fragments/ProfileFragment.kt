package com.example.skillsharehub.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.skillsharehub.LoginActivity
import com.example.skillsharehub.R
import com.example.skillsharehub.models.UserModel
import de.hdodenhof.circleimageview.CircleImageView

class ProfileFragment : Fragment() {

    private lateinit var profileImageView: CircleImageView
    private lateinit var userNameTextView: TextView
    private lateinit var userSkillsTextView: TextView
    private lateinit var userSchoolTextView: TextView
    private lateinit var historyButton: Button
    private lateinit var settingsButton: Button
    private lateinit var logoutButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        profileImageView = view.findViewById(R.id.profileImageView)
        userNameTextView = view.findViewById(R.id.userNameTextView)
        userSkillsTextView = view.findViewById(R.id.userSkillsTextView)
        userSchoolTextView = view.findViewById(R.id.userSchoolTextView)
        historyButton = view.findViewById(R.id.historyButton)
        settingsButton = view.findViewById(R.id.settingsButton)
        logoutButton = view.findViewById(R.id.logoutButton)

        // Dummy data for user profile
        val user = UserModel("John Doe", listOf("Photography", "Digital Marketing"), "University of Example")

        userNameTextView.text = user.name
        userSkillsTextView.text = "Skills: ${user.skills.joinToString(", ")}"
        userSchoolTextView.text = "School: ${user.school}"

        historyButton.setOnClickListener {
            // Add logic to view history
        }

        settingsButton.setOnClickListener {
            // Add logic to open settings
        }

        logoutButton.setOnClickListener {
            // Add logic to logout
            val intent = Intent(requireContext(), LoginActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }

        return view
    }
}