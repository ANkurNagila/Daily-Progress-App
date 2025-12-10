package com.evc.todolist.data.local.migrations

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        // Example: Add a new column
        database.execSQL("ALTER TABLE todos ADD COLUMN userReminder INTEGER NOT NULL DEFAULT 0")
    }
}
