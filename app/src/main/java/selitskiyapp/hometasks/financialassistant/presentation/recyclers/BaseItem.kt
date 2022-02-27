package selitskiyapp.hometasks.financialassistant.presentation.recyclers

sealed class BaseItem

data class HeadItem(
    val date: String
): BaseItem()


