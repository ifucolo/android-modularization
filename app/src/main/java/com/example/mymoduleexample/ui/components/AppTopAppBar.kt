package com.example.mymoduleexample.ui.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import com.example.mymoduleexample.theme.tintColor

@Composable
fun AppTopAppBar(
    title: String,
    hasNavigationBack: Boolean = true,
    navigateUp: () -> Unit
) {
    TopAppBar(
        title = { BigTitle(text = title) },
        backgroundColor = MaterialTheme.colors.surface,
        navigationIcon = {
            if (hasNavigationBack) {
                IconButton(onClick = { navigateUp.invoke() }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = tintColor()
                    )
                }
            }
        }
    )
}

@Composable
fun AppTopAppBar(
    title: String,
) {
    TopAppBar(
        title = { BigTitle(text = title) },
        backgroundColor = MaterialTheme.colors.surface
    )
}
