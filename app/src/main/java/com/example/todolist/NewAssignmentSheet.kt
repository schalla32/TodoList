package com.example.todolist

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.todolist.databinding.FragmentNewAssignmentSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import android.app.TimePickerDialog
import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalTime

class NewAssignmentSheet (var assignmentItem: AssignmentItem?) : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentNewAssignmentSheetBinding
    private lateinit var assignmentViewModel: AssignmentViewModel
    private var dueTime: LocalTime? = null



    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)
        val activity = requireActivity()

        if (assignmentItem != null) {
            binding.assignmentTitle.text = "Edit Assignment"
            val editable = Editable.Factory.getInstance()
            binding.name.text = editable.newEditable(assignmentItem!!.name)
            binding.desc.text = editable.newEditable(assignmentItem!!.desc)
            if (assignmentItem!!.dueTime() != null)
            {
                dueTime = assignmentItem!!.dueTime()!!
                updateTimeButtonText()
            }

        }
        else {
            binding.assignmentTitle.text = "New Assignment"
        }

        assignmentViewModel = ViewModelProvider(activity).get(AssignmentViewModel::class.java)
        binding.saveButton.setOnClickListener {
            saveAction()
        }
        binding.timePickerButton.setOnClickListener {
            openTimePicker()
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun updateTimeButtonText() {
        binding.timePickerButton.text =
            String().format("%02d:%02d", dueTime!!.hour, dueTime!!.minute)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun openTimePicker() {
        if (dueTime == null)
            dueTime = LocalTime.now()
        val listener = TimePickerDialog.OnTimeSetListener { _, selectedHour, selectedMinute ->
            dueTime = LocalTime.of(selectedHour, selectedMinute)
            updateTimeButtonText()
        }
        val dialog = TimePickerDialog(activity, listener, dueTime!!.hour, dueTime!!.minute, true)
        dialog.setTitle("Assignment Due")
        dialog.show()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewAssignmentSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun saveAction() {
        val name = binding.name.text.toString()
        val desc = binding.desc.text.toString()
        val dueTimeString = if(dueTime == null ) null else AssignmentItem.timeFormatter.format(dueTime)
        if (assignmentItem == null) {
            val newAssignment = AssignmentItem(name, desc, dueTimeString, null)
            assignmentViewModel.addAssignmentItem(newAssignment)
        } else {
            assignmentItem!!.name = name
            assignmentItem!!.desc = desc
            assignmentItem!!.dueTimeString = dueTimeString
            assignmentViewModel.updateAssignmentItem(assignmentItem!!)
        }
        binding.name.setText("")
        binding.desc.setText("")
        dismiss()
    }

}




