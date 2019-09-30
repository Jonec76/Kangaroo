package com.example.kangaroo._5_chatRoom

import android.net.Uri
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

class chatMsg{
    @Parcelize
    data class Data(val from: String, val msg: String?): Parcelable
}