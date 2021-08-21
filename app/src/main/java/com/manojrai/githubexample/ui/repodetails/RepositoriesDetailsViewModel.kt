package com.manojrai.githubexample.ui.repodetails

import androidx.lifecycle.MutableLiveData
import com.manojrai.githubexample.ui.base.BaseViewModel
import com.manojrai.githubexample.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class RepositoriesDetailsViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
) : BaseViewModel(schedulerProvider, compositeDisposable) {

    val name = MutableLiveData<String>()
    val fullName = MutableLiveData<String>()
    val description = MutableLiveData<String>()
    val avatarUrl = MutableLiveData<String>()

    fun setDetails(name: String?, fullName: String?, description: String?, avatarUrl: String?) {
        this.name.postValue(name)
        this.fullName.postValue(fullName)
        this.description.postValue(description)
        this.avatarUrl.postValue(avatarUrl)
    }

}