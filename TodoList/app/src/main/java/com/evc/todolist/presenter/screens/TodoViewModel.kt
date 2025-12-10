package com.evc.todolist.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.evc.todolist.data.TodoRepository
import com.evc.todolist.domain.model.Todo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(
    private val repository: TodoRepository
) : ViewModel() {

    val todos: StateFlow<List<Todo>> = repository.getTodos()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    private val _uiState = MutableStateFlow<UiState>(UiState.Success)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            if (!repository.isUserSignedIn()) {
                repository.signInAnonymously()
            }
        }
    }

    fun addTodo(title: String, description: String,reminder: Boolean) {
        if (title.isBlank()) return
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                repository.addTodo(
                    Todo(
                        title = title,
                        description = description,
                        userReminder = reminder
                    )
                )
                _uiState.value = UiState.Success
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun updateTodo(todo: Todo) {
        viewModelScope.launch {
            try {
                repository.updateTodo(todo)
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun toggleTodoComplete(todo: Todo) {
        updateTodo(todo.copy(isCompleted = !todo.isCompleted))
    }

    fun deleteTodo(todo: Todo) {
        viewModelScope.launch {
            try {
                repository.deleteTodo(todo)
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "Unknown error")
            }
        }
    }

    sealed class UiState {
        object Success : UiState()
        object Loading : UiState()
        data class Error(val message: String) : UiState()
    }
}