package project.seo.pictureviewer.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PictureDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: List<PictureEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: PictureEntity)

    @Query("DELETE FROM pictures")
    suspend fun deleteAll()

    @Query("DELETE FROM pictures WHERE id = :id")
    suspend fun delete(id: Int)

    @Query("SELECT * FROM pictures ORDER BY id ASC LIMIT :limit OFFSET :offset")
    suspend fun getPicture(offset: Int, limit: Int): List<PictureEntity>

    @Query("SELECT * FROM pictures WHERE id = :id")
    suspend fun getPicture(id: Int): PictureEntity?

}