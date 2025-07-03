package com.example.wishlistapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "wish-table")
data class Wish(
    @PrimaryKey(autoGenerate = true)
    val id : Long = 0L,
    @ColumnInfo(name = "wish-title")
    val title : String = "",
    @ColumnInfo(name = "wish-desc")
    val description : String = ""
)



object DummyWish{
    val wishList = listOf(
        Wish(title="complete android", description = "up to 31st of may"),
        Wish(title ="complete dsa", description = "up to 27th of july"),
        Wish(title = "do backend", description = "up to the holidays")
    )
}