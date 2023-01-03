package io.github.seoj17.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import io.github.seoj17.doamain.model.PictureEntity

@Database(entities = [PictureEntity::class], version = 1)
abstract class PictureDatabase : RoomDatabase() {
    abstract fun pictureDao(): PictureDao
}