
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeCosmeticDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipeCosmetic(recipeCosmetic: RecipeCosmetic)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(recipeCosmetics: List<RecipeCosmetic>)

    @Query("SELECT * FROM cosmetic INNER JOIN recipe_cosmetic ON cosmetic.id = recipe_cosmetic.cosmetic_id WHERE recipe_cosmetic.recipe_id = :recipeId")
    fun getCosmeticsForRecipe(recipeId: Long): Flow<List<Cosmetic>>

    @Query("DELETE FROM recipe_cosmetic WHERE recipe_id = :recipeId")
    suspend fun deleteCosmeticsForRecipe(recipeId: Long)
}
