package com.example.mymoduleexample.ui.list

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.domain.entities.AndroidJob
import com.example.mymoduleexample.R
import com.example.mymoduleexample.databinding.ActivityAndroidJobsListBinding
import com.example.mymoduleexample.utils.exhaustive
import org.koin.androidx.viewmodel.ext.android.viewModel


class AndroidJobsListActivity: AppCompatActivity() {

    private val viewModel: AndroidJobListViewModel by viewModel()
    private lateinit var binding: ActivityAndroidJobsListBinding
    private lateinit var androidJobsAdapter: AndroidJobsAdapter

    companion object {
        fun launchIntent(context: Context): Intent {
            return Intent(context, AndroidJobsListActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_android_jobs_list)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        setupView()
        setupViewModel()
    }

    private fun setupView() {
        setSupportActionBar(binding.toolbar)
        binding.toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_material)
        binding.toolbar.setNavigationOnClickListener{
            finish()
        }

        setupRecyclerView()
    }

    private fun setupViewModel() {
        viewModel.getJobs()

        viewModel.viewJobsStatesLiveData.observe(this, Observer { state ->
            state.getContentIfNotHandled()?.let {
                hideAll()

                when(it) {
                    is AndroidJobListViewModel.ViewJobsStates.Show -> showContent(it.list)
                    AndroidJobListViewModel.ViewJobsStates.Empty -> showEmptyState()
                    AndroidJobListViewModel.ViewJobsStates.Error -> showError()
                }.exhaustive
            }
        })
    }

    private fun setupRecyclerView() = with(binding.recyclerView) {
        androidJobsAdapter = AndroidJobsAdapter()

        layoutManager = LinearLayoutManager(context)
        adapter = androidJobsAdapter
    }

    private fun showError() {
        binding.btnTryAgain.show()
    }

    private fun showEmptyState() {
        binding.txtEmpty.show()
    }

    private fun showContent(list: List<AndroidJob>) = with(androidJobsAdapter) {
        jobs = list
        notifyDataSetChanged()
        binding.recyclerView.show()
    }

    private fun hideAll() {
        listOf(binding.btnTryAgain, binding.txtEmpty, binding.recyclerView, binding.progressBar).map {
            it.hide()
        }
    }
}
