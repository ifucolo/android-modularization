package com.example.mymoduleexample.ui.home

import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.mymoduleexample.R
import com.example.mymoduleexample.ui.Screens
import com.example.mymoduleexample.ui.components.AppTopAppBar


internal fun NavGraphBuilder.addHomeScreenGraph(onClick: () -> Unit) {
    composable(route = Screens.Home.route) {
        HomeScreen(onClick)
    }
}

@Composable
fun HomeScreen(
    onClick: () -> Unit
) {

    Scaffold(
        topBar = {
            AppTopAppBar(title = stringResource(id = R.string.app_name))
        }
    ) {
        Button(onClick) {
            Text(text = stringResource(id = R.string.app_name))
        }
    }
}
