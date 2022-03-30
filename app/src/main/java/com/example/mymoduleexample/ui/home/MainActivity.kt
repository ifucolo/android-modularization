package com.example.mymoduleexample.ui.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.Observer
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.mymoduleexample.databinding.ActivityMainBinding
import com.example.mymoduleexample.ui.list.AndroidJobsListActivity
import com.example.mymoduleexample.theme.AppTheme
import com.example.mymoduleexample.ui.Screens
import com.example.mymoduleexample.ui.list.addListJobsScreenGraph
import com.example.mymoduleexample.utils.exhaustive
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModel()
    private lateinit var binding: ActivityMainBinding

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

    private fun setupViewModel() {
        viewModel.mainActionLiveData.observe(this, Observer {
            it.getContentIfNotHandled()?.let { mainAction ->
                when(mainAction) {
                    MainViewModel.MainAction.SHOW_JOBS -> startActivity(AndroidJobsListActivity.launchIntent(this))
                    MainViewModel.MainAction.LEAVE_APP -> finish()
                }.exhaustive
            }
        })
    }

    @Composable
    fun MyApp() {
        val navController = rememberNavController()

        NavHost(navController,
            startDestination = Screens.Home.route
        ) {
            addHomeScreenGraph()
            addListJobsScreenGraph()
        }
    }
}
