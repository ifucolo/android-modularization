package com.example.mymoduleexample.ui.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.mymoduleexample.theme.AppTheme
import com.example.mymoduleexample.ui.Screens
import com.example.mymoduleexample.ui.list.addListJobsScreenGraph
import com.example.mymoduleexample.utils.exhaustive
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MyApp()
                }
            }
        }
    }

    @Composable
    fun MyApp() {
        val navController = rememberNavController()
        val viewModel = hiltViewModel<MainViewModel>()

        NavHost(navController,
            startDestination = Screens.Home.route
        ) {
            addHomeScreenGraph(
                onClick = {
                    viewModel.onShowAndroidJobsRequire()
                }
            )
            addListJobsScreenGraph(navController)
        }

        viewModel.mainActionLiveData.observe(this) {
            it.getContentIfNotHandled()?.let { mainAction ->
                when (mainAction) {
                    MainViewModel.MainAction.SHOW_JOBS -> {
                        navController.navigate(Screens.ListJobs.route)
                    }
                    MainViewModel.MainAction.LEAVE_APP -> finish()
                }.exhaustive
            }
        }
    }
}
