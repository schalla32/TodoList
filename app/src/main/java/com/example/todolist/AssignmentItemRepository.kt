package com.example.todolist

import androidx.annotation.WorkerThread
import androidx.room.Entity
import kotlinx.coroutines.flow.Flow


class AssignmentItemRepository(private val assignmentItemDao: AssignmentItemDao) {
    val allAssignmentItems: Flow<List<AssignmentItem>> = assignmentItemDao.allAssignmentItems()

    @WorkerThread
    suspend fun insertAssignmentItem(assignmentItem: AssignmentItem) {
        assignmentItemDao.insertAssignmentItem(assignmentItem)
    }

    @WorkerThread
    suspend fun updateAssignmentItem(assignmentItem: AssignmentItem) {
        assignmentItemDao.updateAssignmentItem(assignmentItem)
    }

    @WorkerThread
    suspend fun deleteAssignmentItem(assignmentItem: AssignmentItem) {
        assignmentItemDao.deleteAssignmentItem(assignmentItem)
    }
}