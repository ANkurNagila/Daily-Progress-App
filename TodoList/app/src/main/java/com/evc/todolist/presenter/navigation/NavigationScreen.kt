package com.evc.todolist.presenter.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.evc.todolist.screens.aboutUs.AboutUsScreen
import com.evc.todolist.screens.auth.signIn.SignInScreen
import com.evc.todolist.screens.auth.signUp.SignUpScreen
import com.evc.todolist.screens.home.HomeScreen
import com.evc.todolist.screens.profile.ProfileScreen

@Composable
fun AppNavigation(navController: NavHostController){

    NavHost(navController,startDestination=NavGraph.Home.route){

        // Login Page
        composable(route=NavGraph.Login.route){
            SignInScreen(
                onSignInSuccess = {
                    navController.navigate(NavGraph.Home.route){
                        popUpTo(NavGraph.Login.route){
                            inclusive = true
                        }
                    }
                },
                onNavigateToSignUp = {
                    navController.navigate(NavGraph.SignUp.route)
                }
            )
        }

        // SignUp Page
        composable(route=NavGraph.SignUp.route){
            SignUpScreen(
                onSignUpSuccess = {
                    navController.navigate(NavGraph.Home.route){
                        popUpTo(NavGraph.SignUp.route){
                            inclusive = true
                        }
                    }
                },
                onBackToSignIn = {
                    navController.navigate(NavGraph.Login.route){
                        popUpTo(NavGraph.SignUp.route){
                            inclusive = true
                        }
                    }
                }
            )
        }

        // Home Page
        composable(route=NavGraph.Home.route){
            HomeScreen()
        }

        // About Us Page
        composable(route=NavGraph.AboutUs.route){
            AboutUsScreen()
        }

        // Profile Page
        composable(route=NavGraph.Profile.route){
            ProfileScreen()
        }
    }
}