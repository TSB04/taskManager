package com.example.taskmanager

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

//class TaskAdapter(
//    private val tasks: List<Task>,
//    private val onTaskLongClick: (Int) -> Unit
//) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
//        return TaskViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
//        val task = tasks[position]
//        holder.bind(task, onTaskLongClick)
//    }
//
//    override fun getItemCount() = tasks.size
//
//    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        private val textViewTaskName: TextView = itemView.findViewById(R.id.editTextTaskName)
//        private val textViewCategory: TextView = itemView.findViewById(R.id.editTextCategory)
//
//        fun bind(task: Task, onTaskLongClick: (Int) -> Unit) {
//            textViewTaskName.text = task.name
//            textViewCategory.text = task.category
//
//            // Handle long-click for deletion
//            itemView.setOnLongClickListener {
//                onTaskLongClick(adapterPosition)
//                true
//            }
//        }
//    }
//}


class TaskAdapter(
    private val tasks: List<Task>,
    private val onDeleteTask: (Int) -> Unit
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    inner class TaskViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val taskName = view.findViewById<TextView>(R.id.textTaskName)
        val taskCategory = view.findViewById<TextView>(R.id.textTaskCategory)
        val deleteButton = view.findViewById<ImageButton>(R.id.buttonDeleteTask)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasks[position]
        holder.taskName.text = task.name
        holder.taskCategory.text = task.category
        holder.deleteButton.setOnClickListener {
            onDeleteTask(position)
        }
    }

    override fun getItemCount() = tasks.size
}

