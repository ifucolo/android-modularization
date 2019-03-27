package com.example.mymoduleexample.feature.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.mymoduleexample.R
import com.example.mymoduleexample.databinding.ActivityMainBinding
import com.example.mymoduleexample.feature.list.AndroidJobsListActivity
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by inject()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        setupViewModel()
    }

    private fun setupViewModel() {
        viewModel.showAndroidJobsLiveData.observe(this, Observer { isShow ->
            when(isShow) {
                true -> { startActivity(AndroidJobsListActivity.launchIntent(this)) }
            }
        })

        viewModel.outAppLiveData.observe(this, Observer { isOut ->
            when(isOut) {
                true -> { finish() }
            }
        })
    }
}
