package io.github.seoj17.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pictures")
data class PictureEntity(
    @ColumnInfo(name = "id", index = true)
    @PrimaryKey
    var id: Int,

    @ColumnInfo(name = "author")
    var author: String,

    @ColumnInfo(name = "width")
    var width: String,

    @ColumnInfo(name = "height")
    var height: String,

    @ColumnInfo(name = "url")
    var url: String,

    @ColumnInfo(name = "download_url")
    var downloadUrl: String,
) 