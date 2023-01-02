package project.seo.pictureviewer.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PictureDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: List<PictureEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(entity: PictureEntity)

    @Delete
    suspend fun deleteAll(entity: PictureEntity)

    @Query("DELETE FROM pictures WHERE id = :id")
    suspend fun deleteItem(id: Int)

    @Query("SELECT * FROM pictures ORDER BY id ASC LIMIT :limit OFFSET :offset")
    suspend fun getAll(offset: Int, limit: Int): List<PictureEntity>

    @Query("SELECT * FROM pictures WHERE id = :id")
    suspend fun getItem(id: Int): PictureEntity?

}