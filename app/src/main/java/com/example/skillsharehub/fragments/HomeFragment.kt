package com.example.skillsharehub.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.skillsharehub.activity.DeskripsiActivity
import com.example.skillsharehub.R
import com.example.skillsharehub.adapters.ClassAdapter
import com.example.skillsharehub.models.ClassItem
import com.example.skillsharehub.models.ScheduleModel
import com.google.android.material.textfield.TextInputEditText
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class HomeFragment : Fragment() {

    private lateinit var searchEditText: TextInputEditText
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ClassAdapter
    private val classList: MutableList<ClassItem> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        searchEditText = view.findViewById(R.id.searchEditText)
        recyclerView = view.findViewById(R.id.classes_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)

        // Dummy data for classList
        classList.add(ClassItem("Fuzzy Clustering", "admin", "Machine Learning"))
        classList.add(ClassItem("Basis data fuzzy", "admin", "Machine Learning"))
        classList.add(ClassItem("Operator Fuzyy", "admin", "Machine Learning"))
        classList.add(ClassItem("Fuzzy Inference Systems", "admin", "Machine Learning"))
        classList.add(ClassItem("Pendahuluan", "admin", "Machine Learning"))

        adapter = ClassAdapter(classList) { classItem ->
            val intent = Intent(requireContext(), DeskripsiActivity::class.java)
            intent.putExtra("classItem", classItem)
            addToSchedule(classItem)
            startActivity(intent)
        }
        recyclerView.adapter = adapter

        // Set "All Skills" as selected by default
        val allSkillsPill = view.findViewById<TextView>(R.id.allSkillsPill)
        allSkillsPill.isSelected = true

        // Add search logic
        searchEditText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == android.view.inputmethod.EditorInfo.IME_ACTION_SEARCH) {
                val query = searchEditText.text.toString()
                if (query.isNotEmpty()) {
                    val filteredList = classList.filter { it.name.contains(query, ignoreCase = true) }
                    adapter.updateList(filteredList)
                }
                true
            } else {
                false
            }
        }

        // Add category pills logic
        val categoryPills = listOf(
            view.findViewById<TextView>(R.id.allSkillsPill),
            view.findViewById<TextView>(R.id.popularPill),
            view.findViewById<TextView>(R.id.nearbyPill)
        )

        categoryPills.forEach { pill ->
            pill.setOnClickListener {
                categoryPills.forEach { it.isSelected = false }
                pill.isSelected = true

                val category = when (pill.id) {
                    R.id.allSkillsPill -> null
                    R.id.popularPill -> "Popular"
                    R.id.nearbyPill -> "Nearby"
                    else -> null
                }

                val filteredList = if (category == null) {
                    classList
                } else {
                    classList.filter { it.category == category }
                }

                adapter.updateList(filteredList)
            }
        }

        return view
    }

    private fun addToSchedule(classItem: ClassItem) {
        val sharedPreferences = requireContext().getSharedPreferences("SchedulePrefs", Context.MODE_PRIVATE)
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
        requireContext().toast("Added to schedule")
    }

    private fun Context.toast(message: String) {
        android.widget.Toast.makeText(this, message, android.widget.Toast.LENGTH_SHORT).show()
    }
}