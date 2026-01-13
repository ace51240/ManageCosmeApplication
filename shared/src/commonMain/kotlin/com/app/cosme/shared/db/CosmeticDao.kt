
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface CosmeticDao {
    @Query("SELECT * FROM cosmetic ORDER BY created_at DESC")
    fun getAllCosmetics(): Flow<List<Cosmetic>>

    @Query("SELECT * FROM cosmetic WHERE id = :id")
    fun getCosmeticById(id: Long): Flow<Cosmetic>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCosmetic(cosmetic: Cosmetic)

    @Update
    suspend fun updateCosmetic(cosmetic: Cosmetic)

    @Delete
    suspend fun deleteCosmetic(cosmetic: Cosmetic)
}
