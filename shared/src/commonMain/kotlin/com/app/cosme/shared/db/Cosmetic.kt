
import androidx.room.Entity
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

@Entity(tableName = "cosmetic")
data class Cosmetic(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = "brand_name")
    val brandName: String?,
    @ColumnInfo(name = "product_name")
    val productName: String?,
    @ColumnInfo(name = "category_name")
    val categoryName: String?,
    val memo: String?,
    @ColumnInfo(name = "image_path")
    val imagePath: String?,
    @ColumnInfo(name = "created_at")
    val createdAt: Long,
    @ColumnInfo(name = "is_favorite")
    val isFavorite: Boolean = false
)
