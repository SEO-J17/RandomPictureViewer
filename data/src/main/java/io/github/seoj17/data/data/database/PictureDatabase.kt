package io.github.seoj17.data.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import io.github.seoj17.domain.model.PictureEntity

@Database(entities = [PictureEntity::class], version = 1)
abstract class PictureDatabase : RoomDatabase() {
    abstract fun pictureDao(): PictureDao
}