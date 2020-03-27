package com.example.mymoduleexample.feature.list

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entities.AndroidJob
import com.example.mymoduleexample.R
import com.example.mymoduleexample.extension.inflate
import kotlinx.android.synthetic.main.item_android_job.view.*

class AndroidJobsAdapter(var jobs: List<AndroidJob> = listOf()): RecyclerView.Adapter<AndroidJobsAdapter.ViewHolder>() {

    inner class ViewHolder(parent: ViewGroup): RecyclerView.ViewHolder(parent.inflate(R.layout.item_android_job)) {

        fun bind(androidJob: AndroidJob) = with(itemView) {
            txtTitle.text = androidJob.title
            txtCountry.text = androidJob.country
            txtYears.text = androidJob.experienceTimeRequired

            chkNative.isChecked = androidJob.native
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(parent)
    override fun getItemCount(): Int = jobs.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(jobs[position])
}