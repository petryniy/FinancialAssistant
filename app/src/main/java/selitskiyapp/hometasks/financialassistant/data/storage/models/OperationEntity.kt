package selitskiyapp.hometasks.financialassistant.data.storage.models

import android.graphics.drawable.Drawable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "operations")
data class OperationEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    @ColumnInfo(name = "category")
    val category: String,
    @ColumnInfo(name = "moneyHolderId")
    val moneyHolderId: Int,
    @ColumnInfo(name = "value")
    val value: Long,
    @ColumnInfo(name = "categoryImageId")
    val categoryImageId: Int,
    @ColumnInfo(name = "date")
    val date: Long,
    @ColumnInfo(name = "comment")
    val comment: String,
)