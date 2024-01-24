package com.example.todolist

import android.content.Context
import android.graphics.Paint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.databinding.AssignmentItemCellBinding
import java.time.format.DateTimeFormatter

class AssignmentItemViewHolder(
    private val context: Context,
    private val binding: AssignmentItemCellBinding,
    private val clickListener: AssignmentItemClickListener
): RecyclerView.ViewHolder(binding.root) {
    @RequiresApi(Build.VERSION_CODES.O)
    private val timeFormat = DateTimeFormatter.ofPattern("HH:mm")
    @RequiresApi(Build.VERSION_CODES.O)
    fun bindAssignmentItem(assignmentItem: AssignmentItem) {
        binding.name.text=assignmentItem.name
        if (assignmentItem.isCompleted()) {
            binding.name.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            binding.dueTime.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        }

        binding.completeButton.setImageResource(assignmentItem.imageResource())
        //binding.completeButton.setColorFilter(assignmentItem.imageColor(context))

        binding.deleteButton.setOnClickListener {
            clickListener.deleteAssignmentItem(assignmentItem)
        }

        binding.completeButton.setOnClickListener{
            clickListener.completeAssignmentItem((assignmentItem))
        }
        binding.assignmentCellContainer.setOnClickListener{
            clickListener.editAssignmentItem(assignmentItem)
        }

        if (assignmentItem.dueTime() != null)
            binding.dueTime.text = timeFormat.format(assignmentItem.dueTime())
        else
            binding.dueTime.text = ""
    }
}