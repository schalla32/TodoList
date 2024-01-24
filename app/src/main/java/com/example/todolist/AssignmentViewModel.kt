package com.example.todolist
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import java.time.LocalDate

class AssignmentViewModel(private val repository: AssignmentItemRepository): ViewModel()
{
    var assignmentItems: LiveData<List<AssignmentItem>> = repository.allAssignmentItems.asLiveData()

    fun addAssignmentItem(newAssignment: AssignmentItem) = viewModelScope.launch {
        repository.insertAssignmentItem(newAssignment)
    }

    fun updateAssignmentItem(assignmentItem: AssignmentItem) = viewModelScope.launch {
        repository.updateAssignmentItem(assignmentItem)
    }

    fun deleteAssignmentItem(assignmentItem: AssignmentItem) = viewModelScope.launch {
        repository.deleteAssignmentItem(assignmentItem)
    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun setCompleted(assignmentItem: AssignmentItem) = viewModelScope.launch {
        if (!assignmentItem.isCompleted())
            assignmentItem.completedDateString = AssignmentItem.dateFormatter.format(LocalDate.now())
        repository.updateAssignmentItem(assignmentItem)
    }
}

class AssignmentItemModelFactory(private val repository: AssignmentItemRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AssignmentViewModel::class.java))
            return AssignmentViewModel(repository) as T
        throw IllegalArgumentException("Unknown Class for View Model")
    }
}
