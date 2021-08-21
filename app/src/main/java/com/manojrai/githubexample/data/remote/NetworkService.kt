package com.manojrai.githubexample.data.remote

import com.manojrai.githubexample.data.model.Repositories
import io.reactivex.Single
import retrofit2.http.GET

interface NetworkService {

    @GET(EndPoints.REPOSITORIES)
    fun repositoriesCall(): Single<List<Repositories>>
}