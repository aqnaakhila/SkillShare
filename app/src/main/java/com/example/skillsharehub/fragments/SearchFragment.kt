package com.example.skillsharehub.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import com.example.skillsharehub.activity.DeskripsiActivity
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.skillsharehub.R
import com.example.skillsharehub.adapters.ClassAdapter
import com.example.skillsharehub.models.ClassItem

class SearchFragment : Fragment() {

    private lateinit var searchEditText: EditText
    private lateinit var searchButton: Button
    private lateinit var searchRecyclerView: RecyclerView
    private lateinit var adapter: ClassAdapter
    private val classList: MutableList<ClassItem> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_search, container, false)

        searchEditText = view.findViewById(R.id.searchEditText)
        searchButton = view.findViewById(R.id.searchButton)
        searchRecyclerView = view.findViewById(R.id.searchRecyclerView)
        searchRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Dummy data for classList
        classList.add(ClassItem("Fuzzy Clustering", "admin", "Machine Learning"))
        classList.add(ClassItem("Basis data fuzzy", "admin", "Machine Learning"))
        classList.add(ClassItem("Operator Fuzyy", "admin", "Machine Learning"))
        classList.add(ClassItem("Fuzzy Inference Systems", "admin", "Machine Learning"))
        classList.add(ClassItem("Pendahuluan", "admin", "Machine Learning"))

        adapter = ClassAdapter(classList) { classItem ->
            val intent = Intent(requireContext(), DeskripsiActivity::class.java)
            intent.putExtra("classItem", classItem)
            startActivity(intent)
        }
        searchRecyclerView.adapter = adapter

        searchButton.setOnClickListener {
            val query = searchEditText.text.toString()
            if (query.isNotEmpty()) {
                // Simulate search functionality
                val filteredList = classList.filter { it.name.contains(query, ignoreCase = true) }
                adapter.updateList(filteredList)
            } else {
                Toast.makeText(requireContext(), "Please enter a search query", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }
}