package com.example.todolist

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [AssignmentItem::class], version = 1, exportSchema = false)
abstract class AssignmentItemDatabase: RoomDatabase() {
    abstract fun assignmentItemDao(): AssignmentItemDao

    companion object {
        private var INSTANCE: AssignmentItemDatabase? = null

        fun getDatabase(context: Context): AssignmentItemDatabase {
            return INSTANCE ?: synchronized(this)
            {
                val instance = Room.databaseBuilder(context.applicationContext,AssignmentItemDatabase::class.java,"assignment_item_database").build()
                INSTANCE = instance
                instance
            }
        }
    }
}