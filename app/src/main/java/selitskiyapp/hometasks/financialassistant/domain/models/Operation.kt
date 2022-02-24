package selitskiyapp.hometasks.financialassistant.domain.models

data class Operation(
    var id: Int = 0,
    val category: String,
    val moneyHolderId: Int,
    val value: Long,
    val categoryImageId: Int,
    val date: String,
    val comment: String,
)

