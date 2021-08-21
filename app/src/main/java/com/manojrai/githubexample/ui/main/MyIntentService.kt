package com.manojrai.githubexample.ui.main

import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Intent
import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MyIntentService() : JobService() {

    override fun onStartJob(p0: JobParameters?): Boolean {
        Log.i("TAGGG", " scheduled started")
        LocalBroadcastManager.getInstance(this).sendBroadcast(Intent("callback"))
        this.jobFinished(p0, true)
        return true
    }

    override fun onStopJob(p0: JobParameters?): Boolean {
        Log.i("TAGGG", " scheduled stopped")
        return true
    }
}