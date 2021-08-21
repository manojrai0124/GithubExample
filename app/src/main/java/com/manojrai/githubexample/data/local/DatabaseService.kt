package com.manojrai.githubexample.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.manojrai.githubexample.data.model.Repositories

@Database(entities = [Repositories::class], exportSchema = false, version = 1)
abstract class DatabaseService : RoomDatabase(){

    abstract fun repoDaa(): RepoDao

}