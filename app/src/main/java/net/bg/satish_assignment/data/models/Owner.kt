package net.bg.satish_assignment.data.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class Owner(
    val avatar_url: String,
    val events_url: String,
    val html_url: String,
    @PrimaryKey
    @ColumnInfo(name = "owner_id")
    val id: Int,
    val login: String,
    val node_id: String,
    val type: String,
    @ColumnInfo(name = "owner_url")
    val url: String
): Parcelable