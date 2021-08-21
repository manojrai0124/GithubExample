package com.manojrai.githubexample.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.manojrai.githubexample.data.model.Repositories
import io.reactivex.Single

@Dao
interface RepoDao {

    @Query("select * from repositories")
    fun getRepos(): Single<List<Repositories>>

    @Insert
    fun insertRepos(list: List<Repositories>): List<Long>

    @Query("select count(*) from repositories")
    fun getCount(): Int
}