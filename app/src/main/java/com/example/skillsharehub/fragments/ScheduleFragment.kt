package com.example.skillsharehub.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.skillsharehub.R
import com.example.skillsharehub.adapters.ScheduleAdapter
import com.example.skillsharehub.models.ScheduleModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ScheduleFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ScheduleAdapter
    private val scheduleList: MutableList<ScheduleModel> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_schedule, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Load schedule list from SharedPreferences
        loadScheduleList()

        adapter = ScheduleAdapter(scheduleList)
        recyclerView.adapter = adapter

        return view
    }

    private fun loadScheduleList() {
        val sharedPreferences = requireContext().getSharedPreferences("SchedulePrefs", Context.MODE_PRIVATE)
        val gson = Gson()
        val scheduleListType = object : TypeToken<MutableList<ScheduleModel>>() {}.type

        // Get the schedule list from SharedPreferences
        val scheduleListJson = sharedPreferences.getString("scheduleList", "[]")
        val loadedScheduleList: MutableList<ScheduleModel> = gson.fromJson(scheduleListJson, scheduleListType)

        // Update the schedule list
        scheduleList.clear()
        scheduleList.addAll(loadedScheduleList)
    }
}