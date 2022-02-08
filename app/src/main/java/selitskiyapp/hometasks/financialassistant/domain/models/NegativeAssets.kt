package selitskiyapp.hometasks.financialassistant.domain.models

data class NegativeAssets(
    val value: Long,
    val imageId: String,
    val category: String,
    val typeOfValue: String,
    val date: String,
    val comment: String
)
