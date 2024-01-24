package com.example.todolist

interface AssignmentItemClickListener {
    fun editAssignmentItem(assignmentItem: AssignmentItem)
    fun completeAssignmentItem(assignmentItem: AssignmentItem)
    fun deleteAssignmentItem(assignmentItem: AssignmentItem)
}