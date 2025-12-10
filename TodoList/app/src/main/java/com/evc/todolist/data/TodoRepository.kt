package com.evc.todolist.data

import com.evc.todolist.data.local.TodoDao
import com.evc.todolist.domain.model.Todo
import com.evc.todolist.data.remote.FirebaseRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TodoRepository @Inject constructor(
    private val todoDao: TodoDao,
    private val firebaseRepository: FirebaseRepository
) {
    private val scope = CoroutineScope(Dispatchers.IO)

    init {
        // Sync Firebase to Room
        scope.launch {
            firebaseRepository.getTodos().collect { remoteTodos ->
                todoDao.insertTodos(remoteTodos)
            }
        }
    }

    fun getTodos(): Flow<List<Todo>> = todoDao.getAllTodos()

    suspend fun getTodoById(id: String): Todo? = todoDao.getTodoById(id)


    suspend fun addTodo(todo: Todo) {
        val todoWithId = todo.copy(id = UUID.randomUUID().toString())
        // Insert to local first
        todoDao.insertTodo(todoWithId)
        // Then sync to Firebase
        firebaseRepository.addTodo(todoWithId)
    }

    suspend fun updateTodo(todo: Todo) {
        val updatedTodo = todo.copy(updatedAt = System.currentTimeMillis())
        todoDao.updateTodo(updatedTodo)
        firebaseRepository.updateTodo(updatedTodo)
    }

    suspend fun deleteTodo(todo: Todo) {
        todoDao.deleteTodo(todo)
        firebaseRepository.deleteTodo(todo.id)
    }

    suspend fun signInAnonymously() = firebaseRepository.signInAnonymously()

    fun isUserSignedIn() = firebaseRepository.getCurrentUserId() != null
}