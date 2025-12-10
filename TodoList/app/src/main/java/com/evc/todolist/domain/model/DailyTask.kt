package com.evc.todolist.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "daily_task_table")
data class DailyTask(

    @PrimaryKey
    var id: Int = 0,
    var date: String = "",
    var time: String = "",
    var title: String = "",
    var tasks: List<Todo> = mutableListOf(),
    var isDone: Boolean = false
){
    // No-arg constructor for Firebase
    constructor() : this(id="", date="", timeStamp="", false, 0L, 0L, "", false)

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


