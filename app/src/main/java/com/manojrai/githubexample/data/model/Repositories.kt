package com.manojrai.githubexample.data.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "repositories")
data class Repositories(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_room")
    val idRoom: Long,

    @ColumnInfo(name = "id_repo")
    @Expose
    @SerializedName("id")
    val id: Long,

    @ColumnInfo(name = "name")
    @Expose
    @SerializedName("name")
    val name: String?,

    @ColumnInfo(name = "full_name")
    @Expose
    @SerializedName("full_name")
    val fullName: String?,

    @Embedded
    @Expose
    @SerializedName("owner")
    val owner: Owner?,

    @ColumnInfo(name = "description")
    @Expose
    @SerializedName("description")
    val description: String?
) {
    data class Owner(

        @ColumnInfo(name = "avatar_url")
        @Expose
        @SerializedName("avatar_url")
        val avatarUrl: String?
    )
}