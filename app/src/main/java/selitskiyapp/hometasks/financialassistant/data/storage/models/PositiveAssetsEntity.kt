package selitskiyapp.hometasks.financialassistant.data.storage.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "positiveBalance")
data class PositiveAssetsEntity(
    @ColumnInfo(name = "value")
    val value: Long,
    @ColumnInfo(name = "imageId")
    val imageId: String,
    @ColumnInfo(name = "category")
    val category: String,
    @ColumnInfo(name = "typeOfValue")
    val typeOfValue: String,
    @ColumnInfo(name = "date")
    val date: String,
    @ColumnInfo(name = "comment")
    val comment: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}