package com.example.taskmanager

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TaskAdapter(
    private val tasks: List<Task>,
    private val onTaskLongClick: (Int) -> Unit
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasks[position]
        holder.bind(task, onTaskLongClick)
    }

    override fun getItemCount() = tasks.size

    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textViewTaskName: TextView = itemView.findViewById(R.id.editTextTaskName)
        private val textViewCategory: TextView = itemView.findViewById(R.id.editTextCategory)

        fun bind(task: Task, onTaskLongClick: (Int) -> Unit) {
            textViewTaskName.text = task.name
            textViewCategory.text = task.category

            // Handle long-click for deletion
            itemView.setOnLongClickListener {
                onTaskLongClick(adapterPosition)
                true
            }
        }
    }
}
