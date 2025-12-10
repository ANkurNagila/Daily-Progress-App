package com.evc.todolist.data.remote

import com.evc.todolist.domain.model.Todo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseRepository @Inject constructor(
    private val database: FirebaseDatabase,
    private val auth: FirebaseAuth
) {
    private val todosRef: DatabaseReference
        get() = database.reference.child("todos").child(getCurrentUserId() ?: "")

    fun getTodos(): Flow<List<Todo>> = callbackFlow {
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val todos = snapshot.children.mapNotNull { childSnapshot ->
                    childSnapshot.getValue(Todo::class.java)
                }
                trySend(todos)
            }

            override fun onCancelled(error: DatabaseError) {
                close(error.toException())
            }
        }

        todosRef.addValueEventListener(listener)
        awaitClose { todosRef.removeEventListener(listener) }
    }

    suspend fun addTodo(todo: Todo): Result<String> = try {
        val userId = getCurrentUserId() ?: return Result.failure(Exception("User not logged in"))
        val todoWithUser = todo.copy(userId = userId)
        todosRef.child(todo.id).setValue(todoWithUser.toMap()).await()
        Result.success(todo.id)
    } catch (e: Exception) {
        Result.failure(e)
    }

    suspend fun updateTodo(todo: Todo): Result<Unit> = try {
        todosRef.child(todo.id).setValue(todo.toMap()).await()
        Result.success(Unit)
    } catch (e: Exception) {
        Result.failure(e)
    }

    suspend fun deleteTodo(todoId: String): Result<Unit> = try {
        todosRef.child(todoId).removeValue().await()
        Result.success(Unit)
    } catch (e: Exception) {
        Result.failure(e)
    }

    suspend fun signInAnonymously(): Result<String> = try {
        val result = auth.signInAnonymously().await()
        Result.success(result.user?.uid ?: "")
    } catch (e: Exception) {
        Result.failure(e)
    }

    fun getCurrentUserId(): String? = auth.currentUser?.uid
}
