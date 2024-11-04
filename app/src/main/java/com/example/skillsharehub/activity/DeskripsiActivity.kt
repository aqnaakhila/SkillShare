package com.example.skillsharehub.activity

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.skillsharehub.R
import com.example.skillsharehub.models.ClassItem
import com.example.skillsharehub.models.ScheduleModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DeskripsiActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar
    private lateinit var titleTextView: TextView
    private lateinit var creatorTextView: TextView
    private lateinit var fileTextView: TextView
    private lateinit var viewButton: Button
    private lateinit var addButton: Button

    // HashMap to store URLs for each ClassItem
    private val urlMap = hashMapOf(
        "Fuzzy Clustering" to "https://docs.google.com/presentation/d/1mUX1YOjIoPQeYXBE0QGJs5_ArG6Fc8Q7/edit?usp=sharing&ouid=113638889799505294942&rtpof=true&sd=true",
        "Basis data fuzzy" to "https://docs.google.com/presentation/d/1JZ9OJ_d_yEmtLHZjwgkfPFy12RoEhYwq/edit?usp=sharing&ouid=113638889799505294942&rtpof=true&sd=true",
        "Operator Fuzyy" to "https://docs.google.com/presentation/d/1yoYQZxizRthrZ3U8CcW6yuwreDotDwFo/edit?usp=sharing&ouid=113638889799505294942&rtpof=true&sd=true",
        "Fuzzy Inference Systems" to "https://docs.google.com/presentation/d/1OS7z1ARE0LFwCDwoRRpA81RavzpcpeKk/edit?usp=sharing&ouid=113638889799505294942&rtpof=true&sd=true",
        "Pendahuluan" to "https://docs.google.com/presentation/d/1C5CulquGj1aewaLUjNUovEWcdZDSOV5u/edit?usp=sharing&ouid=113638889799505294942&rtpof=true&sd=true"
        // Add more entries as needed
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_deskripsi)

        toolbar = findViewById(R.id.toolbar)
        titleTextView = findViewById(R.id.titleTextView)
        creatorTextView = findViewById(R.id.creatorTextView)
        fileTextView = findViewById(R.id.fileTextView)
        viewButton = findViewById(R.id.viewButton)
        addButton = findViewById(R.id.addButton)

        // Set Toolbar as the ActionBar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        // Get the class item from the intent
        val classItem = intent.getParcelableExtra<ClassItem>("classItem")

        if (classItem != null) {
            titleTextView.text = classItem.name
            creatorTextView.text = "Created by: ${classItem.instructor}"
            fileTextView.text = "File: ${classItem.name}.pdf"

            // Get the URL from the HashMap
            val presentationUrl = urlMap[classItem.name]

            viewButton.setOnClickListener {
                // Open the URL in the browser or Google Slides app
                presentationUrl?.let { url ->
                    openUrl(url)
                } ?: run {
                    // Handle case where URL is not found
                    fileTextView.text = "URL not found for ${classItem.name}"
                }
            }

            addButton.setOnClickListener {
                // Add the class item to the schedule
                addToSchedule(classItem)
            }
        }
    }

    private fun openUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }

    private fun addToSchedule(classItem: ClassItem) {
        val sharedPreferences = getSharedPreferences("SchedulePrefs", Context.MODE_PRIVATE)
        val gson = Gson()
        val scheduleListType = object : TypeToken<MutableList<ScheduleModel>>() {}.type

        // Get the current schedule list from SharedPreferences
        val scheduleListJson = sharedPreferences.getString("scheduleList", "[]")
        val scheduleList: MutableList<ScheduleModel> = gson.fromJson(scheduleListJson, scheduleListType)

        // Add the new schedule item
        val newScheduleItem = ScheduleModel(classItem.name, classItem.instructor, "2023-10-15 10:00 AM") // You can set the time dynamically
        scheduleList.add(newScheduleItem)

        // Save the updated schedule list back to SharedPreferences
        val updatedScheduleListJson = gson.toJson(scheduleList)
        sharedPreferences.edit().putString("scheduleList", updatedScheduleListJson).apply()

        // Notify the user that the item has been added
        Toast.makeText(this, "Added to schedule", Toast.LENGTH_SHORT).show()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}