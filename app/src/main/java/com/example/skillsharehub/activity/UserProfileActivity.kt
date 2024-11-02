package com.example.skillsharehub

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.skillsharehub.models.UserModel
import de.hdodenhof.circleimageview.CircleImageView

class UserProfileActivity : AppCompatActivity() {

    private lateinit var profileImageView: CircleImageView
    private lateinit var userNameTextView: TextView
    private lateinit var userSkillsTextView: TextView
    private lateinit var userSchoolTextView: TextView
    private lateinit var historyButton: Button
    private lateinit var settingsButton: Button
    private lateinit var logoutButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        profileImageView = findViewById(R.id.profileImageView)
        userNameTextView = findViewById(R.id.userNameTextView)
        userSkillsTextView = findViewById(R.id.userSkillsTextView)
        userSchoolTextView = findViewById(R.id.userSchoolTextView)
        historyButton = findViewById(R.id.historyButton)
        settingsButton = findViewById(R.id.settingsButton)
        logoutButton = findViewById(R.id.logoutButton)

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
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}