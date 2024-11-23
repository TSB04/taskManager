package com.example.taskmanager

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    // Mutable list of tasks (used to update tasks dynamically)
    private val tasks = mutableListOf("Acheter des courses", "Faire du sport", "Préparer la réunion")
    private lateinit var taskAdapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialiser le RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewTasks)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Initialize the adapter with the task list
        taskAdapter = TaskAdapter(tasks)
        recyclerView.adapter = taskAdapter

        // Toast de bienvenue
        Toast.makeText(this, "Bienvenue dans l'application de gestion de tâches", Toast.LENGTH_SHORT).show()

        // Set up the FloatingActionButton to add new tasks
        val fabAddTask = findViewById<FloatingActionButton>(R.id.fabAddTask)
        fabAddTask.setOnClickListener {
            addNewTask()
        }
    }

    // Function to add a new task
    private fun addNewTask() {
        // Add a new task to the list (You can modify this logic, e.g., prompt the user to enter a task name)
        tasks.add("Nouvelle tâche")

        // Notify the adapter that the data has changed
        taskAdapter.notifyItemInserted(tasks.size - 1)

        // Optional: Show a Toast to confirm task addition
        Toast.makeText(this, "Nouvelle tâche ajoutée", Toast.LENGTH_SHORT).show()
    }
}
