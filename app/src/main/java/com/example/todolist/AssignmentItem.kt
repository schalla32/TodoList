package com.example.todolist

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.DateTimeException
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.UUID

@Entity(tableName = "assignment_item_table")
class AssignmentItem (
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "desc")var desc: String,
    @ColumnInfo(name = "dueTimeString")var dueTimeString: String?,
    @ColumnInfo(name = "completedDateString")var completedDateString: String?,
    @PrimaryKey(autoGenerate = true)var id: Int = 0,
    )

{
    @RequiresApi(Build.VERSION_CODES.O)
    fun completedDate(): LocalDate? = if (completedDateString == null) null else LocalDate.parse(completedDateString, dateFormatter)
    fun dueTime(): LocalTime? = if (dueTimeString == null) null else LocalTime.parse(dueTimeString, timeFormatter)

    @RequiresApi(Build.VERSION_CODES.O)
    fun isCompleted() = completedDate() != null
    fun imageResource(): Int = if (isCompleted()) R.drawable.checked_24 else R.drawable.unchecked_24
    @RequiresApi(Build.VERSION_CODES.O)
    fun imageColor(context: Context): Unit = if(isCompleted()) purple(context) else black(context)

    private fun purple(context: Context){ val customColor = getCustomColor(context, R.color.my_magenta)}
    private fun black(context: Context) { val customColor = getCustomColor(context, R.color.my_pink)}
    private fun getCustomColor(context: Context, colorID: Int): Int {
        return ContextCompat.getColor(context, colorID)
    }


    companion object {
        @RequiresApi(Build.VERSION_CODES.O)
        val timeFormatter: DateTimeFormatter = DateTimeFormatter.ISO_TIME
        @RequiresApi(Build.VERSION_CODES.O)
        val dateFormatter: DateTimeFormatter = DateTimeFormatter.ISO_DATE
    }
}