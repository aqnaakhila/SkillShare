package com.example.skillsharehub.models

import android.os.Parcel
import android.os.Parcelable

data class ClassItem(
    val name: String,
    val instructor: String,
    val category: String,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(instructor)
        parcel.writeString(category)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ClassItem> {
        override fun createFromParcel(parcel: Parcel): ClassItem {
            return ClassItem(parcel)
        }

        override fun newArray(size: Int): Array<ClassItem?> {
            return arrayOfNulls(size)
        }
    }
}