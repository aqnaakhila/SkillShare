package com.example.skillsharehub.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.skillsharehub.R
import com.example.skillsharehub.models.ClassItem

class ClassAdapter(
    private var classList: List<ClassItem>,
    private val onItemClick: (ClassItem) -> Unit
) : RecyclerView.Adapter<ClassAdapter.ClassViewHolder>() {

    class ClassViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val className: TextView = itemView.findViewById(R.id.className)
        val instructorName: TextView = itemView.findViewById(R.id.instructorName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClassViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_class, parent, false)
        return ClassViewHolder(view)
    }

    override fun onBindViewHolder(holder: ClassViewHolder, position: Int) {
        val classItem = classList[position]
        holder.className.text = classItem.name
        holder.instructorName.text = "By ${classItem.instructor}"

        holder.itemView.setOnClickListener {
            onItemClick(classItem)
        }
    }

    override fun getItemCount(): Int {
        return classList.size
    }

    fun updateList(newList: List<ClassItem>) {
        classList = newList
        notifyDataSetChanged()
    }
}