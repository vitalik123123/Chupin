package com.example.tinf.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Post(
    val id: Int,
    val description: String,
    val gifURL: String,
): Parcelable
