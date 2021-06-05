package net.bg.satish_assignment.data.models

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class Item(
    val clone_url: String,
    val created_at: String,
    val default_branch: String,
    val deployments_url: String,
    val forks_count: Int,
    val forks_url: String,
    val full_name: String,
    @NonNull
    @PrimaryKey
    val id: Int,
    val open_issues_count: Int,
    @Embedded
    val owner: Owner,
    val url: String,
    val watchers: Int,
    val watchers_count: Int
):Parcelable

