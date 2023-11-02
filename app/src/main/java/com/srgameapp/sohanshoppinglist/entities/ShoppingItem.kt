package com.srgameapp.sohanshoppinglist.entities

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import io.reactivex.annotations.NonNull


data class ShoppingItem(
    val itemName: String?,
    val price: Double?,
    var checked: Boolean?

):Parcelable



{


    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readValue(Boolean::class.java.classLoader) as? Boolean
    ) {
    }

    override fun toString(): String {
       return itemName.toString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(itemName)
        parcel.writeValue(price)
        parcel.writeValue(checked)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ShoppingItem> {
        override fun createFromParcel(parcel: Parcel): ShoppingItem {
            return ShoppingItem(parcel)
        }

        override fun newArray(size: Int): Array<ShoppingItem?> {
            return arrayOfNulls(size)
        }
    }
}




