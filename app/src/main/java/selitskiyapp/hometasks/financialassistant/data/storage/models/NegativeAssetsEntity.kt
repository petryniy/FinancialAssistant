package selitskiyapp.hometasks.financialassistant.data.storage.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "negativeBalance")
data class NegativeAssetsEntity(
    @ColumnInfo(name = "value")
    val value: Long, //число
    @ColumnInfo(name = "imageId")
    val imageId: String, //айди картинки
    @ColumnInfo(name = "category")
    val category: String, //категория расхода
    @ColumnInfo(name = "typeOfValue")
    val typeOfValue: String, //нал-безнал
    @ColumnInfo(name = "date")
    val date: String, //дата
    @ColumnInfo(name = "comment")
    val comment: String //комментарии
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}