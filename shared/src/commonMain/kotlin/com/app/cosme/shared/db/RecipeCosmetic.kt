
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "recipe_cosmetic",
    primaryKeys = ["recipe_id", "cosmetic_id"],
    foreignKeys = [
        ForeignKey(
            entity = Recipe::class,
            parentColumns = ["id"],
            childColumns = ["recipe_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Cosmetic::class,
            parentColumns = ["id"],
            childColumns = ["cosmetic_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class RecipeCosmetic(
    val recipe_id: Long,
    val cosmetic_id: Long
)
