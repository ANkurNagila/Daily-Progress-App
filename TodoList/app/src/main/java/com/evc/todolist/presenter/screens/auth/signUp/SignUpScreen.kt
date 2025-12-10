package com.evc.todolist.screens.auth.signUp

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.firebase.auth.FirebaseAuth

@Composable
fun SignUpScreen(
    onSignUpSuccess: () -> Unit,
    onBackToSignIn: () -> Unit
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
        Text("Welcome Onboard!", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(20.dp))

        Text(
            "Let’s help you meet your tasks"
        )

        Spacer(Modifier.height(20.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Enter Full Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(20.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Enter Email") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(10.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("EnterPassword") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(10.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Confirm Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(16.dp))

        Button(
            onClick = {
                loading = true
                auth!!.createUserWithEmailAndPassword(email.trim(), password.trim())
                    .addOnCompleteListener { task ->
                        loading = false
                        if (task.isSuccessful) {
                            onSignUpSuccess()
                        } else {
                            error = task.exception?.message ?: "Sign up failed"
                        }
                    }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (loading) "Creating..." else "Sign Up")
        }

        Spacer(Modifier.height(10.dp))

        Box(modifier = Modifier.fillMaxWidth(),contentAlignment = Alignment.Center){
            Row(modifier = Modifier,verticalAlignment = Alignment.CenterVertically){
                Text(
                    "Already have an account? "
                )

                TextButton(onClick = onBackToSignIn) {
                    Text("SIGN IN")
                }
            }
        }



        if (error.isNotEmpty()) {
            Spacer(Modifier.height(10.dp))
            Text(text = error, color = MaterialTheme.colorScheme.error)
        }
    }
}

@Preview(showBackground=true)
@Composable
fun SignUpPreview() {
    SignUpScreen(onSignUpSuccess = {}, onBackToSignIn = {})
}
