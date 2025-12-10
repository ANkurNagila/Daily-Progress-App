package com.evc.todolist.data.worker

import android.content.Context
import androidx.work.*
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ReminderScheduler @Inject constructor(
    @ApplicationContext private val context: Context
) {
    // Create a instance of WorkManger Context will be Application Context
    private val workManager = WorkManager.getInstance(context)


    fun scheduleReminder(todoId: String, reminderTimeMillis: Long) {

        // Cancel any existing reminder for this todo
        cancelReminder(todoId)

        val currentTime = System.currentTimeMillis()
        val delay = reminderTimeMillis - currentTime

        // Only schedule if the reminder is in the future
        if (delay <= 0) return

        val data = Data.Builder()
            .putString(ReminderWorker.KEY_TODO_ID, todoId)
            .build()

        val reminderWork = OneTimeWorkRequestBuilder<ReminderWorker>()
            .setInitialDelay(delay, TimeUnit.MILLISECONDS)
            .setInputData(data)
            .addTag(getWorkTag(todoId))
            .build()

        workManager.enqueueUniqueWork(
            getWorkName(todoId),
            ExistingWorkPolicy.REPLACE,
            reminderWork
        )
    }

    fun cancelReminder(todoId: String) {
        workManager.cancelUniqueWork(getWorkName(todoId))
    }

    fun cancelAllReminders() {
        workManager.cancelAllWorkByTag(REMINDER_TAG)
    }

    private fun getWorkName(todoId: String) = "reminder_$todoId"

    private fun getWorkTag(todoId: String) = REMINDER_TAG

    companion object {
        private const val REMINDER_TAG = "todo_reminder"
    }
}