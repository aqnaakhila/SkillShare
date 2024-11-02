package com.example.skillsharehub.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.skillsharehub.R
import com.example.skillsharehub.models.ScheduleModel

class ScheduleAdapter(private val scheduleList: MutableList<ScheduleModel>) :
    RecyclerView.Adapter<ScheduleAdapter.ScheduleViewHolder>() {

    class ScheduleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        val creatorTextView: TextView = itemView.findViewById(R.id.creatorTextView)
        val timeTextView: TextView = itemView.findViewById(R.id.timeTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_schedule, parent, false)
        return ScheduleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {
        val scheduleItem = scheduleList[position]
        holder.titleTextView.text = scheduleItem.title
        holder.creatorTextView.text = "Created by: ${scheduleItem.creator}"
        holder.timeTextView.text = scheduleItem.time
    }

    override fun getItemCount(): Int {
        return scheduleList.size
    }
}