package com.example.mymoduleexample.ui.list

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.mymoduleexample.ui.Screens


internal fun NavGraphBuilder.addListJobsScreenGraph() {
    composable(route = Screens.ListJobs.route) {
        ListJobsScreen()
    }
}

@Composable
fun ListJobsScreen(
    viewModel: AndroidJobListViewModel = hiltViewModel<AndroidJobListViewModel>()
) {
    println(viewModel.state)
}