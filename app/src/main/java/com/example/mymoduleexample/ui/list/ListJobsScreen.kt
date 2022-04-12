package com.example.mymoduleexample.ui.list

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.domain.entities.AndroidJob
import com.example.mymoduleexample.R
import com.example.mymoduleexample.theme.marginDefault
import com.example.mymoduleexample.theme.tintColor
import com.example.mymoduleexample.theme.verticalSpace
import com.example.mymoduleexample.ui.Screens
import com.example.mymoduleexample.ui.components.*


internal fun NavGraphBuilder.addListJobsScreenGraph(navController: NavHostController) {
    composable(route = Screens.ListJobs.route) {
        ListJobsScreen(navController = navController)
    }
}

@Composable
fun ListJobsScreen(
    viewModel: AndroidJobListViewModel = hiltViewModel<AndroidJobListViewModel>(),
    navController: NavHostController
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { BigTitle(text = stringResource(id = R.string.jobs_list)) },
                backgroundColor = MaterialTheme.colors.background,
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = tintColor()
                        )
                    }
                }
            )
        }
    ) {
        when(val state = viewModel.state.collectAsState().value) {
            AndroidJobListViewModel.JobsUiStateState.Empty -> {
                EmptyCompose(emptyMsg = stringResource(id = R.string.empty))
            }
            AndroidJobListViewModel.JobsUiStateState.Error -> {
                ErrorCompose(errorMsg = stringResource(id = R.string.error_generic))
            }
            AndroidJobListViewModel.JobsUiStateState.Loading -> {
                LoadingCompose()
            }
            is AndroidJobListViewModel.JobsUiStateState.Show -> {
                ShowJobsCompose(list = state.list)
            }
        } 
    }
}

@Composable
fun ShowJobsCompose(list: List<AndroidJob>) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = marginDefault, vertical = marginDefault),
        verticalArrangement = Arrangement.spacedBy(verticalSpace)
    ) {
        items(list) {
            JobsItem(job = it)
        }
    }
}

@Composable
fun JobsItem(job: AndroidJob) {
    var expanded by remember { mutableStateOf(false)}
    Card(
        backgroundColor = MaterialTheme.colors.onSurface
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(marginDefault)
                .clickable { expanded = !expanded },
            verticalArrangement = Arrangement.spacedBy(verticalSpace),
        ) {
            TitleList(
                text = job.title
            )
            AnimatedVisibility(visible = expanded) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(verticalSpace)
                ) {
                    SubTitleList(text = job.country)
                    SubTitleList(text = job.experienceTimeRequired)
                }
            }
        }
    }
}