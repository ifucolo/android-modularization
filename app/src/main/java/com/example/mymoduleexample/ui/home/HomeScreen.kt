package com.example.mymoduleexample.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.mymoduleexample.R
import com.example.mymoduleexample.ui.Screens


internal fun NavGraphBuilder.addHomeScreenGraph(onClick: () -> Unit) {
    composable(route = Screens.Home.route) {
        HomeScreen(onClick)
    }
}

@Composable
fun HomeScreen(
    onClick: () -> Unit
) {
    Box(Modifier.fillMaxSize()) {
        Button(onClick) {
            Text(text = stringResource(id = R.string.show_list))
        }
    }
}

private fun setupViewModel() {

}