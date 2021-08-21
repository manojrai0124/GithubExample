package com.manojrai.githubexample.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.manojrai.githubexample.data.model.Repositories
import com.manojrai.githubexample.data.repository.MainRepository
import com.manojrai.githubexample.ui.base.BaseViewModel
import com.manojrai.githubexample.utils.common.NetworkUtils
import com.manojrai.githubexample.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import kotlin.math.log

class MainViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    private val mainRepository: MainRepository,
    private val networkUtils: NetworkUtils
) : BaseViewModel(schedulerProvider, compositeDisposable) {

    val loading = MutableLiveData<Boolean>()
    val data = MutableLiveData<List<Repositories>>()

    init {
        onCreate()
    }

    fun onCreate(){
        if (networkUtils.isNetworkAvailable()) getRepositories()
        else getLocalRepo()
    }

    private fun getRepositories() {
        loading.postValue(true)
        compositeDisposable.add(
            mainRepository.getRepoCall()
                .map {
                    if (mainRepository.getCount() < 1) mainRepository.insertRepos(it)
                    it
                }
                .subscribeOn(schedulerProvider.io())
                .subscribe({
                    data.postValue(it)
                    loading.postValue(false)
                }, {
                    loading.postValue(false)
                    messageString.postValue(it.message)
                })
        )
    }

    private fun getLocalRepo() {
        loading.postValue(true)
        compositeDisposable.add(
            mainRepository.getRepoLocal().subscribeOn(schedulerProvider.io())
                .subscribe({
                    data.postValue(it)
                    loading.postValue(false)
                }, {
                    loading.postValue(false)
                    messageString.postValue(it.message)
                })
        )
    }

}