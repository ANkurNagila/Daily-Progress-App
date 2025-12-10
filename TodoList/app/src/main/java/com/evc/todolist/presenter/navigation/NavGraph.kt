package com.evc.todolist.presenter.navigation

sealed class NavGraph(val route:String) {
    object Login : NavGraph("login")
    object SignUp : NavGraph("signUp")
    object Home : NavGraph("home")
    object AboutUs : NavGraph("aboutUs")
    object Profile : NavGraph("profile")

}