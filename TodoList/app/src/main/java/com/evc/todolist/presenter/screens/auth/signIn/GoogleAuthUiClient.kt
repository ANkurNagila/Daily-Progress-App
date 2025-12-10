package com.evc.todolist.presenter.screens.auth.signIn

import android.content.Context
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

// class to sign in - sign out and get user info
class GoogleAuthUiClient(
    private val context: Context,
    private val client: SignInClient
) {

    private val auth = Firebase.auth

    suspend fun signIn() {
        val result = try{
            oneTapClient.beginSignIn(

            )

        } catch (e: Exception) {

        }
    }

    private fun buildSignInRequest(): BeginSignInRequest {
        return BeginSignInRequest.builder()
    }
}