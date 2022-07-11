package com.example.mymoduleexample.ui.list

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
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

    val uiState: JobsScreenUiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            AppTopAppBar(title = stringResource(id = R.string.jobs_list)) {
                navController.navigateUp()
            }
        }
    ) {
        when(val state = uiState.jobsUiState) {
            JobsUiState.Error -> {
                ErrorCompose(errorMsg = stringResource(id = R.string.error_generic))
            }
            JobsUiState.Loading -> {
                LoadingCompose()
            }
            is JobsUiState.Success -> {
                ShowJobsCompose(list = state.jobs)
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