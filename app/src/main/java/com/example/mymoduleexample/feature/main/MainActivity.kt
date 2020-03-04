package com.example.mymoduleexample.feature.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.mymoduleexample.R
import com.example.mymoduleexample.databinding.ActivityMainBinding
import com.example.mymoduleexample.feature.list.AndroidJobsListActivity
import com.example.mymoduleexample.utils.exhaustive
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        setupViewModel()
    }

    private fun setupViewModel() {
        viewModel.mainActionLiveData.observe(this, Observer {
            it.getContentIfNotHandled()?.let { mainAction ->
                when(mainAction) {
                    MainAction.SHOW_JOBS -> startActivity(AndroidJobsListActivity.launchIntent(this))
                    MainAction.LEAVE_APP -> finish()
                }.exhaustive
            }
        })
    }
}
