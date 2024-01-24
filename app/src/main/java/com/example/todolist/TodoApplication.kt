package com.example.todolist

import android.app.Application

class TodoApplication: Application() {
    private val database by lazy { AssignmentItemDatabase.getDatabase(this) }
    val repository by lazy { AssignmentItemRepository(database.assignmentItemDao()) }
}
