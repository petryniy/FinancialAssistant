package selitskiyapp.hometasks.financialassistant.domain.models

import androidx.room.PrimaryKey

data class Operations(
    val category: String,
    val moneyHolderId: Int,
    val date: Long,
    val comment: String,
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}