package com.evc.todolist

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.compose.rememberNavController
import com.evc.todolist.presenter.screens.common.AppScreen
import com.evc.todolist.presenter.theme.TodoListTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            Log.d("Permission", "Notification permission granted ✅")
        } else {
            Log.d("Permission", "Notification permission denied ❌")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.TIRAMISU){
            requestPermissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS)
        }

//        val testScheduler = ReminderScheduler(this)
//        testScheduler.scheduleReminder("test123", System.currentTimeMillis() + 5000)

        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            TodoListTheme {
                AppScreen(navController = navController)
            }
        }
    }
}
