package project.seo.pictureviewer.database

import android.content.Context
import androidx.room.Room

object LocalService {
    private lateinit var database: PictureDatabase

    fun create(context: Context) {
        database = Room.databaseBuilder(
            context,
            PictureDatabase::class.java,
            "picture.db"
        ).build()
    }

    fun getDao(): PictureDao {
        return database.pictureDao()
    }

    suspend fun getPicture(id: Int): PictureEntity? {
        return database.pictureDao().getItem(id)
    }

}