package com.example.taskmanager

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import android.util.Log

class TaskProvider : ContentProvider() {

    override fun onCreate(): Boolean {
        // Initialization logic (if needed)
        Log.d("TaskProvider", "TaskProvider initialized")
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        Log.d("TaskProvider", "Query not implemented")
        return null
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        Log.d("TaskProvider", "Insert not implemented")
        return null
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        Log.d("TaskProvider", "Update not implemented")
        return 0
    }

    override fun delete(
        uri: Uri,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        Log.d("TaskProvider", "Delete not implemented")
        return 0
    }

    override fun getType(uri: Uri): String? {
        Log.d("TaskProvider", "GetType not implemented")
        return null
    }
}
