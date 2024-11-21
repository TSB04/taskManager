package com.example.taskmanager;

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialiser le RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewTasks)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = TaskAdapter(listOf("Acheter des courses", "Faire du sport", "Préparer la réunion"))

        // Toast de bienvenue
        Toast.makeText(this, "Bienvenue dans l'application de gestion de tâches", Toast.LENGTH_SHORT).show()
    }
}
