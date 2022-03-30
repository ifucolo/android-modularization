package com.example.mymoduleexample.ui

sealed class Screens(val route: String) {
    object Home: Screens("home")
    object ListJobs: Screens("list_jobs")
}