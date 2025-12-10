package com.evc.todolist.screens.auth.signIn

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.firebase.auth.FirebaseAuth
import com.evc.todolist.R

@Composable
fun SignInScreen(
    onSignInSuccess: () -> Unit,
    onNavigateToSignUp: () -> Unit
) {
    val auth = if (LocalInspectionMode.current) null else FirebaseAuth.getInstance()
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var loading by remember { mutableStateOf(false) }
    var error by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text("Welcome Back!", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(20.dp))

        Text(
            "Let’s Restart from where we left"
        )

        Image(
            painter=painterResource(id= R.drawable.github),
            contentDescription="Organiser Button",
            contentScale = ContentScale.None,
            modifier = Modifier
                .padding(start = 80.dp)
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(10.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(10.dp))

        TextButton(
            onClick = {
                /* Handle forgot password */
            }) {
            Text("Forgot Password?")
        }

        Spacer(Modifier.height(16.dp))

        Button(
            onClick = {
                loading = true
                auth!!.signInWithEmailAndPassword(email.trim(), password.trim())
                    .addOnCompleteListener { task ->
                        loading = false
                        if (task.isSuccessful) {
                            onSignInSuccess()
                        } else {
                            error = task.exception?.message ?: "Login failed"
                        }
                    }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (loading) "Loading..." else "Sign In")
        }

        Spacer(Modifier.height(10.dp))

        TextButton(onClick = onNavigateToSignUp) {
            Text("Don't have an account? Sign Up")
        }

        if (error.isNotEmpty()) {
            Spacer(Modifier.height(10.dp))
            Text(text = error, color = MaterialTheme.colorScheme.error)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignInScreenPreview() {
    SignInScreen(onSignInSuccess = {}, onNavigateToSignUp = {})
}
