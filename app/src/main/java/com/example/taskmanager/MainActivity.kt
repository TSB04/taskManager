package com.example.taskmanager

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

//    private val defaultTask = Task("Task 1", "General")

    private val tasks = mutableListOf<Task>()
    private lateinit var taskAdapter: TaskAdapter
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        this.addNewTask(defaultTask)

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("TaskPrefs", MODE_PRIVATE)
        if (sharedPreferences.contains("tasks")) {
            Log.d("TaskManager", "Tasks found in SharedPreferences")
        } else {
            Log.d("TaskManager", "No tasks found in SharedPreferences")

        }

        // Load tasks and set up RecyclerView
        lifecycleScope.launch(Dispatchers.IO) {
            loadTasks()
            withContext(Dispatchers.Main) { setupRecyclerView() }
        }

        // Set up FAB for adding new tasks
        val fabAddTask = findViewById<FloatingActionButton>(R.id.fabAddTask)
        fabAddTask.setOnClickListener { showAddTaskDialog() }
    }

    private fun setupRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewTasks)
        recyclerView.layoutManager = LinearLayoutManager(this)
        taskAdapter = TaskAdapter(tasks) { position -> deleteTask(position) }
        recyclerView.adapter = taskAdapter
    }

    private fun showAddTaskDialog() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_task, null)
        val editTextTaskName = dialogView.findViewById<EditText>(R.id.editTextTaskName)
        val editTextCategory = dialogView.findViewById<EditText>(R.id.editTextCategory)

        AlertDialog.Builder(this)
            .setTitle("Add Task")
            .setView(dialogView)
            .setPositiveButton("Add") { _, _ ->
                val taskName = editTextTaskName.text.toString().trim()
                val category = editTextCategory.text.toString().trim()

                if (taskName.isNotEmpty()) {
                    addNewTask(Task(taskName, category.ifEmpty { "General" }))
                } else {
                    Log.d("TaskManager", "Task name is required")
                }
            }
            .setNegativeButton("Cancel", null)
            .create()
            .show()
    }

    private fun addNewTask(task: Task) {
        tasks.add(task)
        lifecycleScope.launch(Dispatchers.Main) {
            taskAdapter.notifyItemInserted(tasks.size - 1)
        }
        saveTasks()
    }

    private fun deleteTask(position: Int) {
        if (position in tasks.indices) {
            tasks.removeAt(position)
            lifecycleScope.launch(Dispatchers.Main) {
                taskAdapter.notifyItemRemoved(position)
            }
            saveTasks()
        }
    }

    private fun saveTasks() {
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val taskJson = Task.serialize(tasks)
                sharedPreferences.edit().putString("tasks", taskJson).apply()
                Log.d("TaskManager", "Tasks saved successfully" + taskJson)
            } catch (e: Exception) {
                Log.e("TaskManager", "Error saving tasks", e)
            }
        }
    }

    private fun loadTasks() {
        try {
            val taskData = sharedPreferences.getString("tasks", "[]")
            val deserializedTasks = Task.deserialize(taskData ?: "[]")
            tasks.clear()
            tasks.addAll(deserializedTasks)
        } catch (e: Exception) {
            Log.e("TaskManager", "Error loading tasks", e)
        }
    }
}