package com.evc.todolist.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.evc.todolist.screens.TodoViewModel
import com.evc.todolist.screens.home.components.AddTodoDialog
import com.evc.todolist.screens.home.components.TodoItem


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: TodoViewModel = hiltViewModel()
) {
    val todos by viewModel.todos.collectAsState()
    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        containerColor=Color.Transparent,
        floatingActionButton = {
            FloatingActionButton(onClick = { showDialog = true }) {
                Icon(Icons.Default.Add, "Add Todo")
            }
        }
    ) { padding ->

        // Elements of Todo List
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(todos, key = { it.id }) { todo ->
                TodoItem(
                    todo = todo,
                    onToggle = { viewModel.toggleTodoComplete(todo) },
                    onDelete = { viewModel.deleteTodo(todo) }
                )
            }
        }

        // Add Todo Dialog
        if (showDialog) {
            AddTodoDialog(
                onDismiss = { showDialog = false },
                onAdd = { title, desc, reminder ->
                    viewModel.addTodo(title, desc, reminder)
                    showDialog = false
                }
            )
        }
    }
}

