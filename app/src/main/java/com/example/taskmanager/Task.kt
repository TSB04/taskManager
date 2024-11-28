package com.example.taskmanager

import org.json.JSONArray
import org.json.JSONObject

data class Task(val name: String, val category: String) {
//    companion object {
//
//        /**
//         * Serializes a list of Task objects into a JSON string.
//         */
//        fun serialize(tasks: List<Task>): String {
//            return try {
//                JSONArray(tasks.map { task ->
//                    JSONObject().apply {
//                        put("name", task.name)
//                        put("category", task.category)
//                    }
//                }).toString()
//            } catch (e: Exception) {
//                e.printStackTrace()
//                "[]"
//            }
//        }
//
//        /**
//         * Deserializes a JSON string into a list of Task objects.
//         */
//        fun deserialize(taskData: String): List<Task> {
//            return try {
//                val jsonArray = JSONArray(taskData)
//                List(jsonArray.length()) { i ->
//                    jsonArray.getJSONObject(i).let { jsonObject ->
//                        Task(
//                            name = jsonObject.getString("name"),
//                            category = jsonObject.optString("category", "General")
//                        )
//                    }
//                }
//            } catch (e: Exception) {
//                e.printStackTrace()
//                emptyList()
//            }
//        }
//    }
}
