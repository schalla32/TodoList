package com.example.todolist

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.RemoteViews.RemoteCollectionItems
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.example.todolist.databinding.AssignmentItemCellBinding

class AssignmentItemAdapter(
    private val assignmentItems: List<AssignmentItem>,
    private val clickListener: AssignmentItemClickListener
): RecyclerView.Adapter<AssignmentItemViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AssignmentItemViewHolder {
        val from = LayoutInflater.from(parent.context)
        val binding = AssignmentItemCellBinding.inflate(from, parent, false)
        return AssignmentItemViewHolder(parent.context, binding, clickListener)
    }

    override fun getItemCount(): Int = assignmentItems.size

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: AssignmentItemViewHolder, position: Int) {
        holder.bindAssignmentItem(assignmentItems[position])
    }
}