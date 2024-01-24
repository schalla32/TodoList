package com.example.todolist

import androidx.room.*
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface AssignmentItemDao {
    @Query("SELECT * FROM assignment_item_table ORDER BY completedDateString, dueTimeString ASC")
    fun allAssignmentItems(): Flow<List<AssignmentItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAssignmentItem(assignmentItem: AssignmentItem)

    @Update
    suspend fun updateAssignmentItem(assignmentItem: AssignmentItem)

    @Delete
    suspend fun deleteAssignmentItem(assignmentItem: AssignmentItem)
}

