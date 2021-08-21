package com.manojrai.githubexample.ui.main

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.app.job.JobService
import android.content.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.manojrai.githubexample.R
import com.manojrai.githubexample.data.model.Repositories
import com.manojrai.githubexample.ui.repodetails.RepositoriesDetailsActivity
import com.manojrai.githubexample.utils.common.toast
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {


    private val mViewModel: MainViewModel by viewModel()

    private val PERIODIC: Long = 15 * 60 * 1000

    private lateinit var jobScheduler: JobScheduler

    private val clickListener: (Repositories) -> Unit = { repositories ->
        Intent(this, RepositoriesDetailsActivity::class.java).apply {
            putExtra("name", repositories.name)
            putExtra("fullName", repositories.fullName)
            putExtra("description", repositories.description)
            putExtra("avatarUrl", repositories.owner?.avatarUrl)
            startActivity(this)
        }
    }

    private val repositoriesAdapter = RepositoriesAdapter(emptyList(), clickListener)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpView()
        setUpObserver()
    }

    private fun setUpView() {

        LocalBroadcastManager.getInstance(this).registerReceiver(object : BroadcastReceiver() {
            override fun onReceive(p0: Context?, p1: Intent?) {
                mViewModel.onCreate()
            }
        }, IntentFilter("callback"))

        jobScheduler = getSystemService(JOB_SCHEDULER_SERVICE) as JobScheduler
        rvRepo.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = repositoriesAdapter
        }
        startJob()
    }

    private fun setUpObserver() {
        mViewModel.data.observe(this, {
            repositoriesAdapter.appendData(it)
        })

        mViewModel.loading.observe(this, {
            progressBar.visibility = if (it) View.VISIBLE else View.GONE
        })

        mViewModel.messageString.observe(this, {
            toast(it)
        })
    }

    private fun startJob() {
        val componentName = ComponentName(this, MyIntentService::class.java)
        val jobInfo = JobInfo.Builder(101, componentName)
            .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
            .setPeriodic(PERIODIC)
            .setRequiresCharging(false)
            .setPersisted(true)
            .build()

        if (jobScheduler.schedule(jobInfo) == JobScheduler.RESULT_SUCCESS) {
            Log.i("TAGGG", " scheduled success")
        } else {
            Log.i("TAGGG", " scheduled failed")
        }
    }

}