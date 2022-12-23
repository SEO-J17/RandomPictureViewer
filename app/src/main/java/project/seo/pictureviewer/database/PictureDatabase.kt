package project.seo.pictureviewer.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [PictureEntity::class], version = 1)
abstract class PictureDatabase : RoomDatabase() {
    abstract fun pictureDao(): PictureDao
}