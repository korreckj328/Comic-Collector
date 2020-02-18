package com.korrecksoftware.comic_collector

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.Date

@Parcelize
@Entity
data class Book(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "publisher") val publisher: String,
    @ColumnInfo(name = "issueNumber") val issueNumber: Int,
    @ColumnInfo(name = "publicationDate") val publicationDate: String
): Parcelable