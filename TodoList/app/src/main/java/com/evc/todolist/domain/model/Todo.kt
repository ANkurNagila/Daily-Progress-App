package com.evc.todolist.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.database.IgnoreExtraProperties

@Entity(tableName = "todos")
@IgnoreExtraProperties
data class Todo(
    val id: String = "",
    val title: String = "",
    val description: String = "",
    val isCompleted: Boolean = false,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis(),
    val userId: String = "",
    val userReminder: Boolean = false
) {
    // No-arg constructor for Firebase
    constructor() : this("", "", "", false, 0L, 0L, "", false)

    fun toMap(): Map<String, Any> {
        return mapOf(
            "id" to id,
            "title" to title,
            "description" to description,
            "isCompleted" to isCompleted,
            "createdAt" to createdAt,
            "updatedAt" to updatedAt,
            "userId" to userId,
            "userReminder" to userReminder
        )
    }
}