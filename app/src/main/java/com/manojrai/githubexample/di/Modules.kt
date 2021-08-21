package com.manojrai.githubexample.di

import androidx.room.Room
import com.manojrai.githubexample.data.local.DatabaseService
import com.manojrai.githubexample.data.remote.Networking
import com.manojrai.githubexample.data.repository.MainRepository
import com.manojrai.githubexample.ui.main.MainViewModel
import com.manojrai.githubexample.ui.main.RepositoriesAdapter
import com.manojrai.githubexample.ui.repodetails.RepositoriesDetailsViewModel
import com.manojrai.githubexample.utils.common.NetworkUtils
import com.manojrai.githubexample.utils.rx.RxSchedulerProvider
import com.manojrai.githubexample.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single {
        Networking.create(
            Networking.BASE_URL,
            androidApplication().cacheDir,
            10 * 1024 * 1024 // 10MB
        )
    }

    single {
        Room.databaseBuilder(
            androidApplication(),
            DatabaseService::class.java, "github_example.db"
        ).build()
    }

    single {
        NetworkUtils(androidApplication())
    }

    factory<SchedulerProvider> { RxSchedulerProvider() }

    factory { CompositeDisposable() }

}

val mainModule = module {

    factory {
        MainRepository(
            networkService = get(),
            databaseService = get()
        )
    }

    viewModel {
        MainViewModel(
            schedulerProvider = get(),
            compositeDisposable = get(),
            mainRepository = get(),
            networkUtils = get()
        )
    }
}

val detailsModule = module {
    viewModel {
        RepositoriesDetailsViewModel(
            schedulerProvider = get(),
            compositeDisposable = get()
        )
    }
}