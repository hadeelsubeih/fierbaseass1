package com.example.myapplicationfirebaseass

import android.os.Parcel
import android.os.Parcelable

data class ListData (var id: Int, var name:String?, var number: String?, var address: String?) :
    Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(number)
        parcel.writeString(address)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ListData> {
        override fun createFromParcel(parcel: Parcel): ListData {
            return ListData(parcel)
        }

        override fun newArray(size: Int): Array<ListData?> {
            return arrayOfNulls(size)
        }
    }
}