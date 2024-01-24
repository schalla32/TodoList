package com.example.todolist

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), AssignmentItemClickListener
{
    private lateinit var binding: ActivityMainBinding
    private  val assignmentViewModel: AssignmentViewModel by viewModels {
        AssignmentItemModelFactory((application as TodoApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.newAsButton.setOnClickListener {
            NewAssignmentSheet(null).show(supportFragmentManager, "newAssignmentTag")
        }

        binding.newAsButton.setOnClickListener {
            NewAssignmentSheet(null).show(supportFragmentManager, "newAssignmentTag")
        }

        setRecyclerView()
    }

    private fun setRecyclerView()
    {
        val mainActivity = this
        assignmentViewModel.assignmentItems.observe(this){
            binding.todoListRecyclerView.apply{
                layoutManager = LinearLayoutManager(applicationContext)
                adapter = AssignmentItemAdapter(it, mainActivity)

            }
        }
    }

    override fun editAssignmentItem(assignmentItem: AssignmentItem) {
        NewAssignmentSheet(assignmentItem).show(supportFragmentManager, "newAssignmentTag")
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun completeAssignmentItem(assignmentItem: AssignmentItem) {
        assignmentViewModel.setCompleted(assignmentItem)
    }

    override fun deleteAssignmentItem(assignmentItem: AssignmentItem) {
        // Implementation for deleting assignment item
        assignmentViewModel.deleteAssignmentItem(assignmentItem)
    }



}