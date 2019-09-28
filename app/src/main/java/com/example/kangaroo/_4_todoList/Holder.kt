package com.example.kangaroo._4_todoList

import android.graphics.Bitmap
import android.net.Uri
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

class Holder{
    @Parcelize
    data class Datas(val imgURL: Uri?, val text: String?): Parcelable
}