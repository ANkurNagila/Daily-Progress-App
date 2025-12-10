package com.evc.todolist.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.evc.todolist.domain.model.Todo

@Database(entities = [Todo::class], version = 2)
abstract class TodoDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDao
}

