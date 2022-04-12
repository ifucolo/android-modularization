package com.example.mymoduleexample.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier


@Composable
fun LoadingCompose() {
    Box(Modifier.fillMaxSize()) {
        CircularProgressIndicator(Modifier.align(Alignment.Center))
    }
}


@Composable
fun EmptyCompose(emptyMsg: String) {
    Box(Modifier.fillMaxSize()) {
        BigTitle(text = emptyMsg)
    }
}

@Composable
fun ErrorCompose(errorMsg: String) {
    Box(Modifier.fillMaxSize()) {
        BigTitle(text = errorMsg)
    }
}