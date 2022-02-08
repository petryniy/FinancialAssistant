package selitskiyapp.hometasks.financialassistant.domain.models

data class PositiveAssets(
    val value: Long,
    val imageId: String,
    val category: String,
    val typeOfValue: String,
    val date: String,
    val comment: String
)
