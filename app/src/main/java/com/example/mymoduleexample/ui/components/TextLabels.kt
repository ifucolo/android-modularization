package com.example.mymoduleexample.ui.components

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.mymoduleexample.theme.textColor
import com.example.mymoduleexample.theme.titleTextColor


@Composable
fun BigTitle(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        modifier = modifier,
        style = MaterialTheme.typography.h1,
        color = titleTextColor()
    )
}

@Composable
fun TitleList(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        modifier = modifier,
        style = MaterialTheme.typography.h3,
        color = textColor()
    )
}

@Composable
fun SubTitleList(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        modifier = modifier,
        style = MaterialTheme.typography.subtitle1,
        color = textColor()
    )
}

@Composable
fun BodyText(
    textId: Int,
    modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(id = textId),
        modifier = modifier,
        style = MaterialTheme.typography.body1,
        color = textColor()
    )
}

