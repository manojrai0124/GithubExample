package com.manojrai.githubexample.data.repository

import com.manojrai.githubexample.data.local.DatabaseService
import com.manojrai.githubexample.data.model.Repositories
import com.manojrai.githubexample.data.remote.NetworkService
import io.reactivex.Single

class MainRepository(
    private val networkService: NetworkService,
    private val databaseService: DatabaseService,
) {

    fun getRepoCall(): Single<List<Repositories>> = networkService.repositoriesCall()

    fun getRepoLocal() = databaseService.repoDaa().getRepos()

    fun getCount() = databaseService.repoDaa().getCount()

    fun insertRepos(list: List<Repositories>) =
        databaseService.repoDaa().insertRepos(list)

}