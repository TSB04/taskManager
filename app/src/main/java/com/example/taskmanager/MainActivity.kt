package com.example.taskmanager

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    // Mutable list of tasks
    private val tasks = mutableListOf<Task>()
    private lateinit var taskAdapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewTasks)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Initialize the adapter with the task list
        taskAdapter = TaskAdapter(tasks) { position -> deleteTask(position) }
        recyclerView.adapter = taskAdapter

        // Welcome Toast
        Toast.makeText(this, "Bienvenue dans l'application de gestion de tâches", Toast.LENGTH_SHORT).show()

        // Set up the FloatingActionButton to add new tasks
        val fabAddTask = findViewById<FloatingActionButton>(R.id.fabAddTask)
        fabAddTask.setOnClickListener {
            showAddTaskDialog()
        }
    }

    // Function to show the Add Task dialog
    private fun showAddTaskDialog() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_task, null)
        val editTextTaskName = dialogView.findViewById<EditText>(R.id.editTextTaskName)
        val editTextCategory = dialogView.findViewById<EditText>(R.id.editTextCategory)

        val dialog = AlertDialog.Builder(this)
            .setTitle("Ajouter une tâche")
            .setView(dialogView)
            .setPositiveButton("Ajouter", null) // Set listener manually later
            .setNegativeButton("Annuler", null)
            .create()

        dialog.show()

        // Set custom listener for the positive button
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
            val taskName = editTextTaskName.text.toString().trim()
            val category = editTextCategory.text.toString().trim()

            if (taskName.isNotEmpty()) {
                addNewTask(Task(taskName, category.ifEmpty { "Général" }))
                dialog.dismiss() // Close the dialog if input is valid
            } else {
                Toast.makeText(this, "Le nom de la tâche est requis", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Function to add a new task
    private fun addNewTask(task: Task) {
        tasks.add(task)
        taskAdapter.notifyItemInserted(tasks.size - 1)
        Log.d("TaskManager", "Task added: $task")
    }

    // Function to delete a task
    private fun deleteTask(position: Int) {
        if (position in tasks.indices) {
            tasks.removeAt(position)
            taskAdapter.notifyItemRemoved(position)
            Toast.makeText(this, "Tâche supprimée", Toast.LENGTH_SHORT).show()
            Log.d("TaskManager", "Task deleted at position: $position")
        }
    }
}
